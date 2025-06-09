# METHOD OVERLOADING

## Method overloading

This project demonstrates *method overloading* in Java by providing

- The Geometry class with:
  - static methods to calculate the area of  different geometric shapes,
    circles, rectangles, triangles, and squares.

- The StringFormatter class with:
  - Static methods to format strings with different ways of formatting a string
  
### Geometry

#### Features

- Area of a Circle
  
  ```java
  Geometry.area(double radius)
  ```

- Area of a Rectangle
  
  ```java
  Geometry.area(double length, double width)
  ```

- Area of a Triangle
  
  ```java
  Geometry.area(double base, double height, boolean isTriangle)
  ```
  
  Set `isTriangle` to `true` to calculate the area of a triangle

- Area of a Triangle
  
  ```java
  Geometry.area(double side , boolean isSquare)
  ```
  
  Set `isSquare` to `true` to calculate the area of a triangles

#### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Circle area: " + Geometry.area(5.0));
        System.out.println("Rectangle area: " + Geometry.area(4.0, 6.0));
        System.out.println("Triangle area: " + Geometry.area(3.0, 8.0, true));
        System.out.println("Square area: " + Geometry.area(4.0, true));
    }
}
```

- **Notes**
  - Passing `false` to the `boolean` `parameters` will throw an `IllegalArgumentException`.
  
  - All methods are `static` and can be called without creating an instance of Geometry

### StringFormatter

#### Features

- **Capitalize First Letter :**
  
  ```java
  StringFormatter.format(String input)
  ```

  Capitalizes the first letter of the input string.

- **Repeat String :**
  
  ```java
  StringFormatter.format(String input, int repeat)
  ```

  Repeat the input string `repeat` number of times.

- **Add Prefix and Suffix**
  
  ```java
  StringFormatter.format(String input, String prefix, String suffix)
  ```

  Add a `prefix` and `suffix` to the inputed string.

#### Example Usage
  
  ```java
    public class Main {
        public static void main(String[] args) {
            System.out.println(StringFormatter.format("hello")); // Output: Hello
            System.out.println(StringFormatter.format("abc", 3)); // Output: abcabcabc
            System.out.println(StringFormatter.format("world", ">>", "<<")); // Output: >>world<<
        }
    }
 ```
  
- **Notes**
  - Passing `null` or `invalid` arguments will throw an `IllegalArgumentException`.
  
  - All methods are `static` and can be called without creating an instance of Geometry

  
