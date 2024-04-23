package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Set;

import entity.Customer;

public class LogicGame {
	GamePanel gp;
	ArrayList<Set<String>> customersOrder = new ArrayList<Set<String>>();
	public LogicGame(GamePanel gp) {
		// TODO Auto-generated constructor stub
		this.gp = gp;
	}
	public void customerPlaceOrders() {
		for (int i = 0; i < gp.customers.length; i++) {
			while(gp.customers[i] != null) {
//				customers[i].placeOrders();
			customersOrder.add(gp.customers[i].getOrders().keySet());
			System.out.println("lo");
			}
		}
	}
}
