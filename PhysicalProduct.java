/**
 * 
 * 
 * Physical Product Description:
 * 
 * This class represents a physical product in the commerce system. It extends the Product class, adding specific 
 * attributes and behaviors pertinent to physical products, such as managing inventory quantities.
 * 
 * 
 * 
 * Key Features:
 * - Inherits common product attributes and methods from the Product class.
 * - Manages the quantity of the physical product available in inventory.
 * 
 * 
 */
public class PhysicalProduct extends Product {

    private int quantity;

   
    //Constructor to initialize a PhysicalProduct object.
    //Inherits properties from the Product class and sets the specific attributes for a physical product.

    public PhysicalProduct(String name, String description, double price, int quantity, String itemIdentifier) {
        super(name, description, price, quantity, itemIdentifier, false); // false because it's not a service
    }

    @Override
    
     //updates the quantity of the product in the inventory.
  
    public void updateQuantity(int quantityChange) {
        this.quantity += quantityChange;
    }

    //Getters and Setters for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}//end of physicalproduct class
