public  class Main{
    public static void main(String[] args) {
        // Test the area method for a circle
        double circleArea = Geometry.area(5.0);
        System.out.println("Area of the circle: " + circleArea);

        // Test the area method for a rectangle
        double rectangleArea = Geometry.area(4.0, 6.0);
        System.out.println("Area of the rectangle: " + rectangleArea);

        // Test the area method for a triangle
        double triangleArea = Geometry.area(3.0, 4.0, true);
        System.out.println("Area of the triangle: " + triangleArea);

        // Test the area method for a square
        double squareArea = Geometry.area(4.0, true);
        System.out.println("Area of the square: " + squareArea);


        // Test the format method for a string with capitalization
        String formattedString1 = StringFormatter.format("hello");
        System.out.println("Formatted string (capitalized): " + formattedString1);
        // Test the format method for repeating a string
        String formattedString2 = StringFormatter.format("hello", 3);
        System.out.println("Formatted string (repeated): " + formattedString2);
        // Test the format method for adding prefix and suffix
        String formattedString3 = StringFormatter.format("wide", "Big ", " World");
        System.out.println("Formatted string (with prefix and suffix): " + formattedString3);

        // Test the format method with null input
        try {
            String formattedString4 = StringFormatter.format(null, "Prefix: ", " :Suffix");
            System.out.println("Formatted string (with null input): " + formattedString4);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        // Test the format method with null prefix
        try {
            String formattedString5 = StringFormatter.format("test", null, " :Suffix");
            System.out.println("Formatted string (with null prefix): " + formattedString5);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}