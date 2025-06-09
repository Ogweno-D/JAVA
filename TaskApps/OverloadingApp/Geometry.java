
public  class Geometry {
 
    /**
     * Calculates the area of a circle given its radius.
     * @param radius the radius of the circle
     * @return the area of the circle
     */
    public static double area(double radius) {
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the area of a rectangle given its length and width.
     * @param length the length of the rectangle
     * @param width the width of the rectangle
     * @return the area of the rectangle
     */

    public static double area(double length, double width) {
        return length * width;
    }

    /**
     * Calculates the area of a triangle given its base and height.
     * @param base the base of the triangle
     * @param height the height of the triangle
     * @return the area of the triangle
     */
    public static double area(double base, double height, boolean isTriangle) {
        if (isTriangle) {
            return 0.5 * base * height;
        } else {
            throw new IllegalArgumentException("Invalid shape type for three parameters");
        }
    }

    /**
     * Calculates the area of a square given its side length.
     * @param side the length of the side of the square
     * @return the area of the square
     */
    public static double area(double side, boolean isSquare) {
        if (isSquare) {
            return side * side;
        } else {
            throw new IllegalArgumentException("Invalid shape type for two parameters");
        }
    }   
}