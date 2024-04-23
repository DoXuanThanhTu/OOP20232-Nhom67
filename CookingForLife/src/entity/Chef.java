package entity;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import entity.Customer;
 
public class Chef extends Entity implements Runnable {
    private ArrayList<Dishes> DishesesInProgress;
    public Stack<Dishes> dishesProgressbyChef ;
    String CustomerSpriteIdle = "Idle.png";
	String CustomerSpriteWalking = "Walking.png";
	String CustomerSpriteRunning = "Running.png";
	BufferedImage[] animationIdle, animationWalking,animationRunning;
	int animationTick = 0;
	int animationSpeed = 4;
	int animationIndex = 0;
	int upIdleIndex = 0;
	int leftIdleIndex = 4;
	int rightIdleIndex = 8;
	int downIdleIndex = 12;
	int upWalkingIndex = 0;
	int leftWalkingIndex = 4;
	int rightWalkingIndex = 8;
	int downWalkingIndex = 12;
	String customerAction;
	public int customerWidth = 64 * 2;
	public int customerHeight = 64 * 2;
	public int actionindex;
	public int count = 0;
	public boolean checkedOrder = false;
	public Set<String> keys;
	int max_order = 4;
	public ArrayList<Dishes> takeOrder = new ArrayList<Dishes>();
	Thread chefThread;
	public String text;
	UI ui;
	public int dishCompletedCount = 0;
	public String dishCompletedname;

    public Chef( GamePanel gp) {
    	
    	super(gp);
    	this.ui = gp.ui;
        this.DishesesInProgress = new ArrayList<>();
        setDefaultValues();
		animationIdle = loadAnimation(CustomerSpriteIdle);
		animationWalking = loadAnimation(CustomerSpriteWalking);
		animationRunning = loadAnimation(CustomerSpriteRunning);
//		startChefThread();
		
    }
    
    public void  receiveOrder(ArrayList<Set<String>> totalOrder) {   
    	ArrayList<Set<String>> temp = totalOrder;
    	int ka;
    	for (int i = 0; i < temp.size(); i++) {
    			
    				
    				if (max_order > 0) {
    					for (String k : temp.get(i)) {
    	    				Dishes d = new Dishes(k);
    	    				int x  = d.isType(d.getName());
    	    			takeOrder.add(d);
    	    			System.out.println(takeOrder.get(takeOrder.size() - 1).getName());
    	        		System.out.println(name + " received an order for " + takeOrder.get(takeOrder.size() - 1).getName());
    	        		 takeOrder.get(takeOrder.size() - 1).startPreparing();
    	        		 
    	        	     max_order--;
    	        	    takeOrder.get(takeOrder.size() - 1).isCompleted(takeOrder.get(takeOrder.size() - 1), x);
//    	        	    ka = takeOrder.size() / 4;
//    	        	   if ( takeOrder.get(takeOrder.size() - 1).completed == true ) {
//    	        		   gp.customers[ka].eat(gp.customers[ka].done);
//    	        		   gp.customers[ka].leave();
//    	        	   }
    	        	    
    	        	    max_order+=1;
    			}
    			ui.update = 0;	
    	}
    		
 
//    	System.out.println(dish.size());
//    	while(dish.peek() != null) {
//    		int i = 0;
//    		if (max_order > 0) {
//    			System.out.println(i);
//    			Dishes d = dish.peek();
//    			takeOrder.add(d);
//        		System.out.println(name + " received an order for " + takeOrder.get(i).getName());
//        		 takeOrder.get(i).startPreparing();
//        	     DishesesInProgress.add(takeOrder.get(i));
//        	     max_order--;
//        	    takeOrder.get(0).isCompleted(, i);
//        	    System.out.println("da hoan thanh");
//        	    max_order+=1;
//        	    Dishes temp = dish.poll();
//        	    d = temp;
//        	    i++;
//    		}
//    	}
//    	int i = 0;
//    	switch(i % 4) {
//    	case 0:
//    		break;
//    	}
    }
       
    }
    public void startChefThread() {
    	chefThread = new Thread(this);
    	chefThread.start();
    }
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	gp.chefs[0].receiveOrder(gp.totalOrder);
    }

    public void completeDishes(Dishes Dishes) {
        System.out.println(name + " has completed preparing " + Dishes.getName() + ".");
        DishesesInProgress.remove(Dishes);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Dishes> getDishesesInProgress() {
        return DishesesInProgress;
    }

	public void setDefaultValues() {
		name = "Chef";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2;
		locationHitBox[2] = 24;
		locationHitBox[3] = 32;		speed = 4;
		direction = "right";
		customerAction = CustomerSpriteIdle;
		System.out.println(screenX + " " + locationHitBox[0]);
//	    keys = orders.keySet();
//	    checkOrder();
	}
	public BufferedImage GetSpriteAlats(String fileName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/npc/" + fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
public void setAction() {
	actionCounter++;
	
	if (actionCounter == 120) {
	Random rd1 = new Random();
	Random rd2 = new Random();
	switch(rd1.nextInt(4)) {
	case 4:
		direction = "down";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2;
		locationHitBox[2] = 24;
		locationHitBox[3] = 32;
		break;
	case 1:
		direction = "up";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2;
		locationHitBox[2] = 24;
		locationHitBox[3] = 32;
		break;
	case 2:
		direction = "right";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
		locationHitBox[2] = 40;
		locationHitBox[3] = 16;
		break;
	case 3:
		direction = "left";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
		locationHitBox[2] = 40;
		locationHitBox[3] = 16;
		break;
	
	}
	switch(rd2.nextInt(2)) {
	case 2:
		customerAction = CustomerSpriteIdle;
		break;
	case 1:
		customerAction = CustomerSpriteWalking;
		break;
	
	}
	actionCounter = 0;
	}
}
public void update() {

	collisionOn = false;
	gp.collsionCheck.checkTile(this);
	gp.collsionCheck.checkPlayer(this, gp.player);
	int objIndex = gp.collsionCheck.checkObject(this, true);
	int i = 0;
			if (customerAction == CustomerSpriteWalking && collisionOn == false) {
				switch(direction) {
				case "up":
					screenY -= speed;
					locationHitBox[1] = screenY + gp.tileSize * 2 ;
					break;
				case "down":
					screenY += speed;
					locationHitBox[1] = screenY + gp.tileSize * 2 ;
					break;
				case "left":
					screenX -= speed;
					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
					break;
				case "right":
					screenX += speed;
					locationHitBox[0] = screenX + gp.tileSize / 2 + 8;
					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
					break;
				}
			}else if(customerAction == CustomerSpriteWalking && collisionOn == true) {
				customerAction = CustomerSpriteIdle;
				switch(direction) {
				case "up":
					direction = "down";
					break;
				case "down":
					direction = "up";
					break;
				case "left":
					direction = "right";
					break;
				case "right":
					direction = "left";
					break;
			}
			}
		}
		
public void draw(Graphics2D g2) {
//	g2.setColor(Color.white);
	
	if(gp.gameState == gp.playState) {
		updateAnimation();
		setAction();
	}
	g2.drawString(name, screenX + gp.tileSize, screenY + gp.tileSize * 2);
	g2.setColor(Color.white);
	g2.setStroke(new BasicStroke(1));
	g2.drawRect(locationHitBox[0] + 1,locationHitBox[1] + 1,locationHitBox[2] - 2,locationHitBox[3] - 2);
//	g2.drawRect(screenX + hitBoxSize[0], screenY + hitBoxSize[1],hitBoxSize[2], hitBoxSize[3]);
	if (customerAction == CustomerSpriteIdle && animationIndex != getAmount(customerAction)) {
		switch(direction) {
		case "up" :
			g2.drawImage(animationIdle[upIdleIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "down" :
			g2.drawImage(animationIdle[downIdleIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "right" :
			g2.drawImage(animationIdle[rightIdleIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "left" :
			g2.drawImage(animationIdle[leftIdleIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		}
	}else {
		switch(direction) {
		case "up" :
			g2.drawImage(animationWalking[upWalkingIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "down" :
			g2.drawImage(animationWalking[downWalkingIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "right" :
			g2.drawImage(animationWalking[rightWalkingIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		case "left" :
			g2.drawImage(animationWalking[leftWalkingIndex + animationIndex], screenX, screenY,customerWidth, customerHeight, null);
			break;
		}

	}
	
}
private int getAmount(String action) {
	if (action != CustomerSpriteWalking) {
		return 4;
	}else return 4;
}
private BufferedImage[] loadAnimation(String name) {
	// TODO Auto-generated method stub
		BufferedImage img = GetSpriteAlats(name);
		int col = img.getWidth() / 64;
		int row = img.getHeight() / 64;
		BufferedImage[] animation = new BufferedImage[col * row];
		int i = 0;
		int j = 0;
			for( j = 0; j < row; j++) {
				for (i = 0; i < col; i++) {
					animation[j * col + i] = img.getSubimage(i * 64, j *64, 64, 64);
				}
		}
		return animation;
}
private void updateAnimation() {
	animationTick++;
	if (animationTick >= animationSpeed) {
		animationTick = 0;
		animationIndex++;
		if (animationIndex >= getAmount(customerAction) - 1) {
			animationIndex = 0;
		}
	}
}

@Override
public void clikOnEntity() {
	// TODO Auto-generated method stub
	
}





   
}
