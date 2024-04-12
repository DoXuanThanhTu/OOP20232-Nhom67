package inputs;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import javax.swing.JPanel;
import main.GamePanel;
 
public class KeyboardInputs extends JPanel implements KeyListener {
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setDir('a');
			gamePanel.getGame().getPlayer().setMoving(true);
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setDir('d');
			gamePanel.getGame().getPlayer().setMoving(true);
			gamePanel.getGame().getPlayer().setRight(true);
			break;
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setDir('w');
			gamePanel.getGame().getPlayer().setMoving(true);
			gamePanel.getGame().getPlayer().setUp(true);
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDir('s');
			gamePanel.getGame().getPlayer().setMoving(true);
			gamePanel.getGame().getPlayer().setDown(true);
			break;
//		case KeyEvent.VK_SPACE:
////			gamePanel.getGame().getPlayer().setDir(3);
//			gamePanel.getGame().getPlayer().setJump(true);
//			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			break;
		case KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(false);
			break;
		case KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(false);
			break;
//		case KeyEvent.VK_SPACE:
////			gamePanel.getGame().getPlayer().setDir(3);
//			gamePanel.getGame().getPlayer().setJump(true);
//			break;
		}
	}
	

}
