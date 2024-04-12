package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entities.Player;
import testGame.Chef;

public class Game implements Runnable{ // thread
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 60;
	private final int UPS_SET = 120;
	private Player player;
	public JLabel label;
	
	
	public JLabel getLabel() {
		return label;
	}


	public void setLabel(JLabel label) {
		this.label = label;
	}
	public final static int GAME_WIDTH = 256;
	public final static int GAME_HEIGHT = 256;
	public final static int GAME_WIDTH_SCALE = 3;
	public final static int GAME_HEIGHT_SCALE = 3;
	public Game() {
		gamePanel = new GamePanel(this);
		initClasses();
		Chef chef = new Chef("Chef ThanhTu");
		
		
		gamePanel.setLayout(null);
		gameWindow = new GameWindow(gamePanel);
		
		gamePanel.requestFocus();
		
		
		
		startGameLoop();
		chef.receiveOrder(getGamePanel().getCustomers().get(0));
		
		
		
	}
	
	
	private void initClasses() {
		// TODO Auto-generated method stub
		player = new Player(16 * GAME_WIDTH_SCALE * 13, 16 * GAME_HEIGHT_SCALE * 1 );
		
        
	}


	private  void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		player.update();
	}
	public void render(Graphics g) {
		player.room(g);
		player.render(g);
		
	}
	@Override
	public void run() {
		double timePerFrame = 1000000000 / FPS_SET;
		double timePerUpdate = 1000000000 / UPS_SET;
		
		double deltaU = 0;
		int update = 0;
		double deltaF = 0;
		long previousTime = System.nanoTime();
		int frame = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {
				update();
				update++;
				deltaU--;
			}
			if (deltaF >= 1) {
				gamePanel.repaint();
				deltaF--;
				frame++;
				
			}
			if (System.currentTimeMillis() - lastCheck > 1000) {
				lastCheck = System.currentTimeMillis();
				String s ="Fps : " + frame + "Ups " + update ;
				label = new JLabel(s);
				frame = 0;
				update = 0;
			}
		}	
	}
	public Player getPlayer() {
		return player;
	}


	public GamePanel getGamePanel() {
		return gamePanel;
	}


	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
}
