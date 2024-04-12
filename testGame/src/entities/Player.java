package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import LoadImg.LoadImg;
import static LoadImg.LoadImg.*;

import static main.Game.*;
import Const.PlayerConst;
import Const.PlayerConst.*;
import static Const.PlayerConst.PlayerConstants.*;
import static Const.PlayerConst.PlayerConstants.GetSpriteAmount;

public class Player extends Entity {
	private boolean moving;
	BufferedImage[] animation;
	public final static int TITLE_SIZE = 16;
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isMoving() {
		return moving;
	}
	public final static int CHARATER_SIZE_WIDTH = 120;
	public final static int CHARATER_SIZE_HEIGHT = 130;
	public final static int CHARATER_WIDTH = 16;
	public final static int CHARATER_HEIGHT = 32;
	public final static int CHARATER_FINAL_WIDTH = CHARATER_WIDTH * GAME_WIDTH_SCALE;
	public final static int CHARATER_FINAL_HEIGHT = CHARATER_HEIGHT * GAME_HEIGHT_SCALE;
	public final static float hitBoxSacle = 1.0f;
	private char playerDirection = 's';
	private int PlayerAction = upIDLE;
	private int animationTick = 0, idleAnimationIndex = 0, animationSpeed = 10;
	private boolean left = false, right = false, up = false, down = false, jump, fall;
	private float xDelta = 1, yDelta = 1;
	public int xCharImg, yCharImg;
	
	public Player(float x, float y) {
		super(x, y, (int)(CHARATER_FINAL_WIDTH * hitBoxSacle) ,(int) (CHARATER_FINAL_HEIGHT * hitBoxSacle));
		loadAnimation();
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public int setDir(char playerDirection) {
		// TODO Auto-generated method stub
		this.playerDirection = playerDirection;
		moving = true;
		return this.playerDirection;
	}
	private void loadAnimation() {
		// TODO Auto-generated method stub
		BufferedImage img = LoadImg.GetSpriteAlats(Chef);
		xCharImg = img.getWidth() / CHARATER_SIZE_WIDTH;
		yCharImg = img.getHeight() / CHARATER_SIZE_HEIGHT;
		
		animation  = new BufferedImage[xCharImg * yCharImg];
		for (int j = 0; j < yCharImg; j++) {
			for (int i = 0; i < xCharImg; i++) {
				animation[i + j * xCharImg ] = img.getSubimage(i*CHARATER_SIZE_WIDTH, j * CHARATER_SIZE_HEIGHT, CHARATER_SIZE_WIDTH, CHARATER_SIZE_HEIGHT);
			}
		}
	}

	public String getPlayerActionIndex() {
		// TODO Auto-generated method stub
		return null;
	}
	public void updatePos() {
		moving = false;
		float xUpdate = 0, yUpdate = 0;
			if (left && !right) {
				xUpdate = -xDelta;
				setPlayerDirection('a');
				moving = true;
			} else if (!left && right) {
				xUpdate = xDelta;
				moving = true;
				setPlayerDirection('d');
			}
			
			if (up && !down) {
				yUpdate = -yDelta;
				setPlayerDirection('w');
				moving = true;
			} else if (!up && down) {
				yUpdate = yDelta;
				moving = true;
				setPlayerDirection('s');
			}
			if(canMove(x + xUpdate, y + yUpdate, (int)(16), (int)(32))) {
				this.x += xUpdate;
				this.y += yUpdate;
				
			}
	}
	private void setAnimation() {
		// TODO Auto-generated method stub
		int startAnimation = PlayerAction;
		if (moving == false) {
			if (playerDirection == 's') {
				setPlayerAction(downIDLE);
			} else if (playerDirection == 'w') {
				setPlayerAction(upIDLE);
			} else if (playerDirection == 'd') {
				setPlayerAction(rightIDLE);
			} else {
				setPlayerAction(leftIDLE);
			}
			
		} else {
			if (playerDirection == 's') {
			setPlayerAction(downRUN);
		} else if (playerDirection == 'w') {
			setPlayerAction(upRUN);
		} else if (playerDirection == 'd') {
			setPlayerAction(rightRUN);
		} else {
			setPlayerAction(leftRUN);
		}
		}
			
		
		if (startAnimation != PlayerAction) {
			animationTick = 0;
			idleAnimationIndex = 0;
		}
		}
	private void updateAnimationTick() {
//		// TODO Auto-generated method stub
		animationTick++;
		if (animationTick >= animationSpeed) {
			animationTick = 0;
			idleAnimationIndex++;
			if (idleAnimationIndex >= GetSpriteAmount(PlayerAction)) {
				idleAnimationIndex = 0;
			}
		}
		
	}
	
	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		
	}
	public int getPlayerDirection() {
		return playerDirection;
	}
	public void setPlayerDirection(char playerDirection) {
		this.playerDirection =  playerDirection;
	}
	public int getPlayerAction() {
		return PlayerAction;
	}
	public void setPlayerAction(int playerAction) {
		PlayerAction = playerAction;
	}
	public void render(Graphics g) {
//		g.drawImage(animation[PlayerAction * yCharImg + PlayerConst.PlayerConstants.GetSpriteAmount(PlayerAction)], 0, 0, null);
		g.drawImage(animation[PlayerAction * xCharImg + idleAnimationIndex],(int)x,(int)y,CHARATER_FINAL_WIDTH, CHARATER_FINAL_HEIGHT, null);
		drawHitBox(g);
		
		
	}
	public void room(Graphics g) {
		
		g.drawImage(LoadImg.GetSpriteAlats(Room),0,0,GAME_WIDTH * GAME_WIDTH_SCALE, GAME_HEIGHT * GAME_HEIGHT_SCALE, null);
	}
	public void windowFocusLost() {
		// TODO Auto-generated method stub
		moving = false;
		left = false;
		right = false;
		up = false;
		down = false;
		PlayerAction = PlayerAction % 4;
		
	}
}
