# Java Collection Framework Tutorial

## Introduction

The Java Collection Framework is a unified architecture for representing and manipulating collections of objects. It provides a set of interfaces, implementations, and algorithms that make it easier to work with groups of objects in Java programs.

## Core Interfaces Hierarchy

```
Collection (interface)
├── List (interface)
├── Set (interface)
└── Queue (interface)

Map (interface) - separate hierarchy
```

## 1. List Interface

Lists are ordered collections that allow duplicate elements and provide indexed access.

### ArrayList

ArrayList is a resizable array implementation that provides fast random access but slower insertion/deletion in the middle.

```java
import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        // Creating an ArrayList
        List<String> fruits = new ArrayList<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicates allowed
        
        System.out.println("Fruits: " + fruits);
        // Output: Fruits: [Apple, Banana, Orange, Apple]
        
        // Accessing elements by index
        System.out.println("First fruit: " + fruits.get(0));
        
        // Modifying elements
        fruits.set(1, "Mango");
        System.out.println("After modification: " + fruits);
        
        // Removing elements
        fruits.remove("Apple"); // Removes first occurrence
        fruits.remove(0); // Removes by index
        System.out.println("After removal: " + fruits);
        
        // Iterating through the list
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }
    }
}
```

### LinkedList

LinkedList uses a doubly-linked list structure, providing efficient insertion/deletion but slower random access.

```java
import java.util.*;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<>();
        
        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.addFirst(5);  // Add at beginning
        numbers.addLast(30);  // Add at end
        
        System.out.println("Numbers: " + numbers);
        // Output: Numbers: [5, 10, 20, 30]
        
        // LinkedList as Queue operations
        numbers.offer(40);    // Add to tail
        Integer first = numbers.poll(); // Remove from head
        System.out.println("Removed: " + first);
        System.out.println("After poll: " + numbers);
        
        // LinkedList as Stack operations
        numbers.push(100);    // Add to head
        Integer top = numbers.pop(); // Remove from head
        System.out.println("Popped: " + top);
        System.out.println("Final: " + numbers);
    }
}
```

### Vector (Legacy)

Vector is similar to ArrayList but synchronized (thread-safe). Generally, ArrayList is preferred for single-threaded applications.

```java
import java.util.*;

public class VectorExample {
    public static void main(String[] args) {
        Vector<String> colors = new Vector<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        
        // Vector-specific methods
        colors.addElement("Yellow");
        System.out.println("Capacity: " + colors.capacity());
        System.out.println("Size: " + colors.size());
        System.out.println("Colors: " + colors);
    }
}
```

## 2. Set Interface

Sets are collections that don't allow duplicate elements.

### HashSet

HashSet uses hash table for storage, providing O(1) average time complexity for basic operations.

```java
import java.util.*;

public class HashSetExample {
    public static void main(String[] args) {
        Set<String> uniqueNames = new HashSet<>();
        
        // Adding elements
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Charlie");
        uniqueNames.add("Alice"); // Duplicate - won't be added
        
        System.out.println("Unique names: " + uniqueNames);
        // Output: Unique names: [Alice, Bob, Charlie] (order not guaranteed)
        
        // Checking existence
        if (uniqueNames.contains("Bob")) {
            System.out.println("Bob is in the set");
        }
        
        // Set operations
        Set<String> otherNames = new HashSet<>();
        otherNames.add("David");
        otherNames.add("Alice");
        
        // Union
        Set<String> union = new HashSet<>(uniqueNames);
        union.addAll(otherNames);
        System.out.println("Union: " + union);
        
        // Intersection
        Set<String> intersection = new HashSet<>(uniqueNames);
        intersection.retainAll(otherNames);
        System.out.println("Intersection: " + intersection);
    }
}
```

### LinkedHashSet

LinkedHashSet maintains insertion order while providing the uniqueness of a set.

```java
import java.util.*;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<Integer> orderedSet = new LinkedHashSet<>();
        
        orderedSet.add(30);
        orderedSet.add(10);
        orderedSet.add(20);
        orderedSet.add(10); // Duplicate - ignored
        
        System.out.println("Ordered set: " + orderedSet);
        // Output: Ordered set: [30, 10, 20] (maintains insertion order)
        
        // Useful for removing duplicates while preserving order
        List<String> listWithDuplicates = Arrays.asList("A", "B", "A", "C", "B");
        Set<String> noDuplicates = new LinkedHashSet<>(listWithDuplicates);
        List<String> cleanList = new ArrayList<>(noDuplicates);
        
        System.out.println("Original: " + listWithDuplicates);
        System.out.println("No duplicates: " + cleanList);
    }
}
```

### TreeSet

TreeSet stores elements in sorted order using a Red-Black tree structure.

```java
import java.util.*;

public class TreeSetExample {
    public static void main(String[] args) {
        Set<Integer> sortedNumbers = new TreeSet<>();
        
        sortedNumbers.add(50);
        sortedNumbers.add(20);
        sortedNumbers.add(80);
        sortedNumbers.add(10);
        
        System.out.println("Sorted numbers: " + sortedNumbers);
        // Output: Sorted numbers: [10, 20, 50, 80]
        
        // TreeSet with custom objects
        Set<Person> people = new TreeSet<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        
        System.out.println("Sorted people:");
        for (Person person : people) {
            System.out.println(person);
        }
        
        // NavigableSet operations
        TreeSet<Integer> numbers = new TreeSet<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Lower than 25: " + numbers.lower(25));
        System.out.println("Higher than 25: " + numbers.higher(25));
        System.out.println("Subset [20, 40]: " + numbers.subSet(20, true, 40, true));
    }
}

class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
    
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
```

## 3. Queue Interface

Queues are collections designed for holding elements prior to processing.

### ArrayDeque

ArrayDeque is a resizable array implementation of the Deque interface.

```java
import java.util.*;

public class QueueExample {
    public static void main(String[] args) {
        // Queue operations
        Queue<String> queue = new ArrayDeque<>();
        
        // Adding elements (enqueue)
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("Queue: " + queue);
        
        // Removing elements (dequeue)
        while (!queue.isEmpty()) {
            System.out.println("Processing: " + queue.poll());
        }
        
        // Deque operations (double-ended queue)
        Deque<Integer> deque = new ArrayDeque<>();
        
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(30);
        
        System.out.println("Deque: " + deque);
        // Output: Deque: [5, 10, 20, 30]
        
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("Final deque: " + deque);
    }
}
```

### PriorityQueue

PriorityQueue is a heap-based priority queue implementation.

```java
import java.util.*;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Natural ordering (min-heap for integers)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(50);
        minHeap.offer(20);
        
        System.out.println("Min heap processing:");
        while (!minHeap.isEmpty()) {
            System.out.println("Next: " + minHeap.poll());
        }
        // Output: 10, 20, 30, 50
        
        // Custom comparator (max-heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(Arrays.asList(30, 10, 50, 20));
        
        System.out.println("Max heap processing:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Next: " + maxHeap.poll());
        }
        // Output: 50, 30, 20, 10
        
        // Priority queue with custom objects
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("Low priority task", 3));
        taskQueue.offer(new Task("High priority task", 1));
        taskQueue.offer(new Task("Medium priority task", 2));
        
        System.out.println("Task processing order:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}

class Task implements Comparable<Task> {
    private String description;
    private int priority;
    
    public Task(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }
    
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    
    @Override
    public String toString() {
        return description + " (Priority: " + priority + ")";
    }
}
```

## 4. Map Interface

Maps store key-value pairs and don't allow duplicate keys.

### HashMap

HashMap uses hash table for storage, providing O(1) average time complexity.

```java
import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> studentGrades = new HashMap<>();
        
        // Adding key-value pairs
        studentGrades.put("Alice", 85);
        studentGrades.put("Bob", 92);
        studentGrades.put("Charlie", 78);
        studentGrades.put("Alice", 90); // Updates existing key
        
        System.out.println("Student grades: " + studentGrades);
        
        // Accessing values
        System.out.println("Alice's grade: " + studentGrades.get("Alice"));
        
        // Checking for keys/values
        if (studentGrades.containsKey("Bob")) {
            System.out.println("Bob is in the map");
        }
        
        if (studentGrades.containsValue(78)) {
            System.out.println("Someone got 78");
        }
        
        // Iterating through the map
        System.out.println("All students and grades:");
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Using Java 8 streams
        studentGrades.entrySet().stream()
            .filter(entry -> entry.getValue() > 80)
            .forEach(entry -> System.out.println(entry.getKey() + " scored above 80"));
    }
}
```

### LinkedHashMap

LinkedHashMap maintains insertion order or access order.

```java
import java.util.*;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        // Insertion order
        Map<String, String> insertionOrderMap = new LinkedHashMap<>();
        insertionOrderMap.put("First", "1");
        insertionOrderMap.put("Third", "3");
        insertionOrderMap.put("Second", "2");
        
        System.out.println("Insertion order: " + insertionOrderMap);
        // Output: {First=1, Third=3, Second=2}
        
        // Access order (LRU cache behavior)
        Map<String, String> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("A", "1");
        accessOrderMap.put("B", "2");
        accessOrderMap.put("C", "3");
        
        // Access B, making it most recently used
        accessOrderMap.get("B");
        
        System.out.println("After accessing B: " + accessOrderMap);
        // B moves to the end: {A=1, C=3, B=2}
        
        // Simple LRU Cache implementation
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);
        cache.get("key1"); // Access key1
        cache.put("key4", 4); // This should evict key2
        
        System.out.println("LRU Cache: " + cache);
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

### TreeMap

TreeMap stores entries in sorted order based on keys.

```java
import java.util.*;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<String, Double> productPrices = new TreeMap<>();
        
        productPrices.put("Laptop", 999.99);
        productPrices.put("Mouse", 25.50);
        productPrices.put("Keyboard", 75.00);
        productPrices.put("Monitor", 299.99);
        
        System.out.println("Products (sorted by name):");
        productPrices.forEach((product, price) -> 
            System.out.println(product + ": $" + price));
        
        // NavigableMap operations
        TreeMap<Integer, String> scores = new TreeMap<>();
        scores.put(85, "Alice");
        scores.put(92, "Bob");
        scores.put(78, "Charlie");
        scores.put(95, "Diana");
        
        System.out.println("Scores above 80: " + scores.tailMap(80));
        System.out.println("Scores between 80-90: " + scores.subMap(80, 90));
        System.out.println("Highest score: " + scores.lastEntry());
        System.out.println("Lowest score: " + scores.firstEntry());
    }
}
```

## 5. Utility Classes

### Collections Class

The Collections class provides static methods for common operations.

```java
import java.util.*;

public class CollectionsUtilityExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        
        // Sorting
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
        
        // Reverse
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);
        
        // Shuffle
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);
        
        // Min and Max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));
        
        // Binary search (requires sorted list)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index);
        
        // Frequency
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "apple");
        System.out.println("Frequency of 'apple': " + Collections.frequency(words, "apple"));
        
        // Immutable collections
        List<String> immutableList = Collections.unmodifiableList(Arrays.asList("A", "B", "C"));
        Set<String> immutableSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("X", "Y", "Z")));
        
        // Synchronized collections
        List<String> syncList = Collections.synchronizedList(new ArrayList<>());
        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
    }
}
```

### Arrays Class

The Arrays class provides utilities for array manipulation.

```java
import java.util.*;

public class ArraysUtilityExample {
    public static void main(String[] args) {
        // Converting array to list
        String[] array = {"apple", "banana", "cherry"};
        List<String> list = Arrays.asList(array);
        System.out.println("Array to List: " + list);
        
        // Note: Arrays.asList() returns a fixed-size list
        // list.add("date"); // This would throw UnsupportedOperationException
        
        // To create a modifiable list
        List<String> modifiableList = new ArrayList<>(Arrays.asList(array));
        modifiableList.add("date");
        System.out.println("Modifiable list: " + modifiableList);
        
        // Sorting arrays
        int[] numbers = {5, 2, 8, 1, 9};
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Binary search in arrays
        int index = Arrays.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index);
        
        // Comparing arrays
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        System.out.println("Arrays equal: " + Arrays.equals(arr1, arr2));
        
        // Filling arrays
        int[] fillArray = new int[5];
        Arrays.fill(fillArray, 42);
        System.out.println("Filled array: " + Arrays.toString(fillArray));
    }
}
```

## 6. Best Practices and Performance Tips

### Choosing the Right Collection

```java
import java.util.*;

public class CollectionChoiceExample {
    public static void main(String[] args) {
        // Use ArrayList when:
        // - You need indexed access
        // - You do more reading than inserting/deleting
        List<String> fastAccess = new ArrayList<>();
        
        // Use LinkedList when:
        // - You frequently insert/delete at the beginning or middle
        // - You implement queue/stack behavior
        List<String> frequentModification = new LinkedList<>();
        
        // Use HashSet when:
        // - You need unique elements
        // - Order doesn't matter
        // - You need fast lookup
        Set<String> uniqueItems = new HashSet<>();
        
        // Use TreeSet when:
        // - You need unique elements in sorted order
        // - You need NavigableSet operations
        Set<String> sortedUniqueItems = new TreeSet<>();
        
        // Use HashMap when:
        // - You need key-value mapping
        // - Order doesn't matter
        // - You need fast lookup
        Map<String, Integer> fastLookup = new HashMap<>();
        
        // Use TreeMap when:
        // - You need sorted key-value mapping
        // - You need NavigableMap operations
        Map<String, Integer> sortedMapping = new TreeMap<>();
        
        // Performance demonstration
        demonstratePerformance();
    }
    
    private static void demonstratePerformance() {
        int size = 100000;
        
        // ArrayList vs LinkedList for random access
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Fill both lists
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        
        // Random access performance
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(size / 2);
        }
        long arrayListTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(size / 2);
        }
        long linkedListTime = System.nanoTime() - start;
        
        System.out.println("ArrayList random access: " + arrayListTime + " ns");
        System.out.println("LinkedList random access: " + linkedListTime + " ns");
        System.out.println("ArrayList is " + (linkedListTime / arrayListTime) + "x faster for random access");
    }
}
```

## 7. Common Patterns and Examples

### Iterator Pattern

```java
import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C++");
        
        // Using Iterator
        Iterator<String> iter = languages.iterator();
        while (iter.hasNext()) {
            String language = iter.next();
            System.out.println("Language: " + language);
        }
        
        // Using ListIterator for bidirectional iteration
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListIterator<Integer> listIter = numbers.listIterator(numbers.size());
        
        System.out.println("Reverse iteration:");
        while (listIter.hasPrevious()) {
            System.out.println(listIter.previous());
        }
        
        // Safe removal during iteration
        List<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "apricot", "cherry"));
        Iterator<String> wordIter = words.iterator();
        
        while (wordIter.hasNext()) {
            String word = wordIter.next();
            if (word.startsWith("a")) {
                wordIter.remove(); // Safe removal
            }
        }
        System.out.println("After removing words starting with 'a': " + words);
    }
}
```

### Comparator Examples

```java
import java.util.*;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, 20),
            new Student("Bob", 92, 19),
            new Student("Charlie", 78, 21),
            new Student("Diana", 92, 18)
        );
        
        // Sort by grade (descending)
        students.sort(Comparator.comparingInt(Student::getGrade).reversed());
        System.out.println("Sorted by grade (desc): " + students);
        
        // Sort by age
        students.sort(Comparator.comparingInt(Student::getAge));
        System.out.println("Sorted by age: " + students);
        
        // Multiple criteria: grade desc, then age asc
        students.sort(
            Comparator.comparingInt(Student::getGrade).reversed()
                     .thenComparingInt(Student::getAge)
        );
        System.out.println("Sorted by grade desc, then age: " + students);
        
        // Custom comparator
        students.sort((s1, s2) -> {
            if (s1.getGrade() != s2.getGrade()) {
                return Integer.compare(s2.getGrade(), s1.getGrade()); // Grade desc
            }
            return s1.getName().compareTo(s2.getName()); // Name asc
        });
        System.out.println("Custom sort: " + students);
    }
}

class Student {
    private String name;
    private int grade;
    private int age;
    
    public Student(String name, int grade, int age) {
        this.name = name;
        this.grade = grade;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getGrade() { return grade; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return name + "(" + grade + "," + age + ")";
    }
}
```

## Summary

The Java Collection Framework provides a rich set of data structures for different use cases:

**Lists**: Ordered collections allowing duplicates

- ArrayList: Fast random access, slower insertion/deletion
- LinkedList: Fast insertion/deletion, slower random access
- Vector: Thread-safe ArrayList alternative

**Sets**: Collections that don't allow duplicates

- HashSet: Fast operations, no ordering
- LinkedHashSet: Maintains insertion order
- TreeSet: Maintains sorted order

**Queues**: Collections for processing elements in order

- ArrayDeque: General purpose queue/deque
- PriorityQueue: Elements processed by priority

**Maps**: Key-value pair collections

- HashMap: Fast operations, no ordering
- LinkedHashMap: Maintains insertion/access order
- TreeMap: Maintains sorted order by keys

Choose the appropriate collection based on your specific needs regarding performance, ordering, and thread safety requirements. The framework also provides utility classes like Collections and Arrays to perform common operations efficiently.
