/**
 * @author zihan
 * @param <T>
 *
 */
public class Good implements Comparable<Good>{
  private Integer barcode;
  private String name;
  private Double price;
  private Integer quantity;

  /**
   * constructor
   */
  public Good() {

  }

  /**
   * constructor
   * 
   * @param barcode
   * @param name
   * @param price
   * @param quantity
   */
  public Good(Integer barcode, String name, Double price, Integer quantity) {
    this.barcode = barcode;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * @return the barCode
   */
  public Integer getBarcode() {
    return this.barcode;
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the price
   */
  public Double getPrice() {
    return this.price;
  }

  /**
   * @return the quantity
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * Good details are printed
   */
  public void printGood() {
    System.out
        .println("Name: " + this.name + "\nPrice: " + this.price + "\nQuantity: " + this.quantity);
  }

  /**
   * @return String containing Good details
   */
  public String getValue() {
    return (String) (getName() + " " + getPrice() + " " + getQuantity() + " ");
  }


  @Override
  public int compareTo(Good item) {
    Integer thisBarcode = this.barcode;
    Integer goodBarcode = item.getBarcode();
    return thisBarcode.compareTo(goodBarcode);
  }

}
