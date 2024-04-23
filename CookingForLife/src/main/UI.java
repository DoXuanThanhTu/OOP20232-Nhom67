package main;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import entity.Customer;


public class UI {
	GamePanel gp;
	public Graphics2D g2;
	Font arial_40 ,arial_80B;
	Font arial_16B ;
//	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime = 0;
	DecimalFormat df = new DecimalFormat("#0.00");
	public int  Uipaint = 0;
	public int update = 0;
	 Font arial_10;
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_10 = new Font("Arial", Font.BOLD, 10);
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		arial_16B = new Font("Arial", Font.BOLD, 16);
//		OBJ_Key key = new OBJ_Key();
//		keyImage = key.image;
		
	}
	public void showMessage(String message) {
		this.message = message;
		this.messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
//			gp.chefs[0].count = 0;
		if(gp.gameState == gp.playState) {
			drawListOrder(drawListInProcess(gp.totalOrder));
//			drawListInProcess(gp.totalOrder);
			
				
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}
	private void drawPauseScreen() {
		// TODO Auto-generated method stu
		String text = "PAUSED";
		int x = gp.tileSize * 11;
		int y = gp.tileSize * 10;
		g2.setFont(arial_80B);
		g2.setFont(g2.getFont().deriveFont(80F));
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
	}
	
	public void  drawListOrder(ArrayList<Set<String>> key) {
		int x = gp.tileSize * 1;
		int y = gp.tileSize ;
		g2.setFont(arial_10);
		g2.setColor(Color.white);
		for (int i = 0; i < key.size(); i++) {
			for (String text : key.get(i)) {		
			g2.drawString(text, x, y);
//			g2.drawImage(gp.chefs[0].takeOrder.get(i).image, x, y, 32, 32,null);
				x += 2 * gp.tileSize + 16;
			}
			x = gp.tileSize * 1;
			y += gp.tileSize;
		}
	}
	public ArrayList<Set<String>> drawListInProcess(ArrayList<Set<String>> totalOrder) {
	    ArrayList<Set<String>> temp = new ArrayList<>(totalOrder); 
	    
	    if (Uipaint != 0) {
	        int x = gp.tileSize * 15;
	        int y = gp.tileSize - 5;
	        g2.setFont(arial_10);
	        
			
	        for (int i = 0; i < gp.chefs[0].takeOrder.size(); i++) {
	        	g2.setColor(Color.white);
	            g2.drawString(gp.chefs[0].takeOrder.get(i).getName(), x, y);
	            g2.drawImage(gp.chefs[0].takeOrder.get(i).image, x, y, 32, 32,null);
	            if (gp.chefs[0].takeOrder.get(i).completed) {
	            	g2.setColor(Color.green);
	                g2.drawString("is done", x, y + gp.tileSize);
	                g2.setColor(Color.black);
	                g2.setFont(arial_16B);
	                g2.drawString(gp.customers[(int)i/ 4].name + "is eating " + gp.chefs[0].takeOrder.get(i).getName() ,  300, 500);

	                
	                for (int j = 0; j < temp.size(); j++) {
	                	System.out.println( j + " " + temp.get(j));
	                }
	            } else {
	    	        g2.setFont(arial_10);
	                g2.drawString("in process "  + gp.chefs[0].takeOrder.get(i).needTime, x, y + gp.tileSize);
	                if (!temp.isEmpty()) {
	                    if (!temp.get(0).isEmpty()) {
	                        Set<String> firstSet = new LinkedHashSet<String>(temp.get(0));
	                        
	                        // Xóa tên món ăn đã hoàn thành khỏi bản sao của phần tử đầu tiên của temp
	                        for (String k : firstSet) {
	                        	if (k == gp.chefs[0].takeOrder.get(i).getName()) {
	                        		firstSet.remove(k);
	                        		break;
	                        	}
	                        	break;
	                        }
	                        
	                        // Thay thế phần tử đầu tiên của temp bằng bản sao đã được chỉnh sửa
	                        temp.set(0, firstSet);
	                    } else {
	                        // Nếu phần tử đầu tiên của temp không tồn tại, xóa phần tử đầu tiên của temp
	                        temp.remove(0);
	                    }
	                }
	            }

	            x += 3 * gp.tileSize;
	            if (x > gp.screenWidth - 5 * gp.tileSize) {
	                x = gp.tileSize * 15;
	                y += 3 * gp.tileSize;
	            }
	           
	        }
	    }

	    Uipaint = 1;
	    return temp;
	}


}
