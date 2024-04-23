package entity;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entity.Customer;
import main.FoodImage;

public class Dishes extends Menu {
    private String name;
    private String orderedByCustomer; 
    public boolean inProgress; 
    public boolean completed; 
    public String type;
    public int needTime;
    FoodImage fi = new FoodImage();
    public BufferedImage image;

    public Dishes(String name) {
        this.name = name;
        this.orderedByCustomer = "";
        this.inProgress = false;
        this.completed = false;
    }

    public void orderedByCustomer(Customer customer) {
        orderedByCustomer = customer.name;
    }

    public void startPreparing() {
        inProgress = true;
        System.out.println("Preparing " + name + "...");
    }

    public void finishPreparing() {
        inProgress = false;
        completed = true;
        System.out.println(name + " is ready!");
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
		this.name = name;
	}

	public String isOrderedByCustomer() {
        return orderedByCustomer;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void isCompleted(Dishes d, int x) {
		long lastCheck = System.currentTimeMillis();
		
		int timeToCook = 0;
		
		if (d.getName() != "-1")
	    	{switch(x){
	    		case 0 :
	    			timeToCook = getAppetizers().get(d.getName()).getPreparationTime();
	    			break;
	    		case 1 :
	    			timeToCook = getMainCourses().get(d.getName()).getPreparationTime();
	    			break;
	    		case 2 :
	    			timeToCook = getDesserts().get(d.getName()).getPreparationTime();;
	    			break; 
	    		case 3 :
	    			timeToCook = getDrinks().get(d.getName()).getPreparationTime();
	    			break;
	    	}
	    
	    	System.out.println(timeToCook);
	    	
	    	while(true) {
	    		long current = System.currentTimeMillis();
	    		if ((current - lastCheck) > timeToCook * 1000) {
		    		System.out.println(d.getName() + " is completed");
		    		completed = true;
		    		break;
		    	}
		    		needTime = (int) ((lastCheck + timeToCook * 1000 - current)/1000);
	    	}
	    	}
		}
        
    public int isType(String name) {
    	switch (name) {
		case "Cherry": 
		case "Dargon Fruit":
			return 0;
		case "Steak":
		case "Chicken" :
		case "Bacon":
			return 1;
		case "Tart" :
		case "Waffles":
			return 2;
		case "Wine":
		case "Melon Water" :
			return 3;
		
    }
    	return -1;
    }

//    public boolean cookDishes(Dishes dish) {
//          
////            System.out.println("Dish name: " + dish.getName());
////            System.out.println("Ordered by customer: " + dish.isOrderedByCustomer());
////
////            System.out.println("Ordered by customer: " + dish.isOrderedByCustomer());
////
////            dish.startPreparing();
////
////            dish.finishPreparing();
////            if (is)
////            return true;
//    }
    public void LoadImageFood(String name) {
    	image = fi.loadImage(name);
    }
}

