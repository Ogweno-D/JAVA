<!-- Java Tasks - No.10 -->
# Shop App

# Java Pass-by-Value Tutorial: Understanding Primitives vs Object References

## Introduction

Java is a **pass-by-value** language, which means that when you pass arguments to methods, Java passes copies of the values, not the original variables themselves. However, the behavior appears different when dealing with primitives versus object references, which can be confusing for many developers.

This tutorial will demonstrate these concepts using a `Product` class with three different scenarios:

1. Attempting to modify a primitive value
2. Modifying an object through its reference
3. Reassigning an object reference

## The Product Class

Let's start by creating our `Product` class:

```java
public class Product {
    private String productName;
    private double price;
    
    // Default constructor
    public Product() {
        this.productName = "";
        this.price = 0.0;
    }
    
    // Parameterized constructor
    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }
    
    // Getters
    public String getProductName() {
        return productName;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // toString method for easy display
    @Override
    public String toString() {
        return "Product{name='" + productName + "', price=" + price + "}";
    }
}
```

## Pass-by-Value Demonstration Methods

Now let's create a separate class to demonstrate the three different scenarios:

```java
public class PassByValueDemo {
    
    // Method 1: Attempting to update a primitive value
    public static void updatePrice(double price) {
        System.out.println("Inside updatePrice method:");
        System.out.println("  Original price parameter: " + price);
        
        price = 99.99; // This only changes the local copy
        
        System.out.println("  Modified price parameter: " + price);
        System.out.println("  (This is only the local copy!)");
    }
    
    // Method 2: Modifying object through its reference
    public static void updateProduct(Product product) {
        System.out.println("Inside updateProduct method:");
        System.out.println("  Original product parameter: " + product);
        
        // These changes affect the original object
        product.setProductName("Updated Laptop");
        product.setPrice(1299.99);
        
        System.out.println("  Modified product parameter: " + product);
        System.out.println("  (Changes made to the actual object!)");
    }
    
    // Method 3: Reassigning object reference
    public static void reassignProduct(Product product) {
        System.out.println("Inside reassignProduct method:");
        System.out.println("  Original product parameter: " + product);
        
        // This only changes the local reference copy
        product = new Product("Brand New Phone", 899.99);
        
        System.out.println("  Reassigned product parameter: " + product);
        System.out.println("  (This is only a new local reference!)");
    }
    
    // Main method to demonstrate all scenarios
    public static void main(String[] args) {
        System.out.println("=== JAVA PASS-BY-VALUE DEMONSTRATION ===\n");
        
        // Scenario 1: Pass-by-value with primitives
        System.out.println("SCENARIO 1: Attempting to modify a primitive value");
        System.out.println("----------------------------------------------------");
        
        double originalPrice = 49.99;
        System.out.println("Before calling updatePrice:");
        System.out.println("  originalPrice = " + originalPrice);
        
        updatePrice(originalPrice);
        
        System.out.println("After calling updatePrice:");
        System.out.println("  originalPrice = " + originalPrice);
        System.out.println("  *** Notice: The original value is UNCHANGED! ***\n");
        
        // Scenario 2: Pass-by-value with object references (modifying object)
        System.out.println("SCENARIO 2: Modifying object through reference");
        System.out.println("----------------------------------------------");
        
        Product originalProduct = new Product("Gaming Laptop", 999.99);
        System.out.println("Before calling updateProduct:");
        System.out.println("  originalProduct = " + originalProduct);
        
        updateProduct(originalProduct);
        
        System.out.println("After calling updateProduct:");
        System.out.println("  originalProduct = " + originalProduct);
        System.out.println("  *** Notice: The original object IS CHANGED! ***\n");
        
        // Scenario 3: Pass-by-value with object references (reassigning reference)
        System.out.println("SCENARIO 3: Reassigning object reference");
        System.out.println("---------------------------------------");
        
        Product anotherProduct = new Product("Old Tablet", 299.99);
        System.out.println("Before calling reassignProduct:");
        System.out.println("  anotherProduct = " + anotherProduct);
        
        reassignProduct(anotherProduct);
        
        System.out.println("After calling reassignProduct:");
        System.out.println("  anotherProduct = " + anotherProduct);
        System.out.println("  *** Notice: The original reference is UNCHANGED! ***\n");
        
        // Summary explanation
        printSummary();
    }
    
    private static void printSummary() {
        System.out.println("=== SUMMARY OF JAVA PASS-BY-VALUE BEHAVIOR ===");
        System.out.println("1. PRIMITIVES: Changes to parameter values don't affect original variables");
        System.out.println("2. OBJECTS: Changes to object contents DO affect the original object");
        System.out.println("3. REFERENCES: Reassigning the reference parameter doesn't affect the original reference");
        System.out.println("\nRemember: Java ALWAYS passes copies of values, but for objects,");
        System.out.println("the 'value' being copied is the reference (memory address) to the object!");
    }
}
```

## Expected Output

When you run this program, you'll see output like this:

```
=== JAVA PASS-BY-VALUE DEMONSTRATION ===

SCENARIO 1: Attempting to modify a primitive value
----------------------------------------------------
Before calling updatePrice:
  originalPrice = 49.99
Inside updatePrice method:
  Original price parameter: 49.99
  Modified price parameter: 99.99
  (This is only the local copy!)
After calling updatePrice:
  originalPrice = 49.99
  *** Notice: The original value is UNCHANGED! ***

SCENARIO 2: Modifying object through reference
----------------------------------------------
Before calling updateProduct:
  originalProduct = Product{name='Gaming Laptop', price=999.99}
Inside updateProduct method:
  Original product parameter: Product{name='Gaming Laptop', price=999.99}
  Modified product parameter: Product{name='Updated Laptop', price=1299.99}
  (Changes made to the actual object!)
After calling updateProduct:
  originalProduct = Product{name='Updated Laptop', price=1299.99}
  *** Notice: The original object IS CHANGED! ***

SCENARIO 3: Reassigning object reference
---------------------------------------
Before calling reassignProduct:
  anotherProduct = Product{name='Old Tablet', price=299.99}
Inside reassignProduct method:
  Original product parameter: Product{name='Old Tablet', price=299.99}
  Reassigned product parameter: Product{name='Brand New Phone', price=899.99}
  (This is only a new local reference!)
After calling reassignProduct:
  anotherProduct = Product{name='Old Tablet', price=299.99}
  *** Notice: The original reference is UNCHANGED! ***

=== SUMMARY OF JAVA PASS-BY-VALUE BEHAVIOR ===
1. PRIMITIVES: Changes to parameter values don't affect original variables
2. OBJECTS: Changes to object contents DO affect the original object
3. REFERENCES: Reassigning the reference parameter doesn't affect the original reference

Remember: Java ALWAYS passes copies of values, but for objects,
the 'value' being copied is the reference (memory address) to the object!
```

## Key Concepts Explained

### 1. Primitive Pass-by-Value

When you pass a primitive value (like `double price`) to a method:

- Java creates a **copy** of the value
- Changes to the parameter only affect the local copy
- The original variable remains unchanged

### 2. Object Reference Pass-by-Value

When you pass an object to a method:

- Java creates a **copy of the reference** (memory address)
- Both the original and copy point to the **same object** in memory
- Changes to the object's properties affect the original object
- This is why it might seem like "pass-by-reference," but it's still pass-by-value!

### 3. Reference Reassignment

When you reassign an object parameter inside a method:

- You're only changing the **local copy** of the reference
- The original reference still points to the original object
- The reassignment has no effect on the caller's variable

## Memory Visualization

```
SCENARIO 2 (Object Modification):
Before method call:
originalProduct → [Gaming Laptop, 999.99] (Object in memory)

Inside method:
originalProduct → [Gaming Laptop, 999.99] (Object in memory) ← product (copy of reference)
                  [Updated Laptop, 1299.99] (Same object, modified)

After method call:
originalProduct → [Updated Laptop, 1299.99] (Same object, now modified)

SCENARIO 3 (Reference Reassignment):
Before method call:
anotherProduct → [Old Tablet, 299.99] (Object A in memory)

Inside method:
anotherProduct → [Old Tablet, 299.99] (Object A in memory)
product → [Brand New Phone, 899.99] (Object B in memory, newly created)

After method call:
anotherProduct → [Old Tablet, 299.99] (Object A unchanged)
(Object B will be garbage collected)
```

## Common Misconceptions

1. **"Java is pass-by-reference for objects"** - FALSE! Java is always pass-by-value, but for objects, the value being passed is a copy of the reference.

2. **"I can change the caller's object reference"** - FALSE! You can only modify the object that the reference points to, not the reference itself.

3. **"Primitives and objects behave the same way"** - FALSE! While both use pass-by-value, the practical effects are different due to what the "value" represents.

## Practical Applications

Understanding these concepts is crucial for:

- **Method design**: Knowing when your methods will or won't affect the caller's data
- **Immutability**: Understanding why reassignment doesn't make objects immutable
- **Performance**: Knowing that object passing doesn't copy the entire object
- **Debugging**: Understanding why some changes persist and others don't

## Conclusion

Java's pass-by-value mechanism is consistent but can appear confusing when dealing with objects. The key insight is that for objects, the "value" being passed is the reference (memory address) to the object, not the object itself. This allows methods to modify object contents but prevents them from changing the caller's reference variables.

Remember: **Java always passes copies, but for objects, it copies the reference, not the object!**
