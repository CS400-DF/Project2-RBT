/**
 * This class represents a good object for the inventory app
 * 
 * @author Dana Schneck
 */
public class Good implements Comparable<Good> {
	private Integer barcode;
	private String name;
	private Double price;
	private Integer quantity;
	
	/**
	 * Default constructor
	 */
	public Good() {
	};

	/**
	 * Initializes a new Good object with its barcode, name, price, and quantity
	 * 
	 * @param barcode  Barcode of the new Good
	 * @param name     Name of the new Good
	 * @param price    Price of the new Good
	 * @param quantity Quantity of the new Good
	 * 
	 */
	public Good(Integer barcode, String name, Double price, Integer quantity) {
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		
	}

	/**
	 * Gets the quantity of the stock of the good
	 * 
	 * @return The quantity of the stock of the good
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the stock of the good
	 * 
	 * @param quantity The new amount you want to set the quantity to
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Good o) {
		return this.barcode.compareTo(o.barcode);
	}

	/**
	 * Prints out the details of a Good object
	 */
	public void printGood() {
		System.out.println("Barcode: " + this.barcode + " \nName: " + this.name + "\nPrice: " + this.price
				+ "\nQuantity in Stock: " + this.quantity);
	}
	
	/**
	 * Returns the information stored with a Good object(its barcode, name, price and quantity)
	 * @return String representation of a Good
	 */
	@Override
	public String toString() {
		//returns product information
		return "Barcode: " + this.barcode + ", Name: " + this.name + ", Price: " + this.price
				+ ", Quantity in Stock: " + this.quantity+"\n";	
	}
}
