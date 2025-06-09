# Java Collections Framework: Lists, Sets, and Maps

This document provides a comprehensive overview of the core components of the Java Collections Framework, focusing on Lists, Sets, and Maps - essential data structures for storing and manipulating groups of objects in Java applications.

## Table of Contents

- [Lists](#lists)
- [Sets](#sets)
- [Maps](#maps)
- [Comparison Table](#comparison-table)
- [Implementation Guide](#implementation-guide)
- [Thread Safety](#thread-safety)
- [Getting Started](#getting-started)

---

## Lists

A **List** is an ordered collection that allows duplicate elements with indexed access (0-based).

### 🔑 Key Features

- ✅ Maintains insertion order
- ✅ Allows duplicate elements
- ✅ Indexed access (0-based)
- ✅ Can contain null elements (implementation-dependent)

### 📋 Common Implementations

| Implementation | Description | Best Use Case |
|----------------|-------------|---------------|
| **ArrayList** | Dynamic array-based list | Fast random access and iteration |
| **LinkedList** | Doubly-linked list | Frequent insertions/deletions |
| **Vector** | Synchronized ArrayList | Thread-safe operations (legacy) |

### 🛠️ Common Methods

```java
List<String> list = new ArrayList<>();

// Adding elements
list.add("Apple");           // Adds element to the end
list.add(0, "Orange");       // Adds element at specific index

// Accessing elements
String item = list.get(0);   // Gets element at index 0
int size = list.size();      // Returns number of elements

// Modifying elements
list.set(1, "Banana");       // Replaces element at index 1
list.remove(0);              // Removes element at index 0
list.remove("Apple");        // Removes first occurrence of "Apple"

// Checking elements
boolean exists = list.contains("Banana");  // Checks if element exists
boolean empty = list.isEmpty();            // Checks if list is empty
```

### 💡 Example Usage

```java
import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple");  // Duplicates allowed
        
        System.out.println(fruits);  // Output: [Apple, Banana, Apple]
        
        // Accessing by index
        System.out.println("First fruit: " + fruits.get(0));
        
        // Iterating through list
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }
    }
}
```

---

## Sets

A **Set** is a collection that does not allow duplicate elements, modeling the mathematical set abstraction.

### 🔑 Key Features

- ❌ No duplicate elements (uses `equals()` to check uniqueness)
- ❓ Order depends on implementation
- ✅ Can contain null elements (implementation-dependent)

### 📋 Common Implementations

| Implementation | Description | Ordering | Performance |
|----------------|-------------|----------|-------------|
| **HashSet** | Hash table-based | No order guaranteed | O(1) average |
| **LinkedHashSet** | Hash table + linked list | Insertion order | O(1) average |
| **TreeSet** | Red-black tree | Sorted order | O(log n) |

### 🛠️ Common Methods

```java
Set<String> set = new HashSet<>();

// Adding elements
set.add("Apple");            // Adds element if not present
boolean added = set.add("Banana");  // Returns true if added

// Removing elements
set.remove("Apple");         // Removes element
set.clear();                 // Removes all elements

// Checking elements
boolean exists = set.contains("Apple");  // Checks if element exists
boolean empty = set.isEmpty();           // Checks if set is empty
int size = set.size();                   // Returns number of elements
```

### 💡 Example Usage

```java
import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<String> uniqueFruits = new HashSet<>();
        
        // Adding elements
        uniqueFruits.add("Apple");
        uniqueFruits.add("Banana");
        uniqueFruits.add("Apple");  // Duplicate ignored
        
        System.out.println(uniqueFruits);  // Output: [Apple, Banana] (order not guaranteed)
        
        // Set operations
        Set<String> otherFruits = Set.of("Cherry", "Apple");
        
        // Union
        Set<String> allFruits = new HashSet<>(uniqueFruits);
        allFruits.addAll(otherFruits);
        System.out.println("Union: " + allFruits);
        
        // Intersection
        uniqueFruits.retainAll(otherFruits);
        System.out.println("Intersection: " + uniqueFruits);
    }
}
```

---

## Maps

A **Map** stores key-value pairs where each key is unique. While not implementing the Collection interface, it's a core part of the Collections Framework.

### 🔑 Key Features

- 🔑 Stores key-value pairs
- ✅ Keys are unique; values can be duplicated
- ❓ Iteration order depends on implementation
- ✅ Keys/values can be null (implementation-dependent)

### 📋 Common Implementations

| Implementation | Description | Ordering | Thread-Safe |
|----------------|-------------|----------|-------------|
| **HashMap** | Hash table-based | No order guaranteed | ❌ |
| **LinkedHashMap** | Hash table + linked list | Insertion order | ❌ |
| **TreeMap** | Red-black tree | Sorted by keys | ❌ |
| **Hashtable** | Synchronized HashMap | No order guaranteed | ✅ |

### 🛠️ Common Methods

```java
Map<String, Integer> map = new HashMap<>();

// Adding/updating key-value pairs
map.put("Apple", 1);         // Associates key with value
map.putIfAbsent("Banana", 2); // Adds only if key doesn't exist

// Accessing values
Integer value = map.get("Apple");           // Gets value for key
Integer defaultValue = map.getOrDefault("Orange", 0);  // Gets value or default

// Removing entries
map.remove("Apple");         // Removes key-value pair
map.clear();                 // Removes all entries

// Checking contents
boolean hasKey = map.containsKey("Apple");    // Checks if key exists
boolean hasValue = map.containsValue(1);      // Checks if value exists
boolean empty = map.isEmpty();                // Checks if map is empty

// Getting collections
Set<String> keys = map.keySet();              // Gets all keys
Collection<Integer> values = map.values();    // Gets all values
Set<Map.Entry<String, Integer>> entries = map.entrySet();  // Gets key-value pairs
```

### 💡 Example Usage

```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> fruitCount = new HashMap<>();
        
        // Adding key-value pairs
        fruitCount.put("Apple", 5);
        fruitCount.put("Banana", 3);
        fruitCount.put("Apple", 7);  // Overwrites previous value
        
        System.out.println(fruitCount);  // Output: {Apple=7, Banana=3}
        
        // Iterating through map
        for (Map.Entry<String, Integer> entry : fruitCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Using Java 8 forEach
        fruitCount.forEach((fruit, count) -> 
            System.out.println(fruit + " -> " + count));
    }
}
```

---

## Comparison Table

| Feature | List | Set | Map |
|---------|------|-----|-----|
| **Duplicates** | ✅ Allowed | ❌ Not allowed | Keys: ❌, Values: ✅ |
| **Order** | ✅ Insertion order | Depends on implementation | Depends on implementation |
| **Access Method** | By index (0-based) | By element | By key |
| **Null Elements** | ✅ Allowed* | ✅ Allowed* | ✅ Allowed* |
| **Common Use Case** | Ordered sequences | Unique elements | Key-value associations |
| **Interface** | `Collection` | `Collection` | Separate hierarchy |

*Implementation-dependent

---

## Implementation Guide

### 🎯 Choosing the Right Implementation

#### For Lists

- **ArrayList**: General-purpose, random access needed
- **LinkedList**: Frequent insertions/deletions at beginning/middle
- **Vector**: Thread-safety required (consider alternatives)

#### For Sets

- **HashSet**: Fast operations, order doesn't matter
- **LinkedHashSet**: Need insertion order preserved
- **TreeSet**: Need sorted elements, NavigableSet operations

#### For Maps

- **HashMap**: Fast key-value lookups, order doesn't matter
- **LinkedHashMap**: Need insertion/access order preserved
- **TreeMap**: Need sorted keys, NavigableMap operations
- **ConcurrentHashMap**: Thread-safe operations

### 📦 Import Statement

```java
import java.util.*;  // Imports all collection classes
// Or import specific classes
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
```

---

## Thread Safety

⚠️ **Important**: Most collection implementations are **not thread-safe** by default.

### 🔒 Thread-Safe Options

1. **Synchronized Wrappers**:

   ```java
   List<String> syncList = Collections.synchronizedList(new ArrayList<>());
   Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());
   Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
   ```

2. **Concurrent Collections**:

   ```java
   Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
   ```

3. **Legacy Thread-Safe Classes**:

   ```java
   Vector<String> vector = new Vector<>();  // Synchronized ArrayList
   Hashtable<String, Integer> hashtable = new Hashtable<>();  // Synchronized HashMap
   ```

---

## Getting Started

### 🚀 Quick Start Example

```java
import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        // List example
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript");
        System.out.println("Languages: " + languages);
        
        // Set example
        Set<Integer> uniqueNumbers = new HashSet<>(Arrays.asList(1, 2, 2, 3, 3, 3));
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // Map example
        Map<String, String> capitals = Map.of(
            "USA", "Washington D.C.",
            "France", "Paris",
            "Japan", "Tokyo"
        );
        System.out.println("Capitals: " + capitals);
    }
}
```

### 📚 Additional Resources

- [Oracle Java Collections Tutorial](https://docs.oracle.com/javase/tutorial/collections/)
- [Java Collections Framework Documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/doc-files/coll-overview.html)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/) - Items 28-34

---

## Contributing

Found an error or want to add more examples? Feel free to:

1. 🍴 Fork the repository
2. 🔧 Make your changes
3. 📝 Submit a pull request

---

## License

This documentation is provided under the [MIT License](LICENSE).

---

**Happy Coding!** 🎉
