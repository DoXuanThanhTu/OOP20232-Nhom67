package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class FoodImage {
	public FoodImage() {
		// TODO Auto-generated constructor stub
	}
	public BufferedImage loadImage(String name) {
		BufferedImage image = null;
		InputStream is = getClass().getResourceAsStream("/food/" + name + "png");
		try {
			 image = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
