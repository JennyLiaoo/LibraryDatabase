/**
 * This is the library that allows access to all the books
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class Library {  //can save and load items from the library: communicates with the text-file
    private ArrayList<Book> books = new ArrayList<>();
    private final File file = new File("BookLibrary.txt");     //creating a file object
    public Library (){ load();}

    /**
     * loads data from the file and makes them into clients
     */
    private void load (){ // all entries from file I/O
        try{
            Scanner scanFile = new Scanner(file);
            scanFile.useDelimiter("/");
            //load all entries here
            //read in first line, read in next line...
            while(scanFile.hasNext()) {
                String name = scanFile.next();
                String author = scanFile.next();
                int ID = Integer.parseInt(scanFile.next());
                String description = scanFile.next();
                String genre = scanFile.next();
                double price = Double.parseDouble(scanFile.next());
                int qty = Integer.parseInt(scanFile.next());

                Book record = new Book(name, author, ID, genre, description, price, qty);   //creates the book objects
                books.add(record);
            }
            scanFile.close();
        }
        catch (IOException e) {
            out.println("File cannot be accessed??");
        }
        catch(NumberFormatException nfe) {
            out.println("Number format Exception");
        }

    }
    /**
     * saves the changed data back into the file
     */
    public void save (){ //saves all added entries back to file
        try{
            FileWriter fw = new FileWriter(file, false); //true adds to the file, while false erases it all
            PrintWriter pw = new PrintWriter(fw);
            for (Book book : books) {
                pw.print("/" + book.getName() + "/" + book.getAuthor() + "/" + book.getID() + "/" + book.getDescription() + "/" + book.getGenre() + "/" + book.getPrice() + "/" + book.getQty());
            }

            pw.close();                 //closes PrintWriter and copies FileWriter to text file (overwrite)
        }
        catch (IOException e) {
            out.println("File cannot be accessed");
        }

    }

    /**
     * Adds a new book to the library
     */
    public void add(String name, String author, int ID, String genre, String description, double price, int quantity){ //adds value to the list
        Book adding = new Book(name, author, ID, genre, description, price, quantity);
        books.add(adding);
    }
    public void printBooks() {
        for (Book book : books) {
            System.out.println("Name: " + book.getName());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ID: " + book.getID());
            System.out.println("Description: " + book.getDescription());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Price: " + book.getPrice());
            System.out.println("Quantity available: " + book.getQty());
            System.out.println(" ");
        }
    }

    /**
     * check if the book exists in the library
     * @param identityNum, int
     * @param q, int
     * @return boolean
     */
    public boolean checkExist(int identityNum, int q) {
        for (Book book : books) {
            if (identityNum == book.getID()) {
                book.addQty(q);
                return true;
            }
        }
        return false;
    }

    /**
     * adds to the availability of a book
     * @param name, String
     */
    public void addBookQty(String name) {
        for (Book book : books) {
            if (name.equalsIgnoreCase(book.getName())) {
                book.addQty(1);
            }
        }
    }

    /**
     * removes a book from the library, cannot go past 0
     * @param name, String
     * @param q, int
     */
    public void removeABook(String name, int q) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                book.removeQty(q);
                if (book.getQty() < 0) {
                    book.setQty(0);
                }
            }
        }
    }

    /**
     * removes a book from the library but cannot be less than 0
     * @param ID, int
     * @param q, int
     */
    public void removeABook(int ID, int q) {
        for (Book book : books) {
            if (book.getID() == ID) {
                book.removeQty(q);
                if (book.getQty() < 0) {
                    book.setQty(0);
                }
            }
        }
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * changes the description of a certain book
     * @param nameBook, String
     * @param newDescription, String
     */
    public void changeDescription(String nameBook, String newDescription) {
        for (Book book : books) {
            if (nameBook.equalsIgnoreCase(book.getName())) {
                book.setDescription(newDescription);
            }
        }

    }

    /**
     * searchs for the books with given genre
     * @param findGenre, String
     */
    public void searchBooksGenre(String findGenre) {
        int found = 0;
        for (Book book : books) {
            if (findGenre.equalsIgnoreCase(book.getGenre())) {
                System.out.println("Book: " + book.getName() + " has genre " + findGenre);
                found++;
            }
        }
        System.out.println("We found: " + found + " results");
    }
}
