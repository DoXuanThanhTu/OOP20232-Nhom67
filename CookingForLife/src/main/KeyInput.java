package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean upReleased, downReleased, leftReleased, rightReleased;
	public boolean setRunning = false;
	public boolean checkDrawTime = false;
	public boolean cPressed,pPressed;
	GamePanel gp;
	public KeyInput(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
//		if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_T) {
				if (checkDrawTime == false) {
					checkDrawTime = true;
				}else {
					checkDrawTime = false;
				}
			}
			if(code == KeyEvent.VK_P) {
				if(gp.gameState == gp.playState) {
					gp.gameState = gp.pauseState;
					pPressed = true;
				}else if (gp.gameState == gp.pauseState) {
						gp.gameState = gp.playState;
						pPressed = false;
						gp.playMusic(0);
				}
			}
			if (code == KeyEvent.VK_C) {
				cPressed = true;
			}
//		}
		
		}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
	}
	
}
