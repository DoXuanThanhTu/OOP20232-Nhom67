package main;

import javax.imageio.ImageIO;
import static testGame.Customer.*;

import static main.Game.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import testGame.Customer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private Game game;
	ArrayList<Customer> customers = new ArrayList<Customer>();
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		JLabel label = new JLabel("Orders : ");
		Font font = new Font("Arial", Font.BOLD, 20);
		label.setBounds(600,450,100, 30);;
		label.setFont(font);
		add(label);
		customers.add(new Customer("John"));
		customers.add(new Customer("Max"));
		for (int i = 0 ; i < customers.size(); i++) {
			int xPostion = 0;
			System.out.println(customers.size() + i);
			JLabel label2 = new JLabel(customers.get(i).getName());
			label2.setBounds(600 , 500 + i * 120, 150, 120);
			label2.setFont(font);
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			label2.setBorder(border);
			add(label2);
			Set<String> keys = customers.get(i).getOrders().keySet();
			
			ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
			for (String key : keys) {
				JLabel temp = new JLabel(key);
				temp.setBounds(50, 0 + xPostion , 100, 30);
				labelArray.add(temp);
				xPostion += 30;
			}
			for (JLabel lablek : labelArray) {
				label2.add(lablek);
			}
			xPostion = 0;
		
	}
		
	}
	
	private void setPanelSize() {
		// TODO Auto-generated method stub
		Dimension size = new Dimension(GAME_WIDTH * GAME_WIDTH_SCALE, GAME_HEIGHT * GAME_HEIGHT_SCALE);
		setPreferredSize(size);
		setLayout(null);
//		TextField textField = new TextField("game width " + GAME_WIDTH + " GAME HEIGHT " + GAME_HEIGHT);
//		textField.setBounds(0, 0, GAME_WIDTH / 2, 30);
//		textField.setVisible(true);
//		add(textField);
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		
//		System.out.println(PlayerDirection +" " + PlayerAction + " " + idleAnimationIndex);
	}
	public Game getGame() {
		return game;
	}

	
}
