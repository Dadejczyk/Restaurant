import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem{
        String name;
        double price;

        String category;

        public MenuItem(String name, double price, String category){
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }
class Appetizer extends MenuItem {
        public Appetizer(String name, double price) {
            super(name, price, "Appetizer");
        }
    }

class MainCourse extends MenuItem{
        public MainCourse(String name, double price){
            super(name, price , "MainCourse");
        }
    }

class Dessert extends MenuItem{
    public Dessert(String name, double price){
        super(name,price, "Dessert");
        }
    }
abstract class Restaurant{
    List<MenuItem> menuItems = new ArrayList<>();
    abstract void displayMenu();

    void addMenuItem(MenuItem item){
        menuItems.add(item);
    }

    void applyPromotions(){
        for (MenuItem item : menuItems){
            if (item instanceof Appetizer){
                item.price *= 0.7;
                item.name += "(Discount 30%)";
            }
        }
    }

    void takeOrder(){
        Scanner scanner = new Scanner(System.in);
        applyPromotions();
        displayMenu();
        System.out.println("Chose the nr from menu to take order(Press 0 to EXIT): ");

        int choice;
        do {
            choice = scanner.nextInt();
            if (choice > 0 && choice <= menuItems.size()){
                MenuItem item = menuItems.get(choice - 1);
                System.out.println("Ordered: " + item.name + " for " + item.price);
            } else
                System.out.println("Select correct item!");
        } while (choice != 0);
        System.out.println("THX");
    }


    }

public class Main {
    public static void main(String[] args) {
        Restaurant myRestaurant = new Restaurant() {
            @Override
            void displayMenu() {
                int index = 1;
                System.out.println("Menu: ");
                for (MenuItem item : menuItems){
                    System.out.println(index++ + ". " + item.name + "(" + item.category + ")" + " - " + item.price + "zł");
                }
            }
        };




        myRestaurant.addMenuItem(new Appetizer("Bread", 20.99));
        myRestaurant.addMenuItem(new MainCourse("Spaghetti  Carbonara", 40.99));
        myRestaurant.addMenuItem(new Dessert("Cake", 15.99));


        myRestaurant.takeOrder();
    }
}