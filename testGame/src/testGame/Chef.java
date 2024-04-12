package testGame;

import java.util.ArrayList;

import main.GamePanel;

public class Chef {
    private String name;
    private ArrayList<Dishes> DishesesInProgress;

    public Chef(String name) {
        this.name = name;
        this.DishesesInProgress = new ArrayList<>();
    }
    
    public void receiveOrder(Customer customer) {
    	int max_order = 4;
    	ArrayList<String> dish = new ArrayList<String>();
    	Dishes d;
    	for (int i = 0; i < 4; i++) {
    		if (max_order <= 4) {
    			dish.add(customer.getName());
        		d = new Dishes((String)customer.getOrders().keySet().toArray()[i]);
               
        		System.out.println(name + " received an order for " + d.getName() + " from Customer: " + customer.getName());
        		 d.startPreparing();
        	     DishesesInProgress.add(d);
        	     max_order--;
        	    d.isCompleted(d, i) ;
        	     
        	     max_order++;
    		}
    	}
       
    }

    public void completeDishes(Dishes Dishes) {
        System.out.println(name + " has completed preparing " + Dishes.getName() + ".");
        DishesesInProgress.remove(Dishes);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Dishes> getDishesesInProgress() {
        return DishesesInProgress;
    }

   
}
