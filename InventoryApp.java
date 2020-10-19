	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.Scanner;
public class InventoryApp {


	/**
	 * This class represents an Inventory App for a store
	 * 
	 * @author Dana Schneck
	 * @author Zihan Wang
	 */


	  private int size;
	  public RedBlackTree<Good> tree;

	  /**
	   * Initializes a new inventory RBT
	   */
	  public InventoryApp() {
	    this.tree = new RedBlackTree<Good>();
	    size = 0;
	  }

	  /**
	   * This method is responsible for parsing a text-file, each line is checked for
	   * its validity. Valid lines are parsed and stored in the Red Black Tree.
	   * 
	   * @param FileName
	   * @return true or false depending on whether the file was parsed correctly
	   */
	  public boolean load(String FileName) {
	      boolean fileFound = true;
	      File data;
	      Scanner scnr;
	      int lineCount = 0;
	      try {
	          // checks if file exists
	          
	          data = new File(FileName);
	          
	          scnr = new Scanner(data);
	          while (scnr.hasNextLine()) {
	              
	              // file is parsed
	              lineCount++;
	              // lines are counted to indicate errors to the user
	              // each line is split
	              String[] details = scnr.nextLine().split(",");
	              if (details.length != 4) {
	                  // if the string array does not contain 4 elements
	                  System.out.println("Invalid line, line " + lineCount + " is skipped, with key " + details[0]);
	                  // moves on to the next line
	                  continue;
	              } else {
	                  // line validity checked
	                  if (checkLineValid(details)) {
	                          // student is added if line is valid
	                          addItem((Integer.parseInt(details[0])), details[1], Double.parseDouble(details[2]), Integer.parseInt(details[3]));
	                      
	                  } else {

	                      System.out.println("The barcode, price and/or quantity have not been entered correctly on line "
	                              + lineCount + " with key " + details[0] + " is skipped");

	                  }
	              }
	          }
	          scnr.close();
	      } catch (FileNotFoundException excpt) {
	          // FNFE exception
	          fileFound = false;
	          excpt.getStackTrace();
	      }
	      // returns true/false if file was parsed successfully
	      return fileFound;
	  }
	  
	  /**
	   * A string array containing the details parsed from a single line is checked
	   * 
	   * @param data
	   * @return true/false if line is valid
	   */
	  public boolean checkLineValid(String[] data) {
	      boolean checkBarcode = true, checkPrice = true, checkQuantity = true;
	      
	      // check if the barcode is an Integer
	      try {
	        Integer.parseInt(data[0]);
	      }
	      catch (NumberFormatException e) {
	        checkBarcode = false;
	      }

	      //length of the id must be 9
	      if (data[0].length() != 9) {            
	          checkBarcode = false;
	      }
	      
	      // check if the price is a Double
	      try {
	        Double.parseDouble(data[2]);
	      }
	      catch(NumberFormatException e) {
	        checkPrice = false;
	      }
	      
	      //check if the Quantity is an Integer
	      try {
	        Integer.parseInt(data[3]);
	      }
	      catch(NumberFormatException e) {
	        checkQuantity = false;
	      }
	      
	      return checkBarcode && checkPrice && checkQuantity;
	  }
	  
	  /**
	   * Adds a good object to the RBT representing the inventory of the store
	   * 
	   * @param barcode Barcode of the new good to add
	   * @param name Name of the good
	   * @param price Price of the good
	   * @param quantity Initial quantity of the good
	   */
	  public void addItem(int barcode, String name, double price, int quantity) {
	    Good good = new Good(barcode, name, price, quantity);
	    // call the super class insert method on a new Ggood
	    tree.insert(good);
	    size++;
	  }

	  /**
	   * Clears all of the goods from the RBT representing the inventory of the store
	   */
	  public void clearAllGoods() {
	    tree.root = null;
	    size = 0;
	  }


	  /**
	   * Search the inventory application for a good and return it if it exists
	   * 
	   * @param barcode Barcode of the good
	   * @return The Good if it is in the inventory app, null otherwise
	   */
	  public Good getGood(int barcode) {
	    Good findGood = new Good(barcode, "", 0.0, 0);
	    return getGoodHelper(findGood, tree.root);
	  }

	  /**
	   * Recursive helper method to lookup a Good in the inventory RBT
	   * 
	   * @param findGood Good to find
	   * @param current  Root of the subtree we are searching through
	   * @return The Good if it is in the inventory app, null otherwise
	   */
	  private Good getGoodHelper(Good findGood, RedBlackTree.Node<Good> current) {
	    if (tree.root == null) {
	      return null;
	    }
	    if (findGood.compareTo(current.data) == 0) {
	      return current.data;
	    } else if (findGood.compareTo(current.data) < 0) {
	      if (current.leftChild == null) {
	        return null;
	      }
	      return getGoodHelper(findGood, current.leftChild);
	    } else {
	      if (current.rightChild == null) {
	        return null;
	      }
	      return getGoodHelper(findGood, current.rightChild);
	    }
	  }

	  /**
	   * Checks if the RBT inventory contains a specific good
	   * 
	   * @param barcode Barcode of the good to check
	   * @return True if the good exists in the RBT, false otherwise
	   */
	  public boolean containsGood(int barcode) {
	    Good findGood = new Good(barcode, "", 0.0, 0);
	    if (getGoodHelper(findGood, tree.root) != null) {
	      return true;
	    } else {
	      return false;
	    }
	  }

	  /**
	   * Gets the number of goods in inventory RBT
	   * 
	   * @return The number of goods stored in this inventory RBt
	   */
	  public int getNumGoods() {
	    return size;
	  }

	  /**
	   * Lowers the quantity of a certain good that exists in the RBT inventory
	   * 
	   * @param barcode Barcode of the good to decrease quantity of
	   * @param quantityToLower Amount to decrease quantity by
	   */
	  public void lowerStock(int barcode, int quantityToLower) {
	    // Finds and stores specific good in the RBT to lower quantity of
	    Good goodToLower = getGood(barcode);

	    // If the good is already out of stock
	    if (goodToLower.getQuantity() == 0) {
	      System.out.println("This item is out of stock and therefore " + quantityToLower
	          + " amount of stock cannot be removed.");
	      return;
	    }
	    // If the good has excess stock than the amount the user wants to decrease by
	    if (goodToLower.getQuantity() >= quantityToLower) {
	      goodToLower.setQuantity(goodToLower.getQuantity() - quantityToLower);
	      System.out.println("Removed " + quantityToLower + " stock of good with barcode " + barcode
	          + ". There is now " + goodToLower.getQuantity() + " stock of this good remaining.");
	      return;
	    }
	    // If the good has less stock than the amount the user wants to decrease by
	    if (goodToLower.getQuantity() < quantityToLower) {
	      System.out.println("Couldn't remove " + quantityToLower + " stock so removed "
	          + goodToLower.getQuantity() + " instead. This good is now out of stock.");
	      goodToLower.setQuantity(0);
	      return;
	    }
	  }

	  /**
	   * Increases the quantity of a certain good that exists in the RBT inventory
	   * 
	   * @param barcode Barcode of the good to increase quantity of
	   * @param quantityToIncrease Amount to increase quantity by
	   */
	  public void increaseStock(int barcode, int quantityToIncrease) {
	    // Finds and stores specific good in the RBT to increase the quantity of
	    Good goodToIncrease = getGood(barcode);
	    
	    // Increase the stock of the good
	    goodToIncrease.setQuantity(goodToIncrease.getQuantity() + quantityToIncrease);
	    System.out
	        .println("Increased stock of good with barcode " + barcode + " by " + quantityToIncrease
	            + ". The current stock of this good is now " + goodToIncrease.getQuantity());
	  }
	}

