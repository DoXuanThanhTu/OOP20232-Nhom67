package entity;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.CollsionCheck;
import main.GamePanel;
import main.KeyInput;

public class Player extends Entity{
	KeyInput key;
	
	String PlayerSpriteIdle = "Idle.png";
	String PlayerSpriteWalking = "Walking.png";
	String PlayerSpriteRunning = "Running.png";
	BufferedImage[] animationIdle, animationWalking,animationRunning;
	int animationTick = 0;
	int animationSpeed = 8;
	int animationIndex = 0;
	int upIdleIndex = 0;
	int leftIdleIndex = 4;
	int rightIdleIndex = 8;
	int downIdleIndex = 12;
	int upWalkingIndex = 0;
	int leftWalkingIndex = 4;
	int rightWalkingIndex = 8;
	int downWalkingIndex = 12;
	String playerAction;
	public int hasKey = 0;
	public double playerScale = 4;
	public int playerWidth;
	public int playerHeight;
	public double hitBoxScale = 1.5;

	private boolean doublePressed;
	
	
	public Player(GamePanel gp, KeyInput key) {
		
		super(gp);
		this.key = key;
		playerWidth =(int)(gp.tileSize * playerScale);
		playerHeight =(int)(gp.tileSize * playerScale );
		setDefaultValues();
		
		
		animationIdle = loadAnimation(PlayerSpriteIdle);
		animationWalking = loadAnimation(PlayerSpriteWalking);
		animationRunning = loadAnimation(PlayerSpriteRunning);
		}
	public void setDefaultValues() {
		screenX = gp.tileSize / 2 + gp.tileSize * 12;
		screenY = gp.tileSize * 7;
		speed = 8;
		direction = "down";
		playerAction = "idle";
		locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
		locationHitBox[1] = screenY + gp.tileSize * 2;
		locationHitBox[2] = 32;
		locationHitBox[3] = 32;
	}
		
	public BufferedImage GetSpriteAlats(String fileName) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(getClass().getResourceAsStream("/player/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return img;
		}
	public void setAction(String action) {
		this.playerAction = action;
	}
	public void update() {
		if(gp.gameState == gp.playState) {
		if ((key.upPressed && key.downPressed) || (key.leftPressed && key.rightPressed))
			{
			setAction(PlayerSpriteIdle);
			doublePressed = true;
			}
		else if(key.upPressed && !key.downPressed) {
			direction = "up";
			setAction(PlayerSpriteWalking);
			doublePressed = false;
			
		}
		else if(key.downPressed && !key.upPressed) {
			direction = "down";
			setAction(PlayerSpriteWalking );
			doublePressed = false;
		}
		else if(key.rightPressed && !key.leftPressed ) {
			direction = "right";
			setAction(PlayerSpriteWalking );
			doublePressed = false;
		}
		else if(key.leftPressed && !key.rightPressed) {
			direction = "left";
			setAction(PlayerSpriteWalking);
			doublePressed = false;
		}
		else setAction(PlayerSpriteIdle);
//		}
		collisionOn = false;
		gp.collsionCheck.checkTile(this);
		int objIndex = gp.collsionCheck.checkObject(this, true);
//		pickUpObject(objIndex);
		
		int npcIndex = gp.collsionCheck.checkEntity(gp.player, gp.customers);
		interact(npcIndex);
//		if (key.upPressed && key.downPressed && key.leftPressed && key.rightPressed) setAction(PlayerSpriteIdle);
			if (collisionOn == false && (key.upPressed || key.downPressed || key.leftPressed || key.rightPressed) && doublePressed == false) {
				switch(direction) {
				case "up":
					screenY -= speed;
//					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
					locationHitBox[1] = screenY + gp.tileSize * 2;
//					locationHitBox[2] = 24;
//					locationHitBox[3] = 32;
					break;
				case "down":
					screenY += speed;
//					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
					locationHitBox[1] = screenY + gp.tileSize * 2;
//					locationHitBox[2] = 24;
//					locationHitBox[3] = 32;
					break;
				case "left":
					screenX -= speed;
					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
//					locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
//					locationHitBox[2] = 40;
//					locationHitBox[3] = 16;
					break;
				case "right":
					screenX += speed;
					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
//					locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
//					locationHitBox[2] = 40;
//					locationHitBox[3] = 16;
					break;
				}
			}
//			if(!gp.collsionCheck.checkinAScreen(this)) {
//				collisionOn = true;
//				switch(direction) {
//				case "up":
//					screenY -= speed;
////					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
//					locationHitBox[1] = screenY + gp.tileSize * 2;
////					locationHitBox[2] = 24;
////					locationHitBox[3] = 32;
//
//					break;
//				case "down":
//					screenY += speed;
////					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
//					locationHitBox[1] = screenY + gp.tileSize * 2;
////					locationHitBox[2] = 24;
////					locationHitBox[3] = 32;
//					break;
//				case "left":
//					screenX -= speed;
//					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
////					locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
////					locationHitBox[2] = 40;
////					locationHitBox[3] = 16;
//					break;
//				case "right":
//					screenX += speed;
//					locationHitBox[0] = screenX + gp.tileSize + 16 + 4;
////					locationHitBox[1] = screenY + gp.tileSize * 2 + 16;
////					locationHitBox[2] = 40;
////					locationHitBox[3] = 16;
//					break;
//				}
//			}
		}else if(gp.gameState == gp.pauseState){
		}
			
	}
	
	private void interact(int npcIndex) {
		// TODO Auto-generated method stub
		if (npcIndex!= 9999) {
			System.out.println("You are ");
		}
	}
//	public void pickUpObject(int i) {
//		
//		if(i != 9999) {
//			String nameObject = gp.obj.get(i).name;
//			switch(nameObject) {
//			case "key":
//				gp.playSE(3);
//				gp.obj.remove(i);
//				gp.ui.showMessage("You got a key");
//				hasKey++;
//				break;
//			case "door" :
//				if (hasKey >= 1) {
//					gp.playSE(2);
//					hasKey--;
//					gp.obj.remove(i);
//					gp.ui.showMessage("Opening the door");
//				}else {
//					gp.ui.showMessage("You need a key!");
//				}
//				break;
//			case "chest" :
//				gp.playSE(1);
//				gp.obj.remove(i);
//				gp.ui.gameFinished = true;
//				
//				break;
//			}
//		}
//	}
	public void draw(Graphics2D g2) {
		if(gp.gameState == gp.playState) {
			updateAnimation();
		}
		
		g2.setColor(Color.red);
		g2.drawRect(locationHitBox[0],locationHitBox[1],locationHitBox[2],locationHitBox[3]);
//		g2.drawRect(screenX + hitBoxSize[0], screenY + hitBoxSize[1],hitBoxSize[2], hitBoxSize[3]);
		if (playerAction == PlayerSpriteIdle ) {
			switch(direction) {
			case "up" :
				g2.drawImage(animationIdle[upIdleIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "down" :
				g2.drawImage(animationIdle[downIdleIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "right" :
				g2.drawImage(animationIdle[rightIdleIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "left" :
				g2.drawImage(animationIdle[leftIdleIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			}
		}else {
			switch(direction) {
			case "up" :
				g2.drawImage(animationWalking[upWalkingIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "down" :
				g2.drawImage(animationWalking[downWalkingIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "right" :
				g2.drawImage(animationWalking[rightWalkingIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			case "left" :
				g2.drawImage(animationWalking[leftWalkingIndex + animationIndex], screenX, screenY,playerWidth, playerHeight, null);
				break;
			}

		}
	}
	private int getAmount(String action) {
		if (action != PlayerSpriteWalking) {
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
			if (animationIndex >= getAmount(playerAction) ) {
				animationIndex = 0;
			}
		}
		
	}
	@Override
	public void clikOnEntity() {
		// TODO Auto-generated method stub
		
	}
}
