package main;

import entity.Chef;
import entity.Customer;

import object.OBJ_Chair;
import object.OBJ_InProcess;
import object.OBJ_Order;
import object.OBJ_Steak;
import object.OBJ_Table;


public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void loadObject() {
		gp.obj.add(new OBJ_Chair("leftchair" , gp));
		gp.obj.get(0).screenX = gp.tileSize * 3;
		gp.obj.get(0).screenY = gp.tileSize * 10;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(1).screenX = gp.tileSize * 3;
		gp.obj.get(1).screenY = gp.tileSize * 12;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(2).screenX = gp.tileSize * 3;
		gp.obj.get(2).screenY = gp.tileSize * 15;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(3).screenX = gp.tileSize * 3;
		gp.obj.get(3).screenY = gp.tileSize * 17;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(4).screenX = gp.tileSize * 17;
		gp.obj.get(4).screenY = gp.tileSize * 10;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(5).screenX = gp.tileSize * 17;
		gp.obj.get(5).screenY = gp.tileSize * 12 ;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(6).screenX = gp.tileSize * 17;
		gp.obj.get(6).screenY = gp.tileSize * 15 ;
		
		gp.obj.add(new OBJ_Chair("leftchair", gp));
		gp.obj.get(7).screenX = gp.tileSize * 17;
		gp.obj.get(7).screenY = gp.tileSize * 17 ;
		
		gp.obj.add(new OBJ_Table("verticaltable", gp));
		gp.obj.get(8).screenX = gp.tileSize * 4;
		gp.obj.get(8).screenY = gp.tileSize * 11;
		
		gp.obj.add(new OBJ_Table("verticaltable", gp));
		gp.obj.get(9).screenX = gp.tileSize * 4;
		gp.obj.get(9).screenY = gp.tileSize * 16;
		
		gp.obj.add(new OBJ_Table("verticaltable", gp));
		gp.obj.get(10).screenX = gp.tileSize * 18;
		gp.obj.get(10).screenY = gp.tileSize * 11;
		
		gp.obj.add(new OBJ_Table("verticaltable", gp));
		gp.obj.get(11).screenX = gp.tileSize * 18;
		gp.obj.get(11).screenY = gp.tileSize * 16;
		
		gp.customers[0] = new Customer(gp);
		gp.customers[0].screenX = gp.tileSize + 3;
		gp.customers[0].screenY = gp.tileSize * 5;
		gp.customers[0].name = gp.customers[0].name + "0";
		
		gp.customers[1] = new Customer(gp);
		gp.customers[1].screenX = gp.tileSize * 10;
		gp.customers[1].screenY = gp.tileSize * 5;
		gp.customers[1].name = gp.customers[0].name + "1";
		
		gp.chefs[0] = new Chef(gp);
		gp.chefs[0].screenX = gp.tileSize * 7;
		gp.chefs[0].screenY = gp.tileSize * 1;
		gp.chefs[0].name = gp.chefs[0].name + "0";
		
		gp.allDishes.add(new OBJ_Steak("Steak", gp));
		gp.allDishes.get(0).screenX = gp.tileSize * 15;
		gp.allDishes.get(0).screenY = gp.tileSize * 2;
		
		gp.panel.add(new OBJ_InProcess("in", gp));
		gp.panel.get(0).screenX = gp.tileSize * 13;
		gp.panel.get(0).screenY = 0;
		
		gp.panel.add(new OBJ_Order("order", gp));
		gp.panel.get(1).screenX = gp.tileSize * 1;
		gp.panel.get(1).screenY = 0;
		}
}
