// --== CS400 File Header Information ==--
// Name: Nicole Gathman
// Email: ngathman@wisc.edu
// Team: DF
// TA: Yelun
// Lecturer: Florian
// Notes to Grader: nothing
import org.junit.Test;
import static org.junit.Assert.*;
	
public class TestInventoryApp {

	/**
	 * Test checks the correctness of load()
	 * After loading with file a.txt, 20 items should be in the database 
	 */
	@Test
	public void testLoad(){
	    InventoryApp app = new InventoryApp();
	    app.load("a.txt");
	    int numGoods = app.getNumGoods();
	    if(numGoods != 20) {
	      fail("Number of goods after load is incorrect");
	    }
	}
	
	/**
	 * Test checks the correctness of lowerStock
	 * Tests case where item is already out of stock
	 * Tests the case where the user asks to remove more than what is in stock
	 */
	@Test 
	public void testLowerStock() {
	    InventoryApp app = new InventoryApp();
	    boolean loaded = true;
	    loaded = app.load("a.txt");

	    int initialQuantity = app.getGood(576876774).getQuantity();
	    app.lowerStock(576876774,10);
	    int finalQuantity = app.getGood(576876774).getQuantity();
	    if(initialQuantity == finalQuantity) {
	        fail("The quantity of the good did not change");
	    }   	   
	    else if(!loaded) {
	    	fail("load method failed");
	    }
        initialQuantity = app.getGood(346765689).getQuantity();
        app.lowerStock(346765689,34);
        finalQuantity = app.getGood(346765689).getQuantity(); 
	    if(initialQuantity == finalQuantity) {
	        fail("The quantity of the good did not change");
	    } 
        initialQuantity = app.getGood(576876774).getQuantity();
        app.lowerStock(576876774,10);
        finalQuantity = app.getGood(576876774).getQuantity();
        if(initialQuantity == finalQuantity) {
            fail("The quantity of the good did not change");
        }
	}
	
	/**
	 * Test checks if inventory contains an item after the item has been added
	 * Also checks the correctness of containsItem()
	 */
	@Test
	public void testAddItem() {
	    InventoryApp app = new InventoryApp();
	    boolean containsItem = true;
        app.addItem(303030300, "1", 9.50,100);
        app.addItem(303030301, "2", 9.51,100);
        app.addItem(303030302, "3", 9.52,100);
        app.addItem(303030303, "4", 9.53,100);
        app.addItem(303030304, "5", 9.54,100);
        app.addItem(303030305, "6", 9.55,100);
        app.addItem(303030306, "7", 9.56,100);
        app.addItem(303030307, "8", 9.57,100);
        app.addItem(303030308, "9", 9.58,100);
        app.addItem(303030309, "10", 9.59,100);
        app.addItem(303030310, "11", 9.60,100);
        app.addItem(303030311, "12", 9.61,100);
        for(int x = 0;x<app.getNumGoods();x++) {
        	int barcode = 303030300 + x;
            if(app.containsGood(barcode) == false) {
                containsItem = false;
            }
        }  
        if(!containsItem) {
            fail("method containsGood() returned false when it should have returned true");
        }
	}

	/**
	 * Test checks if the quantity of a good will increase after calling increaseStock()
	 * Fails if the quantity of the good stays constant after calling increaseStock()
	 */
	@Test 
	public void testIncreaseStock() {
	    InventoryApp app = new InventoryApp();
	    boolean loaded = true;
	    loaded = app.load("a.txt");
	    int initialQuantity = app.getGood(576876774).getQuantity();
	    app.increaseStock(576876774,10);
	    int finalQuantity = app.getGood(576876774).getQuantity();
	    if(initialQuantity == finalQuantity) {
	        fail("The quantity of the good did not change");
	    } 
	    else if(!loaded) {
	    	fail("load method failed");
	    }
        initialQuantity = app.getGood(346765689).getQuantity();
        app.increaseStock(346765689,34);
        finalQuantity = app.getGood(346765689).getQuantity(); 
	    if(initialQuantity == finalQuantity) {
	        fail("The quantity of the good did not change");
	    } 
        initialQuantity = app.getGood(900089877).getQuantity();
        app.lowerStock(900089877,10);
        finalQuantity = app.getGood(900089877).getQuantity();
        if(initialQuantity == finalQuantity) {
            fail("The quantity of the good did not change");
        }	
	}

	/**
	 * Test checks the correctness of the method clearAllGoods()
	 * The method should clear all the items in the structure.
	 * Test will fail if the number of items after calling the method is not zero and initial is not zero
	 */
	@Test
	public void testClearAllGoods() {
	    InventoryApp app = new InventoryApp();
	    boolean loaded = true;
	    loaded = app.load("a.txt");
	    int initialNumGoods = app.getNumGoods();
	    app.clearAllGoods();
	    int finalNumGoods = app.getNumGoods();
	    if(initialNumGoods != 0 && initialNumGoods == finalNumGoods) {
	        fail("the number of items in the data structure did not change after calling clearNumGoods()");
	    }
	    else if(!loaded) {
	    	fail("load method failed");
	    }
	}
}
