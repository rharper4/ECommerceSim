/*IT 206 - Final Project - Backend of a tech companies E-Commerce site

Robert Harper
Jiyoung Torres


* Product Description: 

 * This will serve as our main implementation class for our commerce system. This system will simualte the backend of an ecommerce site for a tech store.
 * The tech store within our demo will have multiple types of products such as services and physical products. Users will be able to add these products to their cart
 * and even create new products that can be displayed within the simulation's product list. These features will all be implemented via a series of JOptionPane menu's prompting the
 * user to choose different options within the program. Upon adding all the items to their cart that they'd like, they will be able to choose within the menus to check-out their cart within the simulation.
 * Whenever proceeding to check-out, the user will be prompted to enter valid credit card details and the credit cards will be validated as there are specific criteria
 * for each credit card type. Upon successful payment verification, the cart will be cleared and the user may continue managing the product inventory or shopping if they so please.
 * 
 */


import javax.swing.JOptionPane;

//start of implementation class
public class CommerceSystem {

    
    private static ProductManager productManager;
    private static CartManager cartManager;
    private static PaymentManager paymentManager;

    //start of main method
    public static void main(String[] args) {

        productManager = new ProductManager(50); //Assuming a maximum of 50 products
        cartManager = new CartManager(20); //Assuming a maximum of 20 items in the cart
        paymentManager = new PaymentManager();
        
        //calling to initialize services
        initializeServiceProducts();

        //the program is running so setting this to true
        boolean running = true;

        //while the program is running
        while (running) {
            String option = JOptionPane.showInputDialog(null, "Choose an option:\n1. Manage Products\n2. Add to Cart\n3. Checkout\n4. Exit");


            //start of the main menu if-else branch
            if ("1".equals(option)) {
                manageProducts();
            }//end else for option 1: managingProducts

            else if ("2".equals(option)) {
                addProductToCart();
            }//end else for option 2: adding product to cart

            else if ("3".equals(option)) {
                checkout();
            }//end else for option 3: checkout

            else if ("4".equals(option)) {
                //user has indicated that they'd like to exit. Setting running to false to exit loop.
                running = false;

                //alerting the user that they've chosen t o exit
                JOptionPane.showMessageDialog(null, "Exiting Commerce System.");

            }//end else for Option 4: user choosing to exit

            //all else was invalid
            else {
                JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");

            }//end else for invalid option

        }//end of while loop

    }//end of main method


    //start of manageProducts class
    private static void manageProducts() {

        //display product list to the user before they begin product management
        productManager.displayProductList();
        
        //prompting the user to choose a menu option
        String manageOption = JOptionPane.showInputDialog(null, "Would you like to:\n1. Add a Product\n2. Remove a Product\n3. Return to main menu\nPlease enter 1, 2, or 3:");

        //user chooses to add a product
        if ("1".equals(manageOption)) {

            addProduct();

        }//end of user choosing to add a product to their shops inventory
        
        else if ("2".equals(manageOption)) {

            removeProduct();

        }//end of removing a product else branch

        else if ("3".equals(manageOption)){

            //alert the user they will be returned to the main menu
            JOptionPane.showMessageDialog(null, "Returning to the main menu.");

        }//end of option 3
        else {

            JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");

        }//end of invalid option else branch

    }//end manageProducts



    //Method to initialize service product objects
    private static void initializeServiceProducts() {
        
        //Hard-coding the services
        //Please note that services will come in hourly packages. So the stock will start at 1000.
        productManager.addProduct("Consulting", "Business consulting services", 150.0, 1000, "I0010", true);
        productManager.addProduct("Maintenance", "Regular maintenance services", 80.0, 1000, "I0020", true);
        productManager.addProduct("Training", "Professional training sessions", 200.0, 1000, "I0030", true);

    }//end of initializing services as a product 


    //method to remove a product upon user indication
    private static void removeProduct() {

        //prompting the user to input the product's item identifier (this code's format will always be IXXXX)
        String itemIdentifier = JOptionPane.showInputDialog("EX Format: I1234\nEnter the unique identifier of the item you'd like to remove:");

        //start of try block
        try{

        //prompting the user for quantity that they'd like to remove
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity you'd like to remove:"));

        //removePRoductQuantity will return true if operation was successful
        if (productManager.removeProductQuantity(itemIdentifier, quantity)) {

            //alert user that the product was removal operation completed successfully
            JOptionPane.showMessageDialog(null, "Removed Successfully.");
            
        }//end if

        else {

            //alert the user that the product quantity was failed to remove
            JOptionPane.showMessageDialog(null, "ERROR:Something went wrong - Failed to remove product quantity. \n\nPlease try again.");

        }//end of product addition failure else branch

    }//end of try

    catch(NumberFormatException e){

        //alert user that they must input a valid integer
        JOptionPane.showMessageDialog(null, "Operation was unsuccessful, Please try again by entering a valid integer for the quantity.");

    }//end of catch

}//end of removeProduct method


    //start of addProduct method
    private static void addProduct() {

        //start of try-catch
        try{

        //Prompt user to input the product details
        String name = JOptionPane.showInputDialog("Please enter a name for the product you are listing:");

        //Prompt user to input the product description
        String description = JOptionPane.showInputDialog("Please enter a detailed description of the product you are listing:");

        //prompt the user to input the price and check its a double
        double price = Double.parseDouble(JOptionPane.showInputDialog("Please enter the product's price:"));

        //prompt the user to input an item identifier (this is validated within Product.java)
        String itemIdentifier = JOptionPane.showInputDialog("Unique Identifier should be I followed by 4 integers\nExample Format: I1234\nPlease enter a unique product identifier: ");
    
        //prompt the user to input the quantity that they'd like to add to their shop
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity you are selling:"));
    
    
        //Add the product to the product manager and display a message based on the result
        if (productManager.addProduct(name, description, price, quantity, itemIdentifier, false)) {

            //display the product was successfully added to the stock
            JOptionPane.showMessageDialog(null, "Product information was added successfully.");

        }//end of successful addition 
        
        //if the product was not successfully added
        else {

            //alert the user that the operation failed.
            JOptionPane.showMessageDialog(null, "Failed to add product.");

        }//end of product failing to add else branch

    }//end try

    catch(NumberFormatException e){

        //alert the user that that the data they input was not valid (Could re-visit & make individual try-catch branches, but for now this is fine just adding a note to the error message)
        JOptionPane.showMessageDialog(null, "You did not enter valid data for one of the numbers. \n\nNote: For price you may enter a decimal, and for quantity you may only enter integers.");

    }//end catch


}//end addProduct method
    


    //checkout method
    private static void checkout() {

        //is the users cart empty?
        if (cartManager.isCartEmpty()){

            //before the operation begins, if the user attempts to checkout with an empty cart, they will be notified that they're being returned to the main menu.
            JOptionPane.showMessageDialog(null, "Users cart is empty, returning them to the main menu.");

        }//end if

    //as long as the users cart is not empty...
    else{

    //display the contents of the cart to the user
    cartManager.displayCartContents();


        //calculate the sum of the items within the cart
        double total = cartManager.calculateTotal();

        //Prompting the user to input a menu option
        String checkoutOption = JOptionPane.showInputDialog(null, "Your total is: $" + total + "\nWould you like to:\n1.Proceed to Payment\n2.Remove an Item from Cart\n3.Return to main menu\nPlease choose option 1, 2 or 3:");
       
        //user choice 1: proceed to payment for cart
        if ("1".equals(checkoutOption)) {

            proceedToPayment(total);

        }//end of if choice 1

        //user choice 2: remove an item from the cart
        else if ("2".equals(checkoutOption)) {

            removeItemFromCart();

        }//end of if choice 2

        else if ("3".equals(checkoutOption)){

            JOptionPane.showMessageDialog(null,"Returning to main menu...");
        }//end of if choice 3

        //every other option not stated will be invalid 
        else {

            //alert the user the option they chose was invalid
            JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");

        }//end of invalid option else branch

    }//end of else

}//end checkout method


    //start of remove item from cart method
    private static void removeItemFromCart() {

        //prompt the user to enter the item identifier of the item they'd like to remove from their cart
        String itemIdentifier = JOptionPane.showInputDialog("Enter the product identifier of the item to remove from cart:");

    //start of try-catch
      try{

        //prompt the user for the quantity  
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity to remove from cart:"));
        

        //as long as the inputs are valid
        if (cartManager.removeItemFromCart(itemIdentifier, quantity)) {

            //alert user the operation was successful
            JOptionPane.showMessageDialog(null, "Item removed from cart successfully.");

            //add the item that was removed back to the total amount in stock


        }//end if

         else {
            //alert the user the operation was not successful
            JOptionPane.showMessageDialog(null, "Failed to remove item from cart.");
        
        }//end else

    }//end try
    catch(NumberFormatException e){

        JOptionPane.showMessageDialog(null, "You did not enter a valid number, please try again.");

    }//end catch

}//end removeItemFromCart method


    //start of proceedToPayment method
    private static void proceedToPayment(double total) {

        //alerting user the total sum of items within cart in dollars. Prompting them if they'd like to continue to payment. (** FIRST TIME USING CONFIRM DIALOG REMEMBER TO DOUBLE CHECK THIS.)
        int confirmPayment = JOptionPane.showConfirmDialog(null, "Your total payment due is: $" + total + "\nWould you like to proceed to enter payment information?", "Payment", JOptionPane.YES_NO_OPTION);

        //did user choose yes?
        if (confirmPayment == JOptionPane.YES_OPTION) {

            //proceed to process payment if yes
            processPayment();

        }//end if yes

        //did user choose No?
        else {
            //alert the user the payment was cancelled
            JOptionPane.showMessageDialog(null, "Payment cancelled.");

        }//end if no

    }//end of proceed to payment


    //Start of addProductToCart method
    private static void addProductToCart() {

        //display products to user
        productManager.displayProductList();

        //prompt for the product identifier
        String itemIdentifier = JOptionPane.showInputDialog("Enter the product identifier of the item you'd like to add to your cart:");

    //start try-catch block
    try{

        //prompting the user to enter the quantity of te product that they'd like to add to their cart
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity you'd like to add to your cart: "));

        //attempt to find the product in the productlist using the item identifier the user input
        Product product = productManager.findProduct(itemIdentifier);

        //check if the product exists and the quantity provided is available
        if (product != null && product.getQuantity() >= quantity) {

            //if all is valid we will remove the amount in product list as its going to be in the users cart (much less complex than deducting after the user checks out)
            if (cartManager.addItemToCart(product, quantity)) {

                //decrease quantity in product list
                productManager.removeProductQuantity(itemIdentifier, quantity);

                //alert the user the addition of the item to their cart was successful
                JOptionPane.showMessageDialog(null, "Item was added to the user's cart successfully.");

            }//end 2nd if

        //if the operation fails due to there not being enough stock or an invalid identifier
        else {

                //alert the user the operation was a failure
                JOptionPane.showMessageDialog(null, "Failed to add item to cart.");

        }//end else

     }//check if product is not null and quantity of specified product is available

        else {

            //alert the user that not enough stock is available or the product may not exist.
            JOptionPane.showMessageDialog(null, "Not enough stock available or product does not exist / cannot be found. \n\nPlease double check the item identifier and try again. EX FORMAT: I1234");

        }//end else

    }//end try
    catch(NumberFormatException e){
        
        //alert the user they did not enter a valid number
        JOptionPane.showMessageDialog(null, "You did not enter a valid number. Please try again.");
    
    }//end catch

}//end of addProductToCart method


    //start of processPayment method
    private static void processPayment() {

        //prompting the user to input credit card information
        String cardType = JOptionPane.showInputDialog("Enter your card type (Visa, MasterCard, American Express, Discover):");
        String cardNumber = JOptionPane.showInputDialog("Enter your card number:");
        String expirationDate = JOptionPane.showInputDialog("Enter your card expiration date (MM/YY):");
        String cvv = JOptionPane.showInputDialog("Enter your card CVV:");

        //process and validate the payment using the provided card details
        boolean paymentSuccess = paymentManager.processPayment(cardType, cardNumber, expirationDate, cvv);

        //check if the payment was successful
        if (paymentSuccess) {

            //if successful alert user it was successful
            JOptionPane.showMessageDialog(null, "Payment successful! Thank you for your purchase.");

            //Clear the cart after successful payment
            cartManager.clearCart();

        }//end if sucessful payment
        
        //if the payment was not successful
        else {
            JOptionPane.showMessageDialog(null, "Payment failed. Please try again.");
        }//end else payment was not successful

    }//end processPayment method

}//end of class
