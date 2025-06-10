
import java.util.Scanner;

public class InputValidator {

    public static void main(String[] args) {
               
        try ( 
            
            // Validate a user input to ensure it is a valid integer
            
            Scanner scanner = new Scanner(System.in)) {
            boolean validInput = false;
            
            while (!validInput) { 
                try {
                    System.out.println("Enter an integer");

                    int number = scanner.nextInt();

                    // Indicator of Validity
                    int square = number *number;
                    System.out.println("The square of " + number + " is: " + square);

                    // Turn the flag to on
                    validInput = true;
                    
                } catch (Exception e) {
                    System.out.println("Well, your code was exceptional in all the wrong ways! >< \n " + e);
                    e.printStackTrace();

                    scanner.nextLine();
                }                
            }
        }

       }
    }
