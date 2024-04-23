package object;

import static main.GamePanel.tileSize;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_InProcess extends Entity{
	public  OBJ_InProcess(String foodName, GamePanel gp) {
		super(gp);
		name = foodName;
		type = 1;
		collison = true;
		hitBox[0] = 0;
		hitBox[1] = 0;
		hitBox[2] = 512;
		hitBox[3] = 64;
		try {
			InputStream is = getClass().getResourceAsStream("/food/" + name +".png");
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
