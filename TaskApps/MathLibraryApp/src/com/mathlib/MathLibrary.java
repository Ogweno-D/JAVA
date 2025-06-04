package com.mathlib;

public class MathLibrary {

    public static double add(double a, double b) {
        double result = a + b;
        validateResult(result, "Addition");
        return result;
    }

    public static double subtract(double a, double b) {
        double result = a - b;
        validateResult(result, "Subtraction");
        return result;
    }

    public static double multiply(double a, double b) {
        double result = a * b;
        validateResult(result, "Multiplication");
        return result;
    }

    public static double divide(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Inputs cannot be NaN.");
        }
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        double result = a / b;
        validateResult(result, "Division");
        return result;
    }

    public static double square(double number) {
        double result = number * number;
        validateResult(result, "Square");
        return result;
    }

    public static double cube(double number) {
        double result = number * number * number;
        validateResult(result, "Cube");
        return result;
    }

    public static double factorial(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }

        if (number != (int) number) {
            throw new IllegalArgumentException("Factorial is only defined for whole numbers.");
        }

        if (number > 170) {
            // Factorial of numbers > 170 overflows double
            throw new ArithmeticException("Factorial result overflows double. Input too large.");
        }

        double result = 1;
        for (int i = 2; i <= (int) number; i++) {
            result *= i;
        }

        validateResult(result, "Factorial");
        return result;
    }

    public static String getVersion() {
        return "MathLibrary version 1.0 (2025-06-02)";
    }

    // Private helper to validate results
    private static void validateResult(double result, String operationName) {
        if (Double.isNaN(result)) {
            throw new ArithmeticException(operationName + " result is NaN.");
        }
        if (Double.isInfinite(result)) {
            throw new ArithmeticException(operationName + " result is infinite.");
        }
    }
}


/**
 * MathLibrary provides basic mathematical operations such as addition, subtraction,
 */
// package com.mathlib;

// /**
//  * MathLibrary provides basic mathematical operations such as addition, subtraction,
//  * multiplication, division, square, cube, and factorial.
//  */

// public class MathLibrary {

//     /**
//      * Adds two double numbers
//      * @param a first number
//      * @param b second number
//      * @return sum of a and b
//      */
//     public static double add(double a, double b) {
//         return a + b;
//     }
    
//     /**
//      * Subtracts second number from first number
//      * @param a first number (minuend)
//      * @param b second number (subtrahend)
//      * @return difference of a and b
//      */
//     public static double subtract(double a, double b) {
//         return a - b;
//     }
    
//     /**
//      * Multiplies two double numbers
//      * @param a first number
//      * @param b second number
//      * @return product of a and b
//      */
//     public static double multiply(double a, double b) {
//         return a * b;
//     }
    
//     /**
//      * Divides first number by second number
//      * @param a dividend
//      * @param b divisor
//      * @return quotient of a divided by b
//      * @throws ArithmeticException if divisor is zero
//      */
//     public static double divide(double a, double b) {
//         if (b == 0) {
//             throw new ArithmeticException("Division by zero is not allowed");
//         }
//         return a / b;
//     }

//     // Method to calculate the square of a number
//     public static double square(double number) {
//         return number * number;
//     }

//     // Method to calculate the cube of a number
//     public static double cube(double number) {
//         return number * number * number;
//     }

//     // Method to calculate the factorial of a number
//     public static double factorial(double number) {
//         if (number == 0 || number == 1) {
//             return 1;
//         }
//         double result = 1;
//         for (double i = 2; i <= number; i++) {
//             result *= i;
//         }
//         return result;
//     }

//     /**
//      * Gets the version of the MathLibrary.
//      * @return the version string of the MathLibrary.
//      * @since 1.0
//      * 
//      */
//     public static String getVersion() {
//         return "MathLibrary version 1.0";
//     }
// }