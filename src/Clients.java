/**
 * Stores all the information about a client
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
import java.util.ArrayList;

public class Clients {
    private final String name;
    private int numBooks;
    private ArrayList<String> books;
    public Clients (String name, int numBooks, ArrayList<String> bookNames) {
        this.name = name;
        this.numBooks = numBooks;
        books = bookNames;
    }

    public String getName() {
        return name;
    }
    public int getNumBooks() {
        return numBooks;
    }

    public ArrayList<String> getBorrowedBooks() {
        return books;
    }
    /**
     * adds a book to the client's inventory
     */
    public void addBookToClient (String bookName) {
        books.add(bookName);
        numBooks++;
    }

    /**
     * this removes a book from a client (returned books)
     * @param bookName, String
     */
    public void removeABookFromClient(String bookName) {
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).equalsIgnoreCase(bookName)) {
                books.remove(i);
                numBooks--;
                break;
            }
        }
    }

    /**
     * This is a method used when the client is removed, all their books are automatically returned
     * @return String
     */
    public String removeABookFromClient() {
        String temp = books.get(0);
        books.remove(0);
        return temp;
    }

}
