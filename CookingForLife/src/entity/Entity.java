package entity;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
	public String name;
	public int type;
	public int screenX, screenY;
	protected GamePanel gp;
	public int speed;
	public String direction;
	public int locationHitBox[] = new int[4];
	public boolean collisionOn = false;
	public int actionCounter = 0;
	public BufferedImage image;
	public int widthObject, heightObject;
	public int hitBox[] = new int[4];
	public boolean collison;
	public abstract void update();
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void draw (Graphics2D g2) {
		switch(type) {
		case 0 :
			g2.drawImage(image, screenX, screenY,widthObject, heightObject, null);
			g2.setColor(Color.white);
			g2.drawString(name, screenX, screenY);
			g2.drawRect(screenX + hitBox[0], screenY + hitBox[1], hitBox[2], hitBox[3]);
		case 1 :
			g2.drawImage(image, screenX, screenY,hitBox[2], hitBox[3], null);
		}
		
	}
	public abstract void clikOnEntity();
}
