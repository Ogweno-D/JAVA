package com.mathlib;

/**
 * MathLibrary provides basic mathematical operations such as addition, subtraction,
 * multiplication, division, square, cube, and factorial.
 */

public class MathLibrary {

    /**
     * Adds two double numbers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public static double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Subtracts second number from first number
     * @param a first number (minuend)
     * @param b second number (subtrahend)
     * @return difference of a and b
     */
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplies two double numbers
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Divides first number by second number
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws ArithmeticException if divisor is zero
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    // Method to calculate the square of a number
    public static double square(double number) {
        return number * number;
    }

    // Method to calculate the cube of a number
    public static double cube(double number) {
        return number * number * number;
    }

    // Method to calculate the factorial of a number
    public static double factorial(double number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        double result = 1;
        for (double i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Gets the version of the MathLibrary.
     * @return the version string of the MathLibrary.
     * @since 1.0
     * 
     */
    public static String getVersion() {
        return "MathLibrary version 1.0";
    }
}