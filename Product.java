/**
 * Product Description:
 * 
 * This class represents a product in the commerce system. It encapsulates all the necessary details of a product,
 * including its name, description, price, quantity, item identifier, and whether it is a service or a physical product.
 * 
 * 
 * Key Features:
 * 
 * - Handles various attributes of a product such as name, description, price, etc.
 * - Allows identification of products using a unique item identifier.
 * - Distinguishes between services and physical products.
 * 
 * 
 */
    public class Product {

        private String name;
        private String description;
        private double price;
        private int quantity;
        private String itemIdentifier; //format: I + 4 digits
        private boolean isService;



    
        //Constructor to initialize a Product object.
        public Product(String name, String description, double price, int quantity, String itemIdentifier, boolean isService) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
            this.itemIdentifier = itemIdentifier;
            this.isService = isService;
        }


        //Getters
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }


        public double getPrice() {
            return price;
        }


        public int getQuantity() {
            return quantity;
        }

        public String getItemIdentifier() {
            return itemIdentifier;
        }

        public boolean isService() {
            return isService;
        }





        //Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setItemIdentifier(String itemIdentifier) {
            this.itemIdentifier = itemIdentifier;
        }

        public void setIsService(boolean isService) {
            this.isService = isService;
        }


        //m ethod to validate the item identifier format
        public static boolean validateItemIdentifier(String itemIdentifier) {

            return itemIdentifier.matches("I\\d{4}");

        }//end of item identifier validation

        //method to update the quantity of the product
        public void updateQuantity(int quantityChange) {
            if (!isService) {
                this.quantity += quantityChange;
            }

        }//end of update quantity

    }//end of product class
