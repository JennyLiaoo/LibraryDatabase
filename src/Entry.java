/**
 * This is the class that helps document any changes in the budget
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
public class Entry {
    //instance variables
    private Double cost;
    private String name;
    private double price;
    private int qty;
    private double budget;

    //constructor
    public Entry (String name, double price, int qty, double budget) {  //gets previous budget
        this.price = price;
        this.qty = qty;
        this.name = name;
        cost = price*qty;
        this.budget = budget;
    }
    //setters and getters
    public double getBudget() {
        return budget;
    }

    public Double getCost() {
        return cost;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    /**
     * When buying (adding) a book, the budget will automatically calculate the cost and subtract it
     */
    public void buy() {
        budget -= price * qty;
    }
    //changes in budget
    public void setBudget(double value) {
        budget = value;
    }
    public void addBudget(double value) {
        budget += value;
    }
    public void subtractBudget(double value) {
        budget -= value;
    }

}
