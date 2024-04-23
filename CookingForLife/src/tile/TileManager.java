package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int [gp.colInDisplays][gp.rowInDisplays];
		getTileImage();
		loadMap("map03.txt");
	}
	private void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].img = ImageIO.read(getClass().getResource("/Tiles/carpet01.png"));
			
			tile[1] = new Tile();
			tile[1].img = ImageIO.read(getClass().getResource("/Tiles/carpet02.png"));
			
			tile[2] = new Tile();
			tile[2].img = ImageIO.read(getClass().getResource("/Tiles/carpet03.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].img = ImageIO.read(getClass().getResource("/Tiles/conwall.png"));
			tile[3].collision = true;

			
			tile[4] = new Tile();
			tile[4].img = ImageIO.read(getClass().getResource("/Tiles/floor.png"));
			
			tile[5] = new Tile();
			tile[5].img = ImageIO.read(getClass().getResource("/Tiles/howall.png"));
			tile[5].collision = true;
			
			tile[6] = new Tile();
			tile[6].img = ImageIO.read(getClass().getResource("/Tiles/pane.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].img = ImageIO.read(getClass().getResource("/Tiles/vewall.png"));
			tile[7].collision = true;

			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	public void loadMap(String name){
		
		try {
			int i = 0;
			InputStream is = getClass().getResourceAsStream("/maps/" + name);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < gp.colInDisplays && row < gp.rowInDisplays) {
				String line = br.readLine();
				while(col < gp.colInDisplays) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.colInDisplays) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		int x = 0, y = 0;
		while(worldCol < gp.colInDisplays && worldRow < gp.rowInDisplays) {
			int tileNum = mapTileNum[worldCol][worldRow];
			g2.drawImage(tile[tileNum].img, x, y,gp.tileSize, gp.tileSize, null);
			worldCol++;
			x+= gp.tileSize;
			if (worldCol == gp.colInDisplays) {
				worldCol = 0;
				worldRow++;
				x = 0;
				y += gp.tileSize;
			}
		}
	}
	public void draw1(Graphics2D g2) {
		g2.drawImage(tile[1].img, 0, 0,gp.tileSize, gp.tileSize, null);
	}
}
