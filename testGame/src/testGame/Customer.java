package testGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Customer {
	public final static int CUSTOMER_MAX = 3;
    private String name;
    private LinkedHashMap<String, Float> orders; 
    private float bill = 0;
    public Customer(String name) {
        this.name = name;
        this.orders = new LinkedHashMap<String, Float>();
        placeOrders();
        
    }

    public void placeOrders() {
        Menu menu = new Menu();
        Random ran = new Random();
        bill = 0;
        
        // Create an ordered list of menu categories
        ArrayList<String> categoryOrder = new ArrayList<>();
        categoryOrder.add("appetizers");
        categoryOrder.add("mainCourses");
        categoryOrder.add("desserts");
        categoryOrder.add("drinks");

        // Iterate over the categories in the desired order
        for (String category : categoryOrder) {
            HashMap<String, FoodItem> chosenMenu = null;
            switch (category) {
                case "appetizers":
                    chosenMenu = menu.getAppetizers();
                    break;
                case "mainCourses":
                    chosenMenu = menu.getMainCourses();
                    break;
                case "desserts":
                    chosenMenu = menu.getDesserts();
                    break;
                case "drinks":
                    chosenMenu = menu.getDrinks();
                    break;
            }
            
            int index = ran.nextInt(chosenMenu.size());
            String dishName = (String) chosenMenu.keySet().toArray()[index];
            float price = (float) chosenMenu.get(dishName).getPrice();
            orders.put(dishName, price);
            bill += price;
        }
    }




    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void leave() {
        System.out.println(name + " is leaving.");
    }

    public String getName() {
        return name;
    }

    public Map<String, Float> getOrders() {
        return orders;
    }



    public static void main(String[] args) {
        Customer customer = new Customer("John");
        System.out.println("Customer's name: " + customer.getName());

        System.out.println("Customer's orders: " + customer.getOrders());
        System.out.println(customer.bill);

        customer.eat();
        customer.leave();
    }
}

