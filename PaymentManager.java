/**
 * Payment Manager Description:
 * 
 * This class is responsible for handling various aspects of payment processing in our system.
 * It includes functionality for validating credit card details and processing (fake) transactions.
 * 
 * Key Features:
 * - Validates credit card numbers for correctness.
 * - Handles the processing of payments.
 * 
 * 
 * 
 */


import javax.swing.JOptionPane;

//The PaymentManager class is responsible for handling various aspects of payment processing.
public class PaymentManager {

    //a constant to define the standard length of a credit card number.
    private static final int CARD_NUMBER_LENGTH = 16;

    //payment manager constructor
    public PaymentManager() {
        
    }//end of payment manager constructor



/*
 *  The following methods perform a series of validation tasks
 *  more information regarding what requirements our validation criteria uses for each method can be found at -
 * 
 *  https://moneytips.com/anatomy-of-a-credit-card/#:~:text=The%20first%20digit%20is%20different,a%207%20has%2015%20digits
 *
 * From the article: 
 * 
 * Visa cards begin with a 4 and have 13 or 16 digits
 * Mastercard cards begin with a 5 and has 16 digits
 * American Express cards begin with a 3, followed by a 4 or a 7 has 15 digits
 * Discover cards begin with a 6 and have 16 digits
 * 
 * 
 * 
 * 
 * 
 */

public boolean validateCreditCardType(String cardType, String cardNumber) {

    //validates that the card number is not null and meets the length requirements
    if (cardNumber == null || cardNumber.length() != CARD_NUMBER_LENGTH) {

        //alert the user that the card number length entered was invalid
        JOptionPane.showMessageDialog(null, "Invalid card number length.");
        
        return false;

    }//end if card number length is invalid check

    //rhe first digit of the card number is significant in determining the card type for our criteria
    char firstDigit = cardNumber.charAt(0);

    //The following conditions check the first digit of the card number against common standards from article
    if ("visa".equalsIgnoreCase(cardType) && firstDigit == '4') {

        //if valid visa
        return true;

    }//end of if card is visa

    else if ("mastercard".equalsIgnoreCase(cardType) && firstDigit == '5') {

        //if valid mastercard
        return true;

    }//end of mastercard requirements

    else if ("american express".equalsIgnoreCase(cardType) && firstDigit == '3') {

        //if valid american express
        return true;

    }//end of american express requirements

     else if ("discover".equalsIgnoreCase(cardType) && firstDigit == '6') {
        
        //if valid discover card
        return true;

    }//end of discover requirements

    //failure branch
     else {

        //If none of the conditions are met, the card type is either unsupported or does not match the card number provided.
        JOptionPane.showMessageDialog(null, "Unsupported card type or card number does not match card type.");

        return false;

    }//end of if the card type is unsupported or invalid

}//end of credit card validation method

//This method validates the credit card number itself, ensuring it consists only of digits and meets the length requirement.
public boolean validateCreditCardNumber(String cardNumber) {

    //checks for null and validates the length of the card number.
    if (cardNumber == null || cardNumber.length() != CARD_NUMBER_LENGTH) {

        //alerts the user the card length was invalid
        JOptionPane.showMessageDialog(null, "Invalid card number length.");

        return false;

    }//end of if check for length of card number

    //Iterates through each character in the card number to ensure each character is a digit since card numbers do not use characters
    for (char c : cardNumber.toCharArray()) {

        if (!Character.isDigit(c)) {

            //alert the user that they should not input characters for the card number
            JOptionPane.showMessageDialog(null, "Invalid card number. The card number should only contain digits.");

            //card considered invalid
            return false;

        }//end of credit card int check

    }//end of for loop for card int validation

    //if all checks pass, the card number is considered valid.
    return true;

}//end of validate credit card number method

//this method validates the expiration date of the credit card 
public boolean validateExpirationDate(String expirationDate) {

    //checks if the expiration date is null or does not match the standard MM/YY format.
    if (expirationDate == null || !expirationDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {

        //alert the user the expiration entered was invalid
        JOptionPane.showMessageDialog(null, "Invalid expiration date format.");

        //invalid check
        return false;

    }//end of invalid if statement

       //If the expiration date format is valid
        return true;

    }//end of expiration check method

    //method to validate the CVV 
    public boolean validateCVV(String cvv) {

        //checks if the CVV is null and whether it has a length of 3 or 4 digits
        if (cvv == null || !(cvv.length() == 3 || cvv.length() == 4)) {

            //alert the user the CVV format entered was invalid
            JOptionPane.showMessageDialog(null, "Invalid CVV format.");

            //check did not pass
            return false;

        }//end of validate CVV

        //recycled from previous card number validation
        for (char c : cvv.toCharArray()) {

            //if the character is not a digit
            if (!Character.isDigit(c)) {

                //alert the user that the CVV should only contain digits
                JOptionPane.showMessageDialog(null, "Invalid CVV. The CVV should only contain digits.");
              
                //check did not pass
                return false;

            }//end of invalid entry for not digits
            
        }//end of converting iteration

        //returns true if the CVV passes the validity checks.
        return true;

    }//end of cvv validation

    //the primary method for processing payments. This method combines all the validation steps and simulates a payment transaction.
    public boolean processPayment(String cardType, String cardNumber, String expirationDate, String cvv) {

        //validates the credit card type
        if (!validateCreditCardType(cardType, cardNumber)) {
            //term
            return false;
        }//end of cardtype valid

        //Validates the credit card number
        if (!validateCreditCardNumber(cardNumber)) {

            return false;

        }//end of number validation

        //validates the expiration date of the credit card
        if (!validateExpirationDate(expirationDate)) {

            //term
            return false;

        }//end of card expiration validation

        //validates the CVV of the credit card
        if (!validateCVV(cvv)) {

            //term
            return false;

        }//end of cvv validation

        //if all validations pass, simulate a successful transaction and display a confirmation message.
        JOptionPane.showMessageDialog(null, "Payment processed successfully.");

        //all is valid
        return true;

    }//end of process payment method

}//end paymentmanager class