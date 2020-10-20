
import org.junit.jupiter.api.Test; // JUnit 5
import static org.junit.Assert.*;

/**
 * Class which tests the functionality of InventoryApp
 * 
 * @author Abhay Prakash Punjabi
 *
 */
public class TestInventoryApp {

	/**
	 * Method responsible for testing the load function of the app, given a text
	 * file Parses information from the txt file while validating each line and adds
	 * to a RBT
	 */
	@Test
	public void testLoadApp() {

		InventoryApp testLoadApp = new InventoryApp();
		// loads a new file
		testLoadApp.load("a.txt");
		// expected output of the RBT
		String entries = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n" + "]";
		// checking if the RBT created and expected output match
		if (!testLoadApp.tree.toString().equals(entries)) {
			fail("File has not been loaded correctly");
		}
		// correct number of items in the tree
		if (testLoadApp.getNumGoods() != 20) {
			fail("The RBT does not contain the correct amount of items");
		}
	}

	/**
	 * Tests the addItem() and getItem() methods of InventoryApp after loading in a
	 * file and adding single entries into the inventory This method also verifies
	 * if the parsed information and the added Good objects are stored correctly in
	 * the RBT and their content matches their expected output
	 */
	@Test
	public void testPutAndGet() {
		InventoryApp testPutandGetApp = new InventoryApp();
		//loads file
		testPutandGetApp.load("a.txt");
		//expected ouput
		String entries = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n" + "]";
		//expected output after adding a Good
		String afterAdd = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 123456789, Name: KitKat, Price: 2.0, Quantity in Stock: 100\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n" + "]";
		//checking if the original tree and expected output match
		if (!testPutandGetApp.tree.toString().equals(entries)) {
			fail("File has not been loaded correctly");
		}
		//adds an item to the tree
		testPutandGetApp.addItem(123456789, "KitKat", 2, 100);
		//checks if the tree is updated successfully
		if (!testPutandGetApp.tree.toString().equals(afterAdd)) {
			fail("Item " + "123456789, KitKat, 2, 100" + " hasn't been added correctly");
		}
		//checks if the size is updated
		if (testPutandGetApp.getNumGoods() != 21) {
			fail("The RBT does not contain the correct amount of items");
		}
		//adds another item
		testPutandGetApp.addItem(111111111, "ABC", 222, 1001);
		afterAdd = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 123456789, Name: KitKat, Price: 2.0, Quantity in Stock: 100\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n"
				+ ", Barcode: 111111111, Name: ABC, Price: 222.0, Quantity in Stock: 1001\n" + "]";
		//checks if the tree is updated successfully
		if (!testPutandGetApp.tree.toString().equals(afterAdd)) {
			fail("Item " + "111111111, ABC, 222, 1001" + " hasn't been added correctly");
		}
		//check if size is updated
		if (testPutandGetApp.getNumGoods() != 22) {
			fail("The RBT does not contain the correct amount of items after the addition of the 2nd item");
		}
		//another item is added
		testPutandGetApp.addItem(211345674, "Chips", 22, 1);
		afterAdd = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 123456789, Name: KitKat, Price: 2.0, Quantity in Stock: 100\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 211345674, Name: Chips, Price: 22.0, Quantity in Stock: 1\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n"
				+ ", Barcode: 111111111, Name: ABC, Price: 222.0, Quantity in Stock: 1001\n" + "]";
		//tree matches expected output and if size is updated correctly
		if (!testPutandGetApp.tree.toString().equals(afterAdd)) {
			fail("Item " + "211345674, Chips, 22, 1" + " hasn't been added correctly");
		}
		if (testPutandGetApp.getNumGoods() != 23) {
			fail("The RBT does not contain the correct amount of items after the addition of the 3rd item");
		}
		testPutandGetApp.addItem(576872774, "Bananas", 1, 100);
		afterAdd = "[Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 211345679, Name: Black Pepper, Price: 1.49, Quantity in Stock: 25\n"
				+ ", Barcode: 898789809, Name: Kind Bar, Price: 1.2, Quantity in Stock: 20\n"
				+ ", Barcode: 134798543, Name: Ketchup, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 346765689, Name: Greek Yogurt, Price: 1.59, Quantity in Stock: 25\n"
				+ ", Barcode: 780679543, Name: Kiwi, Price: 0.49, Quantity in Stock: 200\n"
				+ ", Barcode: 907539754, Name: Peach, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 119876542, Name: Corn Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 166795467, Name: Vegetable Oil, Price: 2.49, Quantity in Stock: 20\n"
				+ ", Barcode: 233345678, Name: Pear, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 458976534, Name: Soup, Price: 1.29, Quantity in Stock: 20\n"
				+ ", Barcode: 678998768, Name: Battery, Price: 4.49, Quantity in Stock: 20\n"
				+ ", Barcode: 887654567, Name: Pumpkin, Price: 9.99, Quantity in Stock: 30\n"
				+ ", Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n"
				+ ", Barcode: 983458721, Name: Prune, Price: 0.49, Quantity in Stock: 100\n"
				+ ", Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 10\n"
				+ ", Barcode: 123456789, Name: KitKat, Price: 2.0, Quantity in Stock: 100\n"
				+ ", Barcode: 156034787, Name: Baking Soda, Price: 3.1, Quantity in Stock: 20\n"
				+ ", Barcode: 211345674, Name: Chips, Price: 22.0, Quantity in Stock: 1\n"
				+ ", Barcode: 367656768, Name: Paper Towel, Price: 2.49, Quantity in Stock: 50\n"
				+ ", Barcode: 576872774, Name: Bananas, Price: 1.0, Quantity in Stock: 100\n"
				+ ", Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 10\n"
				+ ", Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n"
				+ ", Barcode: 111111111, Name: ABC, Price: 222.0, Quantity in Stock: 1001\n" + "]";
		//checks the tree after the addition of the last item
		if (!testPutandGetApp.tree.toString().equals(afterAdd)) {
			fail("Item " + "576872774, Bananas, 1, 100" + " hasn't been added correctly");
		}
		if (testPutandGetApp.getNumGoods() != 24) {
			fail("The RBT does not contain the correct amount of items after the addition of the 4th item");
		}
		//gets information of the good from the tree
		if (!testPutandGetApp.getGood(111111111).toString()
				.equals("Barcode: 111111111, Name: ABC, Price: 222.0, Quantity in Stock: 1001\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		//gets info of the Good added from the parsed file
		if (!testPutandGetApp.getGood(987322345).toString()
				.equals("Barcode: 987322345, Name: Salmon, Price: 6.49, Quantity in Stock: 20\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		//gets info of the Good added from the parsed file
		if (!testPutandGetApp.getGood(900089877).toString()
				.equals("Barcode: 900089877, Name: Salt, Price: 2.0, Quantity in Stock: 25\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		//checks if an item absent from the tree exists
		if ((testPutandGetApp.getGood(222222222) != null)) {
			fail("An item which should be absent from the inventory exists");
		}
		//size remains the same
		if (testPutandGetApp.getNumGoods() != 24) {
			fail("The RBT does not contain the correct amount of items after the addition of the 4th item");
		}
	}

	/**
	 * Tests whether the quantity of a Good object increases accordingly
	 */
	@Test
	public void testIncrease() {
		InventoryApp testIncreaseApp = new InventoryApp();
		testIncreaseApp.load("a.txt");
		//increases stock of an item
		testIncreaseApp.increaseStock(576876774, 100);
		//verifies whether increase was successful
		if (testIncreaseApp.getGood(576876774).getQuantity() != 200) {
			fail("Quantity hasn't been increased correctly");
		}
		//also checks if the item description updates according to the new quantity
		if (!testIncreaseApp.getGood(576876774).toString()
				.equals("Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 200\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		
		testIncreaseApp.increaseStock(116789876, 200);
		//verifies whether increase was successful
		if (testIncreaseApp.getGood(116789876).getQuantity() != 210) {
			fail("Quantity hasn't been increased correctly");
		}
		//also checks if the item description updates according to the new quantity
		if (!testIncreaseApp.getGood(116789876).toString()
				.equals("Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 210\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
	}

	/**
	 * Tests whether the quantity of a Good object decreases accordingly
	 */
	@Test
	public void testDecrease() {
		InventoryApp testDecreaseApp = new InventoryApp();
		//loads file
		testDecreaseApp.load("a.txt");
		//decreases stock of an item by decreasing it by an amount equal to its quantity
		testDecreaseApp.lowerStock(576876774, 100);
		//quantity should now be set to 0 
		if (testDecreaseApp.getGood(576876774).getQuantity() != 0) {
			fail("Quantity hasn't been increased correctly");
		}
		//checks if the item description decreases correctly
		if (!testDecreaseApp.getGood(576876774).toString()
				.equals("Barcode: 576876774, Name: Apple, Price: 0.49, Quantity in Stock: 0\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		//decreases stock of an item by decreasing it by an amount greater than its quantity
		testDecreaseApp.lowerStock(116789876, 200);
		//quantity has been updated
		if (testDecreaseApp.getGood(116789876).getQuantity() != 0) {
			fail("Quantity hasn't been decreased correctly");
		}
		//checks if the description changes
		if (!testDecreaseApp.getGood(116789876).toString()
				.equals("Barcode: 116789876, Name: Body Lotion, Price: 10.49, Quantity in Stock: 0\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
		//decreases stock of an item by decreasing it by an amount lesser than its quantity
		testDecreaseApp.lowerStock(776542980, 5);
		if (!testDecreaseApp.getGood(776542980).toString()
				.equals("Barcode: 776542980, Name: Burger, Price: 3.49, Quantity in Stock: 5\n")) {
			fail("The item is stored incorrectly, and its details are incorrect");
		}
	}

}
