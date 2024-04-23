package object;

import java.io.IOException;

import java.io.InputStream;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

import static main.GamePanel.tileSize;

public class OBJ_Chair extends Entity {
	public boolean hasSeat;
	public OBJ_Chair(String chairName, GamePanel gp) {
		super(gp);
		hasSeat = false;
		name = chairName;
		collison = true;
		hitBox[0] = 0;
		hitBox[1] = tileSize;
		hitBox[2] = tileSize;
		hitBox[3] = tileSize;
		type = 0;
		try {
			InputStream is = getClass().getResourceAsStream("/object/" + name +".png");
			image = ImageIO.read(is);
			widthObject = 32;;
			heightObject = 64;
		}catch(IOException e) {
			e.getStackTrace();
		}
		}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clikOnEntity() {
		// TODO Auto-generated method stub
		
	}
}
