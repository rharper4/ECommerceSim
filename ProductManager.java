/**
 * Product Manager Description:
 * 
 * This class is responsible for managing the products in the commerce system. It handles the storage, addition, 
 * and retrieval of product information. The class uses an array to store products and keeps track of the current
 * number of products in the system.
 * 
 * 
 * Key Features:
 * 
 * 
 * - Stores and manages products in an array.
 * - Allows addition of new products to the system.
 * - Provides functionality to retrieve product information.
 * 
 * 
 */

 
 //import
 import javax.swing.JOptionPane;




public class ProductManager {

    private Product[] products; //array to store products and services
    
    private int productCount; //number of products and services currently in the system

    
    //constructor to initialize a ProductManager object.
    public ProductManager(int maxProducts) {
        products = new Product[maxProducts];
        productCount = 0;

    }//end of productmanager constructor

    //adds a new product or service to the system
    public boolean addProduct(String name, String description, double price, int quantity, String itemIdentifier, boolean isService) {
        if (!Product.validateItemIdentifier(itemIdentifier)) {

            //was the item identifier valid?
            JOptionPane.showMessageDialog(null, "Invalid Item Identifier. It must start with 'I' followed by 4 digits. \n\n You may not add or remove services.");
            
            //not valid
            return false;

        }//end if not valid

        //was the item identifier the same as a previously used one?
        if (findProduct(itemIdentifier) != null) {

            JOptionPane.showMessageDialog(null, "Item Identifier already exists. Please use a unique identifier.");
            
            return false;

        }//end if same identifier was used

        //is the product list full?
        if (productCount >= products.length) {

            JOptionPane.showMessageDialog(null, "Product list is full. Cannot add more products.");
            
            return false;

        }//end if product list is full

        //if everything else is valid then create the product
        products[productCount++] = new Product(name, description, price, quantity, itemIdentifier, isService);
       
        return true;

    }//end of addProduct

    //finds and returns a product or service based on its unique identifier
    public Product findProduct(String itemIdentifier) {

        //iterate through products based on the total count
        for (int i = 0; i < productCount; i++) {

            //if product is found
            if (products[i].getItemIdentifier().equals(itemIdentifier)) {

                //return the product
                return products[i];

            }//end if

        }//end of for loop

        //else if product cant be found return nothing
        return null;

    }//end of findProduct

    //removes a specified quantity of a product or service
    public boolean removeProductQuantity(String itemIdentifier, int quantityToRemove) {

        Product product = findProduct(itemIdentifier);

        if (product == null) {

            JOptionPane.showMessageDialog(null, "Product or service with the given identifier does not exist.");
            
            return false;

        }//end of if product is null or invalid on user entry

        //is the product a service?
        if (product.isService() && quantityToRemove > product.getQuantity()) {

            JOptionPane.showMessageDialog(null, "Cannot remove quantity from a service.");
            
            return false;

        }//end of if service check

        //is the quantity valid?
        if (quantityToRemove <= 0 || quantityToRemove > product.getQuantity()) {

            JOptionPane.showMessageDialog(null, "Invalid quantity to remove.");

            return false;

        }//end of quantity validation

        product.updateQuantity(-quantityToRemove);

        if (product.getQuantity() == 0) {

            //remove product or service from list if quantity is zero
            removeProduct(product);
            
        }//if 0 remove from list completely

        return true;

    }//end of removeProductQuantity

    //removing products from the list and updating it
    private void removeProduct(Product product) {
        
        for (int i = 0; i < productCount; i++) {
            if (products[i].equals(product)) {
                for (int j = i; j < productCount - 1; j++) {
                    products[j] = products[j + 1];
                }
                //subtract product total count
                productCount--;
                break;
            }

        }//end loop

    }//end removeProduct mutator

    //generates a report of all products and services
    public void displayProductList() {

        String serviceProductsList = "*******************************************************************************************************************************************\nIMPORTANT: Please take note that all services come in hourly packages \ntherefore the quantity that you add to your cart will be equal to the hours of the service you are purchasing.\n*******************************************************************************************************************************************\nService Products Available:\n";
        String physicalProductsList = "Physical Products Available:\n\n";

        //iterate through the products
        for (int i = 0; i < productCount; i++){
        Product product = products[i];
        String productDetails = "Item Name: " + product.getName() + " || Price: $" + product.getPrice() + " || Amount In Stock: " + product.getQuantity() + " || Item's Unique Identifier: " + product.getItemIdentifier() + "\n";

        if (product.isService()){
            serviceProductsList += productDetails;

        }//end of if

        else{
            physicalProductsList += productDetails + "Product Description:  " + product.getDescription() + "\n\n";
             }

            }

            JOptionPane.showMessageDialog(null, serviceProductsList + "\n" +physicalProductsList);

        }//end of displayProductList method

}//end of productManager class
