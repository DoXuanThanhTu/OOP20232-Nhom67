package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import LoadImg.LoadImg;
import static LoadImg.LoadImg.*;
import static entities.Player.*;
import static main.Game.*;

public abstract class Entity {
	protected float x, y;
	protected int width, height;
	protected Rectangle hitBox;
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initHitBox();
	}
	protected void initHitBox() {
		hitBox = new Rectangle((int) x , (int) y, width, height);
	}
	protected void updateHitBox() {
		hitBox.x = (int) x;
		hitBox.y = (int) y;
	}
	protected void drawHitBox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect(hitBox.x, hitBox.y, width, height);
		updateHitBox();
	}
	protected static boolean canMove(float x, float y, int width, int height) {
		if (x < 0 || x + width* 3 - 2  >= GAME_WIDTH * GAME_WIDTH_SCALE) {
			return false;
		}
		if (y < 0 || y + height * 3 - 2 >=GAME_HEIGHT_SCALE * GAME_HEIGHT)
			return false;
		if (isLineGreen(x, y, width, height)) return true;
		else return false;
		
	}
	static Color c = new Color(LoadImg.GetSpriteAlats(Room).getRGB(16, 16));
	static int k = c.getGreen();
	static BufferedImage img = LoadImg.GetSpriteAlats(Room);
	protected static boolean isLineGreen(float x, float y, int width, int height) {
		int xP = (int) x / GAME_WIDTH_SCALE;
		int yP = (int) y / GAME_HEIGHT_SCALE;
		BufferedImage check = img.getSubimage(xP , yP , width - 2, height - 2);
		int value = 0;
		
		for (int i = 0; i < check.getWidth(); i++) {
			for (int j = 0; j < check.getHeight() ; j++) {
				Color cc = new Color(check.getRGB(i, j));
				value = cc.getGreen();
//				System.out.println(value);
				
			}
			if (value != k) return false;
		}
		return true;
}
}
