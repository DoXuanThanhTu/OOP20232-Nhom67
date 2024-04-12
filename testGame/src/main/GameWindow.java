package main;
import java.awt.event.WindowEvent;
import static main.Game.*;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class GameWindow {
	private JFrame frame = new JFrame();
	
	
	public GameWindow(GamePanel gamePanel) {
		frame.add(gamePanel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.pack();
		frame.setBounds(0, 0, GAME_WIDTH * GAME_WIDTH_SCALE, GAME_HEIGHT * GAME_HEIGHT_SCALE + 40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().getPlayer().windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void addPanel(JPanel panel) {
		frame.add(panel);
		panel.setVisible(true);
	}
}
