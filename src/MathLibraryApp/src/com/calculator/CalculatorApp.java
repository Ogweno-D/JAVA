package com.calculator;

import com.mathlib.MathLibrary;
import java.util.Scanner;

/**
 * A simple calculator application that performs basic arithmetic operations
 * and some additional mathematical functions like square, cube, and factorial.
 */
public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to something we can use Calculator!");

        System.out.print("Enter first number: ");
        double firstNumber = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double secondNumber = scanner.nextDouble();

        System.out.println("Choose an operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        // System.out.println("5. Square (first number)");
        // System.out.println("6. Cube (second number)");
        // System.out.println("7. Factorial (first number)");

        int choice = scanner.nextInt();
        double result;

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
            // case 5:
            //     result = MathLibrary.square(firstNumber);
            //     break;
            // case 6:
            //     result = MathLibrary.cube(secondNumber);
            //     break;
            // case 7:
            //     result = MathLibrary.factorial(firstNumber);
            //     break;
            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }

        System.out.println("Result: " + result);
        scanner.close();
    }
}