/**
 * A book class that stores variables and information about a book
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
public class Book {
    //instance variables
    private final String name;
    private final String author;
    private final int ID;
    private final String genre;
    private String description;
    private double price;
    private int qty;

    //constructor
    public Book (String name, String author, int ID, String genre, String description, double price, int qty) {
        this.name = name;
        this.author = author;
        this.ID = ID;
        this.description = description;
        this.genre = genre;
        this.qty = qty;
        this.price = price;

    }

    public String getName() {
        return name;
    }public String getAuthor() {
        return author;
    }public int getID() {
        return ID;
    }public String getDescription() {
        return description;
    }public String getGenre() {
        return genre;
    }public double getPrice(){
        return price;
    }
    public int getQty() {
        return qty;
    }
    public void addQty(int q) {
        qty += q;
    }
    public void removeQty(int q) {
        qty -= q;
    }
    public void setQty(int q) {
        qty = q;
    }
    public void setDescription(String d) {
        description = d;
    }
}
