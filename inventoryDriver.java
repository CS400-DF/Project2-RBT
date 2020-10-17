// --== CS400 File Header Information ==--
// Name: <Haoxuan Wei>
// Email: <hwei72@wisc.edu>
// Team: <DF>
// Role: <test engineer>
// TA: <Yelun>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class drives the Inventory Application
 * 
 * @author Haoxuan Wei
 */

public class inventoryDriver {

  private final static String WELCOME_MSG =
      "~~~~~~~~ WELCOME to the Inventory Application ~~~~~~~~";
  private final static String GOODBYE_MSG =
      "~~~~~~~~~~~ Thank you for using this application! ~~~~~~~~~~~";
  private final static String MENU = "\nCOMMAND MENU:\n"
      + "[L] Loads a text file of Student objects into the Covid-19 Tracker App\n"
      + "[P] Puts a single new Good into the Inventory App.\n"
      + "[G] Gets a good's information from the application.\n"
      + "[S] Return the amount of goods in the application.\n"
      + "[K] Check if a good exists in the application\n"
      + "[U] Check if a good exists in the application\n"
      + "[D] Check if a good exists in the application\n"
      + "[C] Clear all existing goods from the application.\n" + "[Q] Quit the application.\n"
      + "[H] Help (display this Menu).";

  /**
   * Processes and runs the user command line
   * 
   * @param command User's command string
   * @param ia Instance of inventory app class that Students are being added into
   * @return False if user wants to quit midway through a command and true otherwise
   */
  private static boolean processUserCommandLine(String command, InventoryApp ia,
      Scanner scanner) {
    String[] input = command.trim().split(" ");
    switch (input[0].toUpperCase()) {
      case "L": // load a Student text file into the application
        if (loadHelper(ia, scanner) == false) {
          return false;
        }
        break;

      case "U": // increase stock
        increaseStockHelper(ia, scanner);
        break;

      case "D": // Decrease stock
        lowerStockHelper(ia, scanner);
        break;

      case "P": // put a new single good into the application
        if (putHelper(ia, scanner) == false) {
          return false;
        } ;
        break;

      case "G": // get a Student's information if they are in the application
        if (getHelper(ia, scanner) == false) {
          return false;
        }
        break;

      case "S": // return the amount of students in the application
        System.out.println("There are " + ia.getNumGoods()
            + " students currently stored in this application.");
        break;

      case "K": // check if a Student exists in the application
        if (containsKeyHelper(ia, scanner) == false) {
          return false;
        }
        break;

      case "C": // clear all Students from the application
        ia.clearAllGoods();
        System.out.println("Cleared all students from this application.");
        break;

      case "H": // display the menu
        System.out.println(MENU);
        break;

      default: // command doesn't exist
        System.out.println("WARNING. Invalid command. Please enter H and refer to the menu.");
    }
    
    return true;
  }

  /**
   * Driver method for the inventory app application (reads and processes user command lines)
   */
  private static void driver() {
    // Initialize variables
    InventoryApp ia = new InventoryApp();
    Scanner scanner = new Scanner(System.in);
    String promptCommandLine = "\nENTER COMMAND: ";

    // initially print out the menu and command prompt
    System.out.println(MENU);
    System.out.print(promptCommandLine);
    
    // get users first input
    String line = scanner.nextLine().trim();  
    char c = line.charAt(0);
    
    while (Character.toUpperCase(c) != 'Q') {
      // parse and process the user command line
      if (processUserCommandLine(line, ia, scanner) == false) {
        break;
      }
      System.out.print(promptCommandLine);
      // read next user command line
      line = scanner.nextLine().trim();
      c = line.charAt(0);
    }

    // close the scanner
    scanner.close();
  }

  /**
   * Main method for the inventory driver application
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(WELCOME_MSG); // start the application
    driver(); // run the application
    System.out.println(GOODBYE_MSG); // end the application
  }

  private static void increaseStockHelper(InventoryApp ia,Scanner scanner)
  {
    boolean successOrNot = false;
    boolean successAmount = false;
    int barcode = 0;
    int amount = 0;
    while (successOrNot==false)
    {
      System.out.println("enter barcode of the good you would like to increase");
      barcode = scanner.nextInt();
      if (ia.containsGood(barcode) != true) {
        System.out.println("A good with this barcode does not exist in the system.");
      }
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      successOrNot = true;
    }
    while (successAmount==false)
    {
      System.out.println("enter amount of the good you would like to increase");
      amount = scanner.nextInt();
      successAmount = true;
    }
    ia.increaseStock(barcode,amount);
  }
  private static void lowerStockHelper(InventoryApp ia,Scanner scanner)
  {
    boolean successOrNot = false;
    boolean successAmount = false;
    int barcode = 0;
    int amount = 0;
    while (successOrNot==false)
    {
      System.out.println("enter barcode of the good you would like to decrease");
      barcode = scanner.nextInt();
      if (ia.containsGood(barcode) != true) {
        System.out.println("A good with this barcode does not exist in the system.");
      }
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      successOrNot = true;
    }
    while (successAmount==false)
    {
      System.out.println("enter amount of the good you would like to decrease");
      amount = scanner.nextInt();
      successAmount = true;
    }
    ia.lowerStock(barcode,amount);
  }
  /**
   * Checks if the user wants to quit at any point
   *
   * @param input User's input as a string
   * @return True if user enters "q" and false otherwise
   */
  private static boolean checkIfQuit(String input) {
    if (input.trim().equalsIgnoreCase("q")) {
      return true;
    }
    
    return false;
  }

  /**
   * Checks if the user has entered a valid barcode number
   * 
   * @param id User's ID number input as a int
   * @return True if user enters a valid barcode and false otherwise
   */
  private static boolean checkIfValidID(Integer id) {
    int length = String.valueOf(id).length();
    if (id != (int)id)
    {
      System.out.println("Invalid ID format. Good's barcode must be a single 9 digit integer.");
      return false;
    }
    if ( length!= 9) {
      System.out.println("Invalid ID format. Good's barcode must be a single 9 digit number.");
      return false;
    }
    
    return true;
  }
  private static boolean loadHelper(InventoryApp ia, Scanner scanner) {
    String fileName = null;
    System.out.print(
            "Please enter the name of the text file of Goods you would like to load into the application in the form <FileName>.txt : ");
    fileName = scanner.nextLine().trim();

    // check if user has entered 'q' to quit
    if (checkIfQuit(fileName)) {
      return false;
    }

    // Try to load file of Students into the system
    if (!(ia.load(fileName))) {
      System.out.println("File of students uncessessfully loaded into the application. Please hit [L] and try again.");
    }
    else {
      System.out.println("File of students successfully loaded into the application.");
    }

    return true;
  }
  /**
   * Helps with the functionality of adding a Good to the application.
   *
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean putHelper(InventoryApp ia, Scanner scanner) {
    boolean correctBarcodeInput = false;
    
    // keep polling user for valid barcode number
    while (correctBarcodeInput == false) {
      // initialize variables
      int barcode = 0;
      String name = null;
      double price = 0.0;
      int quantity = 0;
      
      System.out.print("Enter 9 digit barcode: ");
      barcode = scanner.nextInt();

      // check for correct barcode input
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      
      // check if good has already been entered into the system
      if (ia.containsGood(barcode) == true) {
        System.out.println("A good with that barcode already exists.");
      
      // else add the good into the system
      } else {
        System.out.print("Enter good's name: ");
        name = scanner.nextLine();
        
        // check if user entered 'q' to quit
        if (checkIfQuit(name)) { 
          return false;
        }
        
        boolean correctQuantityInput = false;
        
        // keep polling user for a valid quantity input
        while (correctQuantityInput == false) {
          System.out.print(
              "Enter good's quantity: ");
          quantity = scanner.nextInt();

          
          // check for correct quantity input
          if (quantity != (int)quantity)
          {
            System.out.println("Quantity invalid., only integers can be entered");
            continue;
          }
          correctQuantityInput = true;
        }
        
        boolean correctPriceInput = false;
        
        // keep polling user for a valid housingStatus input
        while (correctPriceInput == false) {
          System.out
              .print("Enter a double for price: ");
          price = scanner.nextDouble();

          // check for correct price input
          if (price != (double)price)
          {
            System.out.println("input must be a double value");
            continue;
          }
          correctPriceInput = true;
        }
        
        // add correctly formatted good to the application
        ia.addItem(barcode, name, price, quantity);
        System.out.println("New good successfully added.");
      }
      correctBarcodeInput = true;
    }
    
    return true;
  }

  /**
   * Assists with the functionality of getting a Good Object
   * 
   * @param ia Instance of the Inventory app class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean getHelper(InventoryApp ia, Scanner scanner) {
    boolean correctBarcode = false;
    int barcode = 0;
    
    // keep polling user for a valid ID number
    while (correctBarcode == false) {
      System.out.print("Enter 9 digit barcode: ");
      barcode = scanner.nextInt();

      // check for correct ID input
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      
      correctBarcode = true;
    }
    
    // try to get Student information
    try {
      ia.getGood(barcode);
    } catch (NoSuchElementException e) {
      System.out.println("There is no existing good with that barcode.");
    }
    
    return true;
  }

  /**
   * Assists with the functionality of checking if a Student exists in the application.
   * 
   * @param ia Instance of the InventoryApp class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  public static boolean containsKeyHelper(InventoryApp ia, Scanner scanner) {
    boolean correctBarcodeInput = false;
    int barcode = 0;
    
    // keep polling user for a valid ID number
    while (correctBarcodeInput == false) {
      System.out.print("Enter 9 digit barcode: ");
      barcode = scanner.nextInt();

      // check for correct ID input
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      correctBarcodeInput = true;
    }
    
    // check if Student exists in the system
    if (ia.containsGood(barcode) == true) {
      System.out.println("A good with this barcode exists in the system.");
    } else {
      System.out.println("A good with this barcode does not exist in the system.");
    }
    
    return true;
  }
}
