/**
 * 
 * Service Product Description:
 * 
 * 
 * 
 * 
 * 
 * This class represents a service product in the commerce system. It extends the Product class, encapsulating 
 * attributes and behaviors specific to service-type products.
 * 
 * 
 * 
 * Key Features:
 * 
 * - Inherits common product attributes and methods from the Product class.
 * - Specifically represents a service rather than a physical product.
 * 
 */

public class ServiceProduct extends Product {
    
     //constructor to initialize a ServiceProduct object.
     //inherits properties from the Product class and sets specific attributes for a service product.

    public ServiceProduct(String name, String description, double price, String itemIdentifier) {
        super(name, description, price, 1, itemIdentifier, true); //true because it's a service

    }//end constructor

    @Override
    
     //this method is overridden from Product. For service products, quantity updates are not applicable as they are hardcoded into the program within commercesystem
     
    public void updateQuantity(int quantityChange) {

    }
    
}//end of serviceproduct class
