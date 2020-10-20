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
      + "[L] Loads a text file of Goods objects into the Covid-19 Tracker App\n"
      + "[P] Puts a single new Good into the Inventory App.\n"
      + "[G] Gets a good's information from the application.\n"
      + "[S] Return the amount of goods in the application.\n"
      + "[K] Check if a good exists in the application\n"
      + "[U] Increase the stock\n"
      + "[D] Decrease the stock\n"
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
            + " goods currently stored in this application.");
        break;

      case "K": // check if a Student exists in the application
        if (containsKeyHelper(ia, scanner) == false) {
          return false;
        }
        break;

      case "C": // clear all Students from the application
        ia.clearAllGoods();
        System.out.println("Cleared all goods from this application.");
        break;

      case "H": // display the menu
        System.out.println(MENU);
        break;

      case "Q": //quit
        System.out.println("thank you for using our app!");
        scanner.close();
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
    System.out.println(promptCommandLine);
    
    // get users first input
    String line = scanner.nextLine().trim();  
    char c = line.charAt(0);
    
    while (Character.toUpperCase(c) != 'Q') {
      // parse and process the user command line
      if (processUserCommandLine(line,ia,scanner) != false)
      {
        System.out.print(promptCommandLine);
        line = scanner.next();
        c = line.charAt(0);
      }
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

  /**increase the quantity of a good, print out menu if barcode can not be found*/
  private static void increaseStockHelper(InventoryApp ia,Scanner scanner)
  {
    //creat all variables
    boolean successOrNot = false;
    boolean successAmount = false;
    String barcode = "";
    String amount = "";
    while (successOrNot==false)
    {
      System.out.println("enter barcode of the good you would like to increase"); //enter barcode
      barcode = scanner.next(); //scan
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      if (ia.containsGood(Integer.parseInt(barcode)) != true) {
        System.out.println("A good with this barcode does not exist in the system."); //check if barcode exists already
        continue;
      }
      successOrNot = true;
    }
    while (successAmount==false)
    {
      System.out.println("enter amount of the good you would like to increase"); //ask user to input amount
      amount = scanner.next();//todo
      if (!checkIfValidInt(amount)) //check if it is an integer
      {
        continue;
      }
      successAmount = true;
    }
    ia.increaseStock(Integer.parseInt(barcode),Integer.parseInt(amount)); //increase it
  }

  /**decrease the quantity of a good, print out menu if barcode can not be found*/
  private static void lowerStockHelper(InventoryApp ia,Scanner scanner)
  {
    boolean successOrNot = false;
    boolean successAmount = false;
    String barcode = "";
    String amount = "";
    while (successOrNot==false)
    {
      System.out.println("enter barcode of the good you would like to decrease"); //same as increase but decrease input barcode item this time
      barcode = scanner.next();
      if (checkIfValidID(barcode) == false) {
        continue;
      }
      if (ia.containsGood(Integer.parseInt(barcode)) != true) {
        System.out.println("A good with this barcode does not exist in the system."); //same as increase but decrease input barcode item this time
        continue;
      }
      successOrNot = true;
    }
    while (successAmount==false)
    {
      System.out.println("enter amount of the good you would like to decrease"); //same as increase but decrease input barcode item this time
      amount = scanner.next();//todo
      if (!checkIfValidInt(amount))
      {
        continue;
      }
      successAmount = true;
    }
    ia.lowerStock(Integer.parseInt(barcode),Integer.parseInt(amount)); //same as increase but decrease input barcode item this time
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
  private static boolean checkIfValidID(String id) {
    int length = String.valueOf(id).length();
    if (!checkIfValidInt(id))
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

  /**check if a input is a double*/
  private static boolean checkIfValidDouble(String str)
  {
    boolean b = true;
    try{
      Double db = Double.parseDouble(str);
    }
    catch (NumberFormatException e)
    {
      b = false;
    }
    if (!b)
    {
      System.out.println("this is not a double value please re-enter a correct double value");
    }
    return b;
  }

  /**check if the input is a int */
  private static boolean checkIfValidInt(String str)
  {
    boolean b = true;
    try{
      int db = Integer.parseInt(str);
    }
    catch (NumberFormatException e)
    {
      b = false;
    }
    if (!b)
    {
      System.out.println("this is not a integer value please re-enter a correct int value");
    }
    return b;
  }

  /**check if the input is a int without print out a statement*/
  private static boolean checkIfValidInt1(String str)
  {
    boolean b = true;
    try{
      int db = Integer.parseInt(str);
    }
    catch (NumberFormatException e)
    {
      b = false;
    }
    return b;
  }

  /**check if the input is a double without print out a statement*/
  private static boolean checkIfValidDouble1(String str)
  {
    boolean b = true;
    try{
      Double db = Double.parseDouble(str);
    }
    catch (NumberFormatException e)
    {
      b = false;
    }
    return b;
  }

  /**check if the input is a string*/
  private static boolean checkIfValidString(String str)
  {
    if (checkIfValidInt1(str) || checkIfValidDouble1(str))
    {
      System.out.println("name must be a string, please re-enter both barcode and name: ");
      return false;
    }
    return true;
  }


  /**for load*/
  private static boolean loadHelper(InventoryApp ia, Scanner scanner) {
    String fileName = null;
    System.out.println(
            "Please enter the name of the text file of Goods you would like to load into the application in the form <FileName>.txt : ");
    fileName = scanner.next();
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
    boolean correctQuantityInput = false;
    boolean correctPriceInput = false;
    // keep polling user for valid barcode number
    while (correctBarcodeInput == false) {
      // initialize variables
      String barcode = "";
      String name = "";
      String price = "";
      String quantity = "";

      System.out.println("Enter 9 digit barcode: ");
      barcode = scanner.next(); //todo
      // check for correct barcode input
      if (checkIfValidID(barcode) == false) {
        continue;
      }

      // check if good has already been entered into the system
      if (ia.containsGood(Integer.parseInt(barcode)) == true) {
        System.out.println("A good with that barcode already exists.");
        continue;
        // else add the good into the system
      }

      System.out.println("Enter good's name: ");
      name = scanner.next(); //TODO
      if (!checkIfValidString(name))
      {
        continue;
      }
      // keep polling user for a valid quantity input
      while (correctQuantityInput == false) {
        System.out.println(
                "Enter good's quantity: ");
        quantity = scanner.next(); //todo
        if (!checkIfValidInt(quantity))
        {
          continue;
        }
        // check for correct quantity input
        correctQuantityInput = true;
      }

      while (correctPriceInput == false) {
        System.out.println("Enter a double for price: ");
        price = scanner.next(); //todo
        // check for correct price input
        if (!checkIfValidDouble(price))
        {
          continue;
        }
        correctPriceInput = true;
      }
      // add correctly formatted good to the application
      ia.addItem(Integer.parseInt(barcode), name, Double.parseDouble(price), Integer.parseInt(quantity));
      System.out.println("New good successfully added.");
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
    String barcode = "";
    try {
      while (!correctBarcode) {
        System.out.print("Enter 9 digit barcode: ");
        barcode = scanner.next();

        // check for correct barcode input
        if (!checkIfValidID(barcode)) {
          System.out.println("barcode must be a 9 digits integer");
          continue;
        }

        correctBarcode = true;
      }

      // try to get good information
      try {
        ia.getGood(Integer.parseInt(barcode)).printGood();
      } catch (NoSuchElementException e) {
        System.out.println("There is no existing good with that barcode.");
      }
      return true;
    }
    catch (NullPointerException e)
    {
      System.out.println("item does not exit please try again");
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
    String barcode = "";
    
    // keep polling user for a valid barcode number
    while (!correctBarcodeInput) {
      System.out.print("Enter 9 digit barcode: ");
      barcode = scanner.next();

      // check for correct barcode input
      if (!checkIfValidID(barcode)) {
        continue;
      }
      correctBarcodeInput = true;
    }
    
    // check if good exists in the system
    if (ia.containsGood(Integer.parseInt(barcode))) {
      System.out.println("A good with this barcode exists in the system.");
    } else {
      System.out.println("A good with this barcode does not exist in the system.");
    }
    
    return true;
  }
}
