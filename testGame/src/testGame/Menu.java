package testGame;

import java.util.HashMap;

public class Menu {
    private HashMap<String, FoodItem> appetizers; 
    private HashMap<String, FoodItem> mainCourses; 
    private HashMap<String, FoodItem> desserts;
    private HashMap<String, FoodItem> drinks; 

    public Menu() {
        this.appetizers = new HashMap<>();
        this.mainCourses = new HashMap<>();
        this.desserts = new HashMap<>();
        this.drinks = new HashMap<>();
        initializeMenu();
    }

    public HashMap<String, FoodItem> getAppetizers() {
		return appetizers;
	}

	public void setAppetizers(HashMap<String, FoodItem> appetizers) {
		this.appetizers = appetizers;
	}

	public HashMap<String, FoodItem> getMainCourses() {
		return mainCourses;
	}

	public void setMainCourses(HashMap<String, FoodItem> mainCourses) {
		this.mainCourses = mainCourses;
	}

	public HashMap<String, FoodItem> getDesserts() {
		return desserts;
	}

	public void setDesserts(HashMap<String, FoodItem> desserts) {
		this.desserts = desserts;
	}

	public HashMap<String, FoodItem> getDrinks() {
		return drinks;
	}

	public void setDrinks(HashMap<String, FoodItem> drinks) {
		this.drinks = drinks;
	}
	public int getSizeAppteizers() {
		return appetizers.size();
	}
	public int getSizemainCourses() {
		return mainCourses.size();
	}
	public int getSizeDesserts() {
		return desserts.size();
	}
	public int getSizeDrinks() {
		return drinks.size();
	}

	private void initializeMenu() {
        appetizers.put("Cherry", new FoodItem(1, 5)); 
        appetizers.put("Dargon Fruit", new FoodItem(1, 3));  
        
        mainCourses.put("Steak", new FoodItem(20, 20)); 
        mainCourses.put("Chiken", new FoodItem(15, 10)); 
        mainCourses.put("Bacon", new FoodItem(10, 5)); 
        
        desserts.put("Tart", new FoodItem(5, 4)); 
        desserts.put("Waffles", new FoodItem(5, 4)); 
        
        drinks.put("Wine", new FoodItem(1, 20)); 
        drinks.put("Melon Water", new FoodItem(2, 2)); 
    }

 

}

class FoodItem {
    private int preparationTime; 
    private double price; 

    public FoodItem(int preparationTime, double price) {
        this.preparationTime = preparationTime;
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Preparation Time: " + preparationTime + " minutes, Price: $" + price;
    }
    
}
