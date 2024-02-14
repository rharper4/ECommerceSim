/**
 * Cart Manager Description:
 * 
 * This class is responsible for managing the shopping cart in the commerce system. It handles the addition, removal,
 * and retrieval of products in the cart. The class uses arrays to store products and their quantities in the cart.
 * 
 * Key Features:
 * - Manages products and their quantities in a shopping cart.
 * - Allows adding and removing products from the cart.
 * - Provides functionality to view and clear the cart contents.
 * 
 * 
 * 
 */

//import JOptionPAne
import javax.swing.JOptionPane;

//start of cartManager class
public class CartManager {

    private Product[] cartProducts; //array to store products and services added to the cart.

    private int[] cartQuantities; //parallel array to store quantities of the products and services.

    private int itemCount; //number of items currently in the cart.

    //Constructor for CartManager initializes arrays based on maximum items.
    public CartManager(int maxItems) {
        cartProducts = new Product[maxItems];
        cartQuantities = new int[maxItems];
        itemCount = 0;

    }//end of constructor

    //method to add a product or service to the cart, returns true if successful.
    public boolean addItemToCart(Product product, int quantity) {

        //check if product is null or quantity is less than or equal to zero.
        if (product == null || quantity <= 0) {

            //invalid entry catch
            return false;

        }//end of if check for product being null/quantity <= 0

        //check if the cart is full.
        if (itemCount >= cartProducts.length) {

            JOptionPane.showMessageDialog(null, "Cart is full. Cannot add more items.");
            return false;

        }//end of check for cart being full 

        //add product and its quantity to the cart
        cartProducts[itemCount] = product;
        cartQuantities[itemCount] = quantity;

        //increment item count
        itemCount++;

        return true;

    }//end of add item to cart mmethod

    //method to check if the cart is empty
    public boolean isCartEmpty() {

        return itemCount == 0;

    }//end of check if cart is empty method


    //method to remove a specified quantity of a product or service from the cart.
    public boolean removeItemFromCart(String itemIdentifier, int quantity) {

        //looping through the items in the cart.
        for (int i = 0; i < itemCount; i++) {

            //check if the current item matches the specified identifier.
            if (cartProducts[i].getItemIdentifier().equals(itemIdentifier)) {

                //Check for valid quantity.
                if (quantity <= 0 || quantity > cartQuantities[i]) {

                    //alert the user the quantity was invalid
                    JOptionPane.showMessageDialog(null, "It appears that the quantity that you've entered is invalid. Please try again.");

                    //quantity not valid
                    return false;

                }//end of valid quantity check

                //subtract the specified quantity from the cart.
                cartQuantities[i] -= quantity;

                //if the quantity is zero, remove the item from the cart.
                if (cartQuantities[i] == 0) {
                
                    //call method to remove item based on its index
                    removeItemFromCart(i); 

                }//end of item removal

            //all is valid
             return true;


                //should add amount removed from cart back to product inventory


            }//end of valid quantity check

        }//end of iterating through items in the cart

        //alert the user the item was not found in their cart
        JOptionPane.showMessageDialog(null, "Item not found in cart.");

        //invalid input
        return false;

    }//end of remove item from cart method

    //helper method to remove an item from the cart by its index.
    private void removeItemFromCart(int index) {

        //shift all items after the removed item to fill the gap.
        for (int j = index; j < itemCount - 1; j++) {

            cartProducts[j] = cartProducts[j + 1];
            cartQuantities[j] = cartQuantities[j + 1];

        }//end of fixing items after removal

        //deincrement the item count after removal.
        itemCount--;

    }//end of remove item from cart method

    //method to display the contents of the cart in a JOP pane dialog.
    public void displayCartContents() {

        //check if the cart is empty before proceeding.
        if (itemCount == 0) {

            //alert user if the cart is empty
            JOptionPane.showMessageDialog(null, "Cart is empty.");

            return;

        }//end of if cart is empty check

        //construct a string to represent the cart contents.
        String cartContents = "Cart Contents:\n";

        //iterate through and retrieve cart contents
        for (int i = 0; i < itemCount; i++) {

        cartContents += cartProducts[i].getName() + " - Quantity: " + cartQuantities[i] + " - Identifier: " + cartProducts[i].getItemIdentifier() + "\n";

        }//end of iterating through to get the carts contents

        //output the carts contents to the user
        JOptionPane.showMessageDialog(null, cartContents);

    }//end of displaying cart contents to user method

    //method to calculate the total cost of items in the cart.
    public double calculateTotal() {

        //init total
        double total = 0.0;

        //Loop through items in the cart to sum up their total cost
        for (int i = 0; i < itemCount; i++) {

            total += cartProducts[i].getPrice() * cartQuantities[i];

        }//end of forloop for total cost sum

        return total;

    }//end of calculatetotal method

    //method to clear all items from the cart.
    public void clearCart() {

        //init item count
        itemCount = 0;

        //reinitialize the arrays to clear the contents.
        cartProducts = new Product[cartProducts.length];
        cartQuantities = new int[cartQuantities.length];

    }//end of clearcart method

}//end of cartmanager class