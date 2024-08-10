/**
 * This is the Budget Handler used by the manager to track the finances spent on books in the library
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class BudgetHandler {  //communicates with the text-file
    private ArrayList<Entry> entries = new ArrayList<>();
    private final File file = new File("BudgetHandler.txt");     //creating a file object

    public BudgetHandler() {
        load();
    }

    /**
     * loads data from the file and makes them into clients
     */
    private void load() { // all entries from file I/O
        try {
            Scanner scanFile = new Scanner(file);
            scanFile.useDelimiter("/");     //add new value, then print previous values
            //load all entries here
            //read in first line, read in next line...
            while (scanFile.hasNext()) {
                double cost = Double.parseDouble(scanFile.next());
                String name = scanFile.next();
                double price = Double.parseDouble(scanFile.next());
                int qty = Integer.parseInt(scanFile.next());
                double budget = Double.parseDouble(scanFile.next());

                Entry record = new Entry(name, price, qty, budget);
                entries.add(record);
            }
            scanFile.close();
        } catch (IOException e) {
            out.println("File cannot be accessed??");
        }

    }
    /**
     * saves the changed data back into the file
     */
    public void save() { //saves all added entries back to file
        try {
            FileWriter fw = new FileWriter(file, false); //true adds to the file, while false erases it all
            PrintWriter pw = new PrintWriter(fw);
            for (Entry entry : entries) {
                pw.print("/" + entry.getCost() + "/" + entry.getName() + "/" + entry.getPrice() + "/" + entry.getQty() + "/" + entry.getBudget());
            }

            pw.close();                 //closes PrintWriter and copies FileWriter to text file (overwrite)
        } catch (IOException e) {
            out.println("File cannot be accessed");
        }

    }

    /**
     * adds an entry (transaction) into the Budget History.
     * Records if money is added, removed, or spent on books
     * @param name, String
     * @param price, double
     * @param quantity, int
     */
    public void addEntry(String name, double price, int quantity) { //adds value to the list
        double prevBudget = entries.get(entries.size() - 1).getBudget();
        Entry added = new Entry(name, price, quantity, prevBudget);
        added.buy();

        entries.add(added);
    }

    public void setBudget(double value) {
        entries.get(entries.size() - 1).setBudget(value);       //sets budget of last entry/transaction
    }

    public double addBudget(double value) {
        entries.get(entries.size() - 1).addBudget(value);
        return entries.get(entries.size()-1).getBudget();
    }

    public double subtractBudget(double value) {
        entries.get(entries.size() - 1).subtractBudget(value);
        return entries.get(entries.size()-1).getBudget();
    }

    /**
     * prints all of the transaction history
     */
    public void printEntries() {
        for (Entry entry : entries) {
            System.out.println("Name: " + entry.getName());
            System.out.println("Price: " + entry.getPrice());
            System.out.println("Qty: " + entry.getQty());
            System.out.println("Cost: " + entry.getCost());
            System.out.println("Remaining Budget: " + entry.getBudget());
            System.out.println(" ");


        }
    }
}


