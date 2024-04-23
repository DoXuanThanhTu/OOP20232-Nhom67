package entity;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import main.GamePanel;


public class Customer extends Entity {
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
	int count = 0;
	public boolean checkedOrder = false;
	public Set<String> keys;
	public final static int CUSTOMER_MAX = 3;
	private LinkedHashMap<String, Float> orders; 
	public float bill = 0;
	public int done = 0;
	public Customer(GamePanel gp) {
		super(gp);
		setDefaultValues();
		animationIdle = loadAnimation(CustomerSpriteIdle);
		animationWalking = loadAnimation(CustomerSpriteWalking);
		animationRunning = loadAnimation(CustomerSpriteRunning);
	}
	public void setDefaultValues() {
		name = "Customer";
		screenX = gp.tileSize ;
		screenY = gp.tileSize * 7;
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2;
		locationHitBox[2] = 24;
		locationHitBox[3] = 32;
		speed = 4;
		direction = "right";
		customerAction = CustomerSpriteIdle;
		System.out.println(screenX + " " + locationHitBox[0]);
		this.orders = new LinkedHashMap<String, Float>();
		placeOrders();
	    keys = orders.keySet();
	    gp.totalOrder.add(keys);
	    
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
		locationHitBox[1] = screenY + gp.tileSize * 2 ;
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
					locationHitBox[1] = screenY + gp.tileSize * 2;
					break;
				case "down":
					screenY += speed;
					locationHitBox[1] = screenY + gp.tileSize * 2;
					break;
				case "left":
					screenX -= speed;
					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
					break;
				case "right":
					screenX += speed;
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
	g2.drawRect(locationHitBox[0],locationHitBox[1],locationHitBox[2],locationHitBox[3]);
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



public void placeOrders() {
    Menu menu = new Menu();
    Random ran = new Random();
    bill = 0;
    int i = 0;
    
    // Create an ordered list of menu categories
    ArrayList<String> categoryOrder = new ArrayList<>();
    categoryOrder.add("appetizers");
    categoryOrder.add("mainCourses");
    categoryOrder.add("desserts");
    categoryOrder.add("drinks");

    // Iterate over the categories in the desired order
    for (String category : categoryOrder) {
        HashMap<String, FoodItem> chosenMenu = null;
        switch (category) {
            case "appetizers":
                chosenMenu = menu.getAppetizers();
                break;
            case "mainCourses":
                chosenMenu = menu.getMainCourses();
                break;
            case "desserts":
                chosenMenu = menu.getDesserts();
                break;
            case "drinks":
                chosenMenu = menu.getDrinks();
                break;
        }
        
        int index = ran.nextInt(chosenMenu.size());
        String dishName = (String) chosenMenu.keySet().toArray()[index];
        float price = (float) chosenMenu.get(dishName).getPrice();
        orders.put(dishName, price);
        bill += price;
        
    }
}

public void checkOrder() {
	if (keys != null) {
		checkedOrder = true;
	}else {
		checkedOrder = false;
	}
}


public void eat(int i) {
	done++;
	System.out.println(name + " is eating " + i);
}

public void leave() {
	if (done == 4)
    System.out.println(name + " is leaving.");
}
public LinkedHashMap<String, Float> getOrders() {
	return orders;
}
public void setOrders(LinkedHashMap<String, Float> orders) {
	this.orders = orders;
}
@Override
public void clikOnEntity() {
	// TODO Auto-generated method stub
	if (gp.key.cPressed == true) {
		for (String x : gp.customers[0].getOrders().keySet()) {
			System.out.println(x);
		}
		System.out.println("Nhan c");
		gp.key.cPressed = false;
	}
}

}
