package testGame;

public class Dishes extends Menu {
    private String name;
    private String orderedByCustomer; 
    private boolean inProgress; 
    private boolean completed; 
    

    public Dishes(String name) {
        this.name = name;
        this.orderedByCustomer = "";
        this.inProgress = false;
        this.completed = false;
    }

    public void orderedByCustomer(Customer customer) {
        orderedByCustomer = customer.getName();
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

    public String isOrderedByCustomer() {
        return orderedByCustomer;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void isCompleted(Dishes d, int x) {
		long lastCheck = System.currentTimeMillis();
		long current = System.currentTimeMillis();
		int timeToCook = 0;
	    	switch(x){
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
	    	}
	    
	    	System.out.println(timeToCook);
	    	while(true) {
	    		if (System.currentTimeMillis() - lastCheck > 1000 * timeToCook) {
		    		System.out.println(d.getName() + " is completed");
		    		break;
		    	}
	    	}
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
   public static void main(String[] args) {
//	Dishes dis = new Dishes("Dragon Fruit");
//	dis.cookDishes(dis);
}
}

