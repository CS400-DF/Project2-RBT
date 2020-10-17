import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents an inventory App
 * @author zihanwang
 *
 */
public class InventoryApp {
  public RedBlackTree<Good> tree;

  public InventoryApp() {
    this.tree = new RedBlackTree();
  }

  public void addItem(Integer barcode, String name, Double price, Integer quantity) {
    Good item = new Good(barcode, name, price, quantity);
    tree.insert(item);
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
  
}
