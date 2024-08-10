/**
 * This is the class that manages all the clients
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

public class ClientManager {  //communicates with the text-file
    private ArrayList<Clients> client = new ArrayList<>();
    private final File file = new File("ClientsV2.txt");     //creating a file object
    public ClientManager (){ load();}

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
                int numBooks = Integer.parseInt(scanFile.next());
                ArrayList<String> clientBooks = new ArrayList<>();
                for(int i = 0; i < numBooks; i++) {
                    clientBooks.add(scanFile.next());                       //add all books to client's inventory
                }
                Clients record = new Clients(name, numBooks, clientBooks);  //create client object
                client.add(record);
            }
            scanFile.close();
        }
        catch (IOException e) {
            out.println("File cannot be accessed??");
        }

    }

    /**
     * saves the changed data back into the file
     */
    public void save (){ //saves all added entries back to file
        try{
            FileWriter fw = new FileWriter(file, false); //true adds to the file, while false erases it all
            PrintWriter pw = new PrintWriter(fw);


            for (Clients clients : client) {
                ArrayList<String> borrowedBooks = new ArrayList<>();
                borrowedBooks.addAll(clients.getBorrowedBooks());

                pw.print("/" + clients.getName() + "/" + clients.getNumBooks());
                for (String borrowedBook : borrowedBooks) {
                    pw.print("/" + borrowedBook);
                }

            }

            pw.close();                 //closes PrintWriter and copies FileWriter to text file (overwrite)
        }
        catch (IOException e) {
            out.println("File cannot be accessed");
        }

    }

    /**
     * adds a new client into the list of clients
     * @param name, String
     * @param num, int
     * @param bookNames, ArrayList<String>
     */
    public void add(String name, int num, ArrayList<String> bookNames){ //adds value to the list
        Clients adding = new Clients(name, num, bookNames);
        client.add(adding);
    }

    /**
     * prints all the clients in the client list
     */
    public void printClients() {
        for (Clients clients : client) {
            System.out.println("Name: " + clients.getName());
            System.out.println("Number of Books Borrowed: " + clients.getNumBooks());
            for (int j = 0; j < clients.getBorrowedBooks().size(); j++) {
                System.out.println("\t" + clients.getBorrowedBooks().get(j));
            }
            System.out.println(" ");
        }
    }

    /**
     * removes a client given their name, their books get removed from them and back into the library
     * @param name, String
     * @return ArrayList<String>
     */
    public ArrayList<String> removeAClient(String name) {
        ArrayList<String> returnedBooks = new ArrayList<>();
        for(int i = 0; i < client.size(); i++) {
            if(client.get(i).getName().equalsIgnoreCase(name)) {
                for(int j = 0; j < client.get(i).getNumBooks(); j++) {
                    returnedBooks.add(client.get(i).removeABookFromClient());
                }

                client.remove(i);
                i--;
            }
        }
        return returnedBooks;
    }
    public ArrayList<Clients> getClient() {
        return client;
    }


}
