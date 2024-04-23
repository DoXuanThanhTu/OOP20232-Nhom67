package main;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import javax.swing.JPanel;

import entity.Chef;
import entity.Customer;
import entity.Dishes;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	public final static int tileSize = 32;
	public final int colInDisplays = 30;
	public final int rowInDisplays = 20;
	final int screenWidth = tileSize *colInDisplays;
	final int screenHeight = tileSize * rowInDisplays;
	TileManager tileManager = new TileManager(this);
	
	public KeyInput key = new KeyInput(this);
	public Player player = new Player(this, key);
	public Customer[] customers = new Customer[2];
	public Chef[] chefs = new Chef[1];
	public CollsionCheck collsionCheck = new CollsionCheck(this);
	public ArrayList<Entity> obj = new ArrayList<Entity>();
	public ArrayList<Entity> entityList = new ArrayList<Entity>();
	public AssetSetter assetSetter = new AssetSetter(this);
	public ArrayList<Set<String>> totalOrder = new ArrayList<Set<String>>();
	public ArrayList<Entity> allDishes = new ArrayList<Entity>();
	public ArrayList<Entity> panel = new ArrayList<Entity>();
	Sounds music = new Sounds();
	Sounds se = new Sounds();
	public LogicGame lg = new LogicGame(this);
	public UI ui = new UI(this);
	Thread gameThread;
	int FPS = 60;
	public boolean checkDrawTime = false;
	int PlayerX = 100;
	int PlayerY = 100;
	int PlayerSpeed = 4;
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public int totalCustomer = 0;
	
	
	public GamePanel() {
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		setDoubleBuffered(true);
		addKeyListener(key);
		setFocusable(true);
	}
	public void setUpgame() {
		assetSetter.loadObject();
		gameState = playState;
		playMusic(0);
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		
		gameThread.start();
		chefs[0].startChefThread();
		
	}
	public void updateTotalOrder(ArrayList<Set<String>> newTotalOrder) {
	    this.totalOrder = newTotalOrder;
	}

	@Override
	public void run() {
		double framePerSecond = 1000000000/FPS;
		double delta = 0;
		long lastCheck = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastCheck) / framePerSecond;
			timer += (currentTime - lastCheck);
			lastCheck = System.nanoTime();
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer > 1000000000) {
				System.out.println("FPS :" +drawCount);
				drawCount = 0;
				timer = 0;
			}
		
		}
	}
	public void update() {
		
		if(gameState == pauseState) {
			//
			stopMusic();
		}
	}
	public void paintComponent(Graphics g) {
		 ArrayList<Set<String>> newTotalOrder = ui.drawListInProcess(totalOrder); // Lấy danh sách mới từ UI
		    updateTotalOrder(newTotalOrder); // Cập nhật totalOrder với danh sách mới
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		long startDraw = 0;
		if (key.checkDrawTime == true) {
			startDraw = System.nanoTime();
		}
		//Tile
		tileManager.draw(g2);
		
		//Object
		
		
		entityList.add(player);
		for (int i = 0; i < chefs.length; i ++) {
			if (chefs[i] != null) {
				entityList.add(chefs[i]);
			}
		}
		for (int i = 0; i < customers.length; i++) {
			if(customers[i] != null ) {
				
				entityList.add(customers[i]);
				customers[i].clikOnEntity();
				totalCustomer = i;
				
				
//				if (customers[i].checkedOrder == false) {
//					for (String key : customers[i].keys) {
//						System.out.println(key);
//						
//					}
//				}
				
			}
		}
//		for (int i = 0; i < allDishes.size(); i++) {
//			entityList.add(allDishes.get(i));
//		}
		for (int i = 0; i< panel.size(); i++) {
			entityList.add(panel.get(i));
		}
		
		for (int i = 0; i < obj.size(); i++) {
			obj.get(i).draw(g2);
		}
		//SORT
		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				// TODO Auto-generated method stub
				int result = Integer.compare(o1.screenY, o2.screenY);
				return result;
			}
		});
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(g2);
			
		}
		for (int i = 0; i < entityList.size(); i++) {
			
			if (gameState == playState) {
				entityList.get(i).update();
				
				}
			entityList.remove(i);
		}
		ui.draw(g2);
		
		if (key.checkDrawTime == true) {
			long endDraw = System.nanoTime();
			long passed = endDraw - startDraw;
		}
		g2.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		music.setFile(i);
		music.play();
	}
}
