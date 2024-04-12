package LoadImg;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import main.Game;


public class LoadImg {
	public static final String Food = "Food.png";
	public static final String Room = "Phòng-ăn-Sheet.png";
	public static final String Chef = "yeutinh.png";
	public static BufferedImage GetSpriteAlats(String fileName) {
		InputStream is  = LoadImg.class.getResourceAsStream("" + fileName);
		BufferedImage img = null;
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				is.close();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return img;
	}
}
