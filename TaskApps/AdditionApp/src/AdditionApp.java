// A start maybe

import java.util.Scanner;

// public class AdditionApp {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the first number: ");
//         int firstNumber = scanner.nextInt();

//         System.out.print("Enter the second number: ");
//         int secondNumber = scanner.nextInt();

//         int sum = firstNumber + secondNumber;

//         System.out.println("The sum of " + firstNumber + " and " + secondNumber + " is: " + sum);

//         scanner.close();
//     }
// }

public class AdditionApp{
    public static void main (String [] args){
        // Prompt the user to enter the first number
        try (Scanner scanner = new Scanner (System.in)) {
            // Prompt the user to enter the first number
            System.out.print("Enter the first number  ");
            
            double firstNumber  = scanner.nextDouble();
            
            // Prompt the user to enter the second number
            System.out.print("Enter the second number  ");
            
            double secondNumber = scanner.nextDouble();
            
            // Calculate the sum of the two numbers
            
            double sum  = firstNumber + secondNumber;
            
            // Display the result
            System.out.println("The sum of " + firstNumber + " and " + secondNumber + " is: " + sum);
        }
    }
}


// public class AdditionAppTest {
//     public static void main(String[] args) {
//         // This is a simple test for the AdditionApp
//         System.out.println("Running AdditionAppTest...");

//         // Normally, you would use a testing framework like JUnit for real tests
//         // Here we just run the main method to see if it works
//         AdditionApp.main(new String[]{});

//         System.out.println("Test completed.");
//     }
// }