/**
 * This is the main class used to access the database
 *
 * @author  Jenny Liao
 * @version 5.0
 * @since   2023-10-5
 */

//import libraries
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    enum Users{     //enum for the types of users
        CLIENT, LIBRARIAN, MANAGER
    }
    static Library library = new Library();
    static ClientManager cM = new ClientManager();
    static BudgetHandler bH = new BudgetHandler();


    public static void main(String[] args) {

        Users person = Users.CLIENT;                            //user is automatically set as a client
        String response = "";
        Scanner user = new Scanner(System.in);

        //determining who the client is
        System.out.println("Who is the user: client, librarian or manager?");
        response = user.nextLine();
        switch (response) {
            case "client" ->                          //can access the library of books
                    System.out.println("Welcome user");
            case "librarian" -> {                  //can add book/remove book + see the client and book information
                System.out.println("Enter a password");
                response = user.nextLine();
                if (response.equals("12345")) {                      //requires a password
                    person = Users.LIBRARIAN;
                    System.out.println("Welcome librarian");

                } else {
                    person = Users.CLIENT;                          //if incorrect, user switches to client
                    System.out.println("Incorrect password... switching to limited access: Welcome Client");
                }
            }
            case "manager" -> {                    //manages the budget
                System.out.println("Enter a password");
                response = user.nextLine();
                if (response.equals("6789")) {                       //requires a password
                    person = Users.MANAGER;
                    System.out.println("Welcome manager");
                } else {
                    person = Users.CLIENT;
                    System.out.println("Incorrect password... switching to limited access: Welcome Client");
                }
            }
            default -> {
                System.out.println("Invalid option, exiting program");
                response = "exit";                                  //if user type does not exist, exits program
            }
        }

        while (!response.equalsIgnoreCase("exit")) {                    //while the response is not exit
            System.out.println("Please enter what you want to do with the Library:");
            if(person == Users.CLIENT) {
                System.out.println("\t Print Books - shows all books' details");
                System.out.println("\t get Description - shows the description of a book given its name");
                System.out.println("\t Search for book - finds book and shows available quantity");
                System.out.println("\t Search for author - shows all books with a given author");
                System.out.println("\t Search genre - shows all books given a certain genre");
                response = user.nextLine();
                if (response.equalsIgnoreCase("print Books")) {
                    System.out.println("Printing books...");
                    library.printBooks();                       //prints all the book information of the library
                    System.out.println(" ");
                }
                else if(response.equalsIgnoreCase("get Description")) {
                    System.out.println("getting description...");
                    getDescription(user);
                    System.out.println(" ");

                }
                else if(response.equalsIgnoreCase("search Genre")) {
                    System.out.println("searching for genre...");
                    searchGenre(user);
                    System.out.println(" ");

                }
                else if(response.equalsIgnoreCase("search for Book")) {
                    System.out.println("finding available quantity...");
                    searchBook(user);
                    System.out.println(" ");

                }
                else if(response.equalsIgnoreCase("search for Author")) {
                    System.out.println("finding books written...");
                    searchAuthor(user);
                    System.out.println(" ");

                }
                else {
                    System.out.println("I don't understand, please try again");
                    System.out.println(" ");
                }
            }
            else if(person == Users.LIBRARIAN) {                //librarian access
                System.out.println("\t Print Books - prints all books' information");
                System.out.println("\t Print Clients - prints all clients' details");
                System.out.println("\t Add Client - add a client to the system");
                System.out.println("\t Remove Client - remove a client from the system");
                System.out.println("\t Add Book - add a book to the library");
                System.out.println("\t Remove Book - remove a book from the library");
                System.out.println("\t Add to Client - add a book to a client");
                System.out.println("\t Remove from Client - remove a book from a client");
                System.out.println("\t Set Description - change the description of a book");
                System.out.println("\t Search for Client - search for a specific client");

                response = user.nextLine();
                if (response.equalsIgnoreCase("add Book")) {
                    System.out.println("Adding book...");
                    addBook(user);                              //can add new or old book to library
                } else if (response.equalsIgnoreCase("add Client")) {
                    System.out.println("Adding client...");
                    addClient(user);                            //can add new client to library
                } else if (response.equalsIgnoreCase("print Clients")) {
                    System.out.println("Printing clients...");
                    cM.printClients();                          //can print all client's information
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("print Books")) {
                    System.out.println("Printing books...");
                    library.printBooks();                       //can print all book information
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("remove Book")) {
                    System.out.println("Removing books...");
                    removeBook(user);                           //can remove book from library (i.e destroyed)
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("remove Client")) {
                    System.out.println("Removing clients...");
                    removeClient(user);                         //can remove a client
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("add to Client")) { //edit these two
                    System.out.println("adding book to client...");
                    addBookClient(user);                        //can add a book to client (i.e. borrowed books)
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("remove from Client")) {
                    System.out.println("Removing book from client...");
                    removeBookClient(user);                     //can remove a book from client (i.e. returned books)
                    System.out.println(" ");
                } else if (response.equalsIgnoreCase("set Description")) {
                    System.out.println("rewrite a description for a book...");
                    setDescription(user);                       //can write a description for a book
                    System.out.println(" ");
                }
                 else if (response.equalsIgnoreCase("search for Client")) {
                    System.out.println("searching...");
                    searchClient(user);                                            //can search for a specific client
                    System.out.println(" ");
                }
                else {
                    System.out.println("I don't understand, please try again");
                    System.out.println(" ");
                }
            }
            else if(person == Users.MANAGER) {
                System.out.println("\t Print Budget History - see all the history of transactions");
                System.out.println("\t add Budget - manually add money into available money");
                System.out.println("\t remove Budget - manually remove money from available money");
                System.out.println("\t edit Budget - manually set available money to a value");
                response = user.nextLine();
                if (response.equalsIgnoreCase("print Budget History")) {
                    System.out.println("Printing history...");
                    bH.printEntries();                          //can see the history of transactions
                    System.out.println(" ");
                }
                else if (response.equalsIgnoreCase("Add Budget")) {
                    System.out.println("adding money...");
                    addMoney(user);                             //can add money to the library's budget
                    System.out.println(" ");
                }
                else if (response.equalsIgnoreCase("Remove Budget")) {
                    System.out.println("removing money...");
                    removeMoney(user);                          //can remove money from the library's budget
                    System.out.println(" ");
                }
                else if (response.equalsIgnoreCase("Edit Budget")) {
                    System.out.println("setting budget...");
                    setMoney(user);                             //can directly alter budget
                    System.out.println(" ");
                }
                else {
                    System.out.println("I don't understand, please try again");
                    System.out.println(" ");
                }
            }
        }

        System.out.println("exiting program :)");
        //save and copy back all changes into the text files
        library.save();
        cM.save();
        bH.save();
    }

    /**
     *  This method adds a new Client and the books they have borrowed to the list of clients managed by the Client Manager.
     *  It will only add books to the Client if it exists and is available in the library, and subtracts it from the quantity available
     * @param input, Input Scanner
     */
    public static void addClient(Scanner input) {
        System.out.println("What is the client name, number of books borrowed and which books? If you enter an invalid book, it will not be counted...");
        String name = input.nextLine();
        int numBooks = input.nextInt();
        ArrayList<String> bookNames = new ArrayList<>();
        int tempNumBooks = 0;

        for (int i = 0; i < numBooks + 1; i++) {
            String tempBook = input.nextLine();
            for (int j = 0; j < library.getBooks().size(); j++) {
                if (tempBook.equalsIgnoreCase(library.getBooks().get(j).getName()) && library.getBooks().get(j).getQty() >= 1) {     //Only adds book to client if it is available in the library
                    bookNames.add(tempBook);
                    library.getBooks().get(j).removeQty(1);                 //remove books they borrowed from library, add to their own collection
                    System.out.println(tempBook + " was added");
                    tempNumBooks++;
                }
            }
        }
        cM.add(name, tempNumBooks, bookNames);                                  //create client object
        System.out.println("your client has been logged");
        System.out.println(" ");
    }       //adds a Client to list
    /**
     * This adds a book to the library
     * If the book already exists, it will add to the library's available quantity
     * if not, it will log in a new book to the library
     * @param input, Scanner input
     */
    public static void addBook(Scanner input) {
        System.out.println("What is the book name, author's name, Book ID, description, genre, its price and its qty");
        String name = input.nextLine();
        String author = input.nextLine();
        int ID = input.nextInt();
        input.nextLine();
        String genre = input.nextLine();
        String description = input.nextLine();
        double price = input.nextDouble();
        int quantity = input.nextInt();
        input.nextLine();
        boolean alreadyExist = library.checkExist(ID, quantity);      //call method which checks if it already exists and adds to the quantity if it does
        bH.addEntry(name, price, quantity);                           //adds to the cost taken off of budget from buying a book
        if (!alreadyExist) {
            library.add(name, author, ID, genre, description, price, quantity);
            System.out.println("your book has been logged");
            System.out.println(" ");
        } else {
            System.out.println("Since this book exists, we have added one to our stock");
            System.out.println(" ");
        }

    }         //buys a book and adds it to the library
    /**
     * This removes a client from the list of clients only if the client exists in the list
     * It also returns the borrowed books held by the client back to the library
     * @param input, Scanner input
     */
    public static void removeClient(Scanner input) {
        System.out.println("Enter the name of the client you would like to remove");
        if (input.hasNextLine()) {
            ArrayList<String> returned = new ArrayList<>();
            String name = input.nextLine();

            returned = cM.removeAClient(name);          //removes a client with the given name if the client is actually registered, if not nothing happens
            for (String s : returned) {
                for (int j = 0; j < library.getBooks().size(); j++) {
                    if (s.equalsIgnoreCase(library.getBooks().get(j).getName())) {
                        library.addBookQty(s);    //adds back to the quantity of available books in the library from the Client's inventory
                    }
                }

            }
            System.out.println("Removing " + name);
        } else {
            System.out.println("Please enter a valid name");
        }
    }    //removes a client from client list
    /**
     * This remove a book from the library (for example a lost or destroyed book that is unavailable)
     * It will only remove book until it reaches a quantity of 0.
     * @param input: Scanner input
     */
    public static void removeBook(Scanner input) {
        System.out.println("Enter the ID or name of the book you would like to remove and the number of books");
        if (input.hasNextInt()) {
            int removed = input.nextInt();
            int q = input.nextInt();
            input.nextLine();
            library.removeABook(removed, q);                //removes book from library based off of its ID and quantity
            System.out.println("Removing book with ID: " + removed);

        } else if (input.hasNextLine()) {
            String removed = input.nextLine();
            int q = input.nextInt();
            library.removeABook(removed, q);                //removes book from library based off of its name and quantity
            System.out.println("Removing book with name: " + removed);
        }
    }       //removes a book from the library
    /**
     * This adds a book to the Client (as in a borrowed Book) only if it exists and is available (more than or equal to 1 copy available)
     * It removes the borrowed books from the library
     * @param input, Scanner input
     */
    public static void addBookClient(Scanner input) {
        System.out.println("Name the book you'd like to add and the client");
        String name = input.nextLine();
        String clientName = input.nextLine();
        //check if book exists in library:
        boolean bookExist = false;
        int tempBookNum = 0;
        for (int j = 0; j < library.getBooks().size(); j++) {
            if (name.equalsIgnoreCase(library.getBooks().get(j).getName()) && library.getBooks().get(j).getQty() >= 1) {
                bookExist = true;
                tempBookNum = j;
                break;
            }

        }
        for (int i = 0; i < cM.getClient().size(); i++) {
            if (clientName.equalsIgnoreCase(cM.getClient().get(i).getName()) && bookExist) {
                cM.getClient().get(i).addBookToClient(name);
                library.getBooks().get(tempBookNum).removeQty(1);                   //removes the borrowed book from library inventory
                System.out.println("Successfully added");
                break;
            }
        }

    }
    /**
     * This removes a book from a client (as in a returned book) if it is found in the client's inventory
     * It adds the book back to the library
     * @param input, Scanner input
     */
    public static void removeBookClient(Scanner input) {
        System.out.println("Name the book you'd like to remove and the client");
        String name = input.nextLine();
        String clientName = input.nextLine();
        //check if book exists in client inventory:
        boolean bookExist = false;
        for (int j = 0; j < cM.getClient().size(); j++) {        //iterating through number of clients
            for (int i = 0; i < cM.getClient().get(j).getBorrowedBooks().size(); i++) {       //iterating through the book array of each client
                if (name.equalsIgnoreCase(cM.getClient().get(j).getBorrowedBooks().get(i))) {
                    bookExist = true;
                    break;
                }
            }
        }

        for (int i = 0; i < cM.getClient().size(); i++) {
            if (clientName.equalsIgnoreCase(cM.getClient().get(i).getName()) && bookExist) {
                cM.getClient().get(i).removeABookFromClient(name);
                library.addBookQty(name);    //add qty to library
                System.out.println("Successfully removed");
                break;
            }
        }
    }
    /**
     * This allows the manager to add money into the library's budget
     * @param input, Scanner input
     */
    public static void addMoney(Scanner input) {
        System.out.println("Please enter an amount");
        double moneyAdded = input.nextDouble(); input.nextLine();
        bH.addEntry("added money", 0, 0);
        double currentBudget = bH.addBudget(moneyAdded);
        System.out.println("current budget: " + currentBudget);
    }
    /**
     * This allows the manager to remove money from the budget
     * @param input, Scanner input
     */
    public static void removeMoney(Scanner input) {
        System.out.println("Please enter an amount");
        double moneyAdded = input.nextDouble(); input.nextLine();
        bH.addEntry("removed money", 0, 0);
        double currentBudget = bH.subtractBudget(moneyAdded);
        System.out.println("current budget: " + currentBudget);
        if(currentBudget < 0) {
            System.out.println("Warning, you are in debt!");
        }

    }
    /**
     * This allows the manager to manually set the amount for the library's budget
     * @param input, Scanner input
     */
    public static void setMoney(Scanner input) {
        System.out.println("Please enter an amount");
        int money = input.nextInt(); input.nextLine();
        bH.addEntry("setting money", 0, 0);
        bH.setBudget(money);
        System.out.println("Your budget is now: " + money);
    }
    /**
     * Changes the description of a book
     * @param input, Scanner input
     */
    public static void setDescription(Scanner input) {      //not working
        System.out.println("Enter the book name you'd like to rewrite and the description ");
        String bookName = input.nextLine();
        String newDescription = input.nextLine();
        library.changeDescription(bookName, newDescription);
        System.out.println("description set");
    }
    /**
     * Searches for a book given its name and gives its availability
     * @param input, Scanner input
     */
    public static void searchBook(Scanner input) {
        System.out.println("Which book are you looking for?");
        String bookName  = input.nextLine();
        int numAvailable = 0;
        for(int i = 0; i < library.getBooks().size(); i++) {
            if(bookName.equalsIgnoreCase(library.getBooks().get(i).getName())) {
                System.out.println("For: " + bookName);
                System.out.println("\tThe author: " + library.getBooks().get(i).getAuthor());
                System.out.println("\tThe availability for the book: " + bookName + " is " + library.getBooks().get(i).getQty());
                System.out.println("\tThe description is: " + library.getBooks().get(i).getDescription());
                System.out.println("\tThe genre is: " + library.getBooks().get(i).getGenre());

            }
        }

    }
    /**
     * Searches for the books written by a certain author
     * @param input, Scanner input
     */
    public static void searchAuthor(Scanner input) {
        System.out.println("Which author are you looking for?");
        String nameAuthor = input.nextLine();
        int numFound = 0;
        for(int i = 0; i < library.getBooks().size(); i++) {
            if(nameAuthor.equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                System.out.println("Book found: " + library.getBooks().get(i).getName());
                numFound++;
            }
        }
        System.out.println("we found: " + numFound + " result(s)");
    }
    /**
     * Gets the description of a book from its name
     * @param input, Scanner input
     */
    public static void getDescription(Scanner input) {
        System.out.println("For which book?");
        String bookName = input.nextLine();
        boolean bookFound = false;
        for(int i = 0; i < library.getBooks().size(); i++) {
            if(bookName.equalsIgnoreCase(library.getBooks().get(i).getName())) {
                System.out.println("description is: " + library.getBooks().get(i).getDescription());
                bookFound = true;
            }
        }
        if (!bookFound) {
            System.out.println("Book not found");
        }
    }
    /**
     * Searches for all the books of a certain genre
     * @param input, Scanner input
     */
    public static void searchGenre(Scanner input) {
        System.out.println("which genre?");
        String findGenre = input.nextLine();
        library.searchBooksGenre(findGenre);
    }

    /**
     * searches for a specific client given a name
     * @param input, Scanner
     */
    public static void searchClient(Scanner input) {
        System.out.println("who?");
        String findPerson = input.nextLine();
        boolean found = false;
        for(int i = 0; i < cM.getClient().size(); i++) {
            if(findPerson.equalsIgnoreCase(cM.getClient().get(i).getName())) {
                found = true;
                System.out.println("Found: " + findPerson);
                System.out.println("Number of Books Borrowed: " + cM.getClient().get(i).getNumBooks());
                for(int j = 0; j < cM.getClient().get(i).getNumBooks(); j++) {
                    System.out.println("\t" + cM.getClient().get(i).getBorrowedBooks().get(j));
                }
            }
        }
        if(!found) {
            System.out.println("user not found");
        }
    }
}