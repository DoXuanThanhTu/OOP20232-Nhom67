package object;

import java.io.IOException;
import static main.GamePanel.tileSize;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Table extends Entity{
	
	public OBJ_Table(String tableName, GamePanel gp) {
		super(gp);
		name = tableName;
		type = 0;
		collison = true;
		hitBox[0] = 0;
		hitBox[1] = 0;
		hitBox[2] = 64;
		hitBox[3] = 96;
		try {
			InputStream is = getClass().getResourceAsStream("/object/" + name +".png");
			image = ImageIO.read(is);
			widthObject = 64;
			heightObject = 96;
		}catch(IOException e) {
			e.getStackTrace();
		}
		}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public void clikOnEntity() {
		// TODO Auto-generated method stub
		
	}
}
