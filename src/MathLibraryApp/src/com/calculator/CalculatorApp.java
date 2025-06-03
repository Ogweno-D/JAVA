package com.calculator;

import com.mathlib.MathLibrary;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A secure and robust calculator application that performs basic arithmetic operations
 * and handles edge cases and input validation.
 */
public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to something we can use Calculator!");

        double firstNumber = readDouble(scanner, "Enter first number: ");
        double secondNumber = readDouble(scanner, "Enter second number: ");

        System.out.println("Choose an operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");

        int choice = readIntInRange(scanner, "Enter your choice (From 1 to 4): ", 1, 4);
        double result;

        try {
            switch (choice) {
                case 1:
                    result = MathLibrary.add(firstNumber, secondNumber);
                    break;
                case 2:
                    result = MathLibrary.subtract(firstNumber, secondNumber);
                    break;
                case 3:
                    result = MathLibrary.multiply(firstNumber, secondNumber);
                    break;
                case 4:
                    result = MathLibrary.divide(firstNumber, secondNumber);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }

            if (Double.isInfinite(result)) {
                System.out.println("⚠️ Result is infinite. This may indicate overflow or division by zero.");
            } else if (Double.isNaN(result)) {
                System.out.println("⚠️ Result is not a number (NaN).");
            } else {
                System.out.println("✅ Result: " + result);
            }

        } catch (ArithmeticException ex) {
            System.out.println("🚫 Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("🚫 Unexpected error: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Safely reads a double from the user with input validation.
     */
    private static double readDouble(Scanner scanner, String prompt) {
        double number = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                number = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Please enter a valid numeric value.");
            }
        }
        return number;
    }

    /**
     * Reads an integer input from user within a specified range.
     */
    private static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.println("❌ Choice out of range. Please select between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    // File path sanitization example (future-proofing, not used in current context)
    public static String sanitizePath(String inputPath) {
        return inputPath.replaceAll("[^a-zA-Z0-9._/-]", "");
    }
}


// package com.calculator;

// import com.mathlib.MathLibrary;
// import java.util.Scanner;

// /**
//  * A simple calculator application that performs basic arithmetic operations
//  * and some additional mathematical functions like square, cube, and factorial.
//  */
// public class CalculatorApp {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Welcome to something we can use Calculator!");

//         System.out.print("Enter first number: ");
//         double firstNumber = scanner.nextDouble();

//         System.out.print("Enter second number: ");
//         double secondNumber = scanner.nextDouble();

//         System.out.println("Choose an operation:");
//         System.out.println("1. Add");
//         System.out.println("2. Subtract");
//         System.out.println("3. Multiply");
//         System.out.println("4. Divide");
//         // System.out.println("5. Square (first number)");
//         // System.out.println("6. Cube (second number)");
//         // System.out.println("7. Factorial (first number)");

//         int choice = scanner.nextInt();
//         double result;

//         switch (choice) {
//             case 1:
//                 result = MathLibrary.add(firstNumber, secondNumber);
//                 break;
//             case 2:
//                 result = MathLibrary.subtract(firstNumber, secondNumber);
//                 break;
//             case 3:
//                 result = MathLibrary.multiply(firstNumber, secondNumber);
//                 break;
//             case 4:
//                 result = MathLibrary.divide(firstNumber, secondNumber);
//                 break;
//             // case 5:
//             //     result = MathLibrary.square(firstNumber);
//             //     break;
//             // case 6:
//             //     result = MathLibrary.cube(secondNumber);
//             //     break;
//             // case 7:
//             //     result = MathLibrary.factorial(firstNumber);
//             //     break;
//             default:
//                 System.out.println("Invalid choice.");
//                 scanner.close();
//                 return;
//         }

//         System.out.println("Result: " + result);
//         scanner.close();
//     }
// }