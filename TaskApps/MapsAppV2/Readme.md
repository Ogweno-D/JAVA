# Database Management System

A comprehensive Java-based system for managing database tables and columns with CRUD operations. This system provides a structured approach to handle table schemas, column definitions, and relationship management.

## 🚀 Features

- **Table Management**: Create, read, and manage database tables
- **Column Operations**: Add, edit, and remove columns from tables
- **Search Functionality**: Find tables with similar column names
- **Ordered Storage**: Lexicographically ordered keys using TreeMap
- **Comprehensive Display**: View all tables and their column structures

## 📋 System Architecture

### Core Classes

1. **Column Class**: Represents individual database columns
2. **Table Class**: Represents database tables containing multiple columns
3. **TableManager Class**: Main management system for all operations

### Key Data Structures

- `Map<String, Table> tables` - Stores all tables (TreeMap for ordering)
- `Map<Integer, Column> columns` - Stores columns per table (TreeMap for position ordering)

## 🛠️ Installation and Setup

1. **Prerequisites**:
   - Java 8 or higher
   - Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

2. **Compilation**:

   ```bash
   javac *.java
   ```

3. **Execution**:

   ```bash
   java TableManager
   ```

## 📖 Usage Guide

### 1. Creating a Column

```java
// Basic column creation
Column column = new Column(1, "user_id", "INT");

// Using setters
Column column2 = new Column();
column2.setColumnId(2);
column2.setColumnName("username");
column2.setDataType("VARCHAR(50)");
```

### 2. Creating a Table

```java
// Create empty table
Table table = new Table(1, "users");

// Add columns to table
table.getColumns().put(1, new Column(1, "user_id", "INT"));
table.getColumns().put(2, new Column(2, "username", "VARCHAR(50)"));
```

### 3. Managing Tables with TableManager

```java
TableManager manager = new TableManager();

// Add a new table
Table usersTable = new Table(1, "users");
manager.addTable(usersTable);

// Add column to existing table
manager.addColumn("users", 3, new Column(3, "email", "VARCHAR(100)"));

// Edit existing column
manager.editColumn("users", 2, new Column(2, "full_name", "VARCHAR(100)"));

// Remove column
manager.removeColumn("users", 3);

// Display all tables
manager.displayAllTables();
```

## 🔍 Advanced Features

### Search for Similar Columns

```java
// Find all tables containing columns with "date" in the name
List<String> tables = manager.getTablesWithSimilarColumn("date");
System.out.println("Tables with date columns: " + tables);
```

### Lexicographic Ordering

The system automatically maintains lexicographic ordering:

- Table names are ordered alphabetically
- Column positions are ordered numerically
- All operations preserve this ordering

## 💡 Practical Examples

### Example 1: E-commerce Database Schema

```java
TableManager ecommerce = new TableManager();

// Users table
Table users = new Table(1, "users");
users.getColumns().put(1, new Column(1, "user_id", "INT PRIMARY KEY"));
users.getColumns().put(2, new Column(2, "username", "VARCHAR(50)"));
users.getColumns().put(3, new Column(3, "email", "VARCHAR(100)"));
users.getColumns().put(4, new Column(4, "date_created", "TIMESTAMP"));

// Products table
Table products = new Table(2, "products");
products.getColumns().put(1, new Column(5, "product_id", "INT PRIMARY KEY"));
products.getColumns().put(2, new Column(6, "product_name", "VARCHAR(100)"));
products.getColumns().put(3, new Column(7, "price", "DECIMAL(10,2)"));
products.getColumns().put(4, new Column(8, "date_created", "TIMESTAMP"));

// Orders table
Table orders = new Table(3, "orders");
orders.getColumns().put(1, new Column(9, "order_id", "INT PRIMARY KEY"));
orders.getColumns().put(2, new Column(10, "user_id", "INT FOREIGN KEY"));
orders.getColumns().put(3, new Column(11, "product_id", "INT FOREIGN KEY"));
orders.getColumns().put(4, new Column(12, "quantity", "INT"));
orders.getColumns().put(5, new Column(13, "order_date", "TIMESTAMP"));

ecommerce.addTable(users);
ecommerce.addTable(products);
ecommerce.addTable(orders);

// Display the schema
ecommerce.displayAllTables();
```

### Example 2: Schema Evolution

```java
// Add new column to existing table
ecommerce.addColumn("users", 5, new Column(14, "last_login", "TIMESTAMP"));

// Modify existing column (e.g., increase username length)
ecommerce.editColumn("users", 2, new Column(2, "username", "VARCHAR(100)"));

// Remove deprecated column
ecommerce.removeColumn("products", 3); // Remove price column

// Find all tables with timestamp columns
List<String> tablesWithTimestamps = ecommerce.getTablesWithSimilarColumn("date");
System.out.println("Tables with date/timestamp columns: " + tablesWithTimestamps);
```

## 🎯 Use Cases and Applications

### 1. Database Schema Management

- **Database Design**: Plan and visualize database structures
- **Schema Evolution**: Track changes to database schemas over time
- **Documentation**: Generate documentation for existing database schemas

### 2. Data Modeling Tools

- **ORM Mapping**: Generate object-relational mapping configurations
- **Code Generation**: Create entity classes from table definitions
- **Migration Scripts**: Generate database migration scripts

### 3. Educational Tools

- **Database Courses**: Teach database design concepts
- **Data Modeling**: Demonstrate normalization and relationship concepts
- **SQL Learning**: Understand table structures before writing queries

### 4. Development Tools

- **API Documentation**: Generate API documentation based on data models
- **Testing**: Create test data structures for unit testing
- **Prototyping**: Quickly prototype database schemas

### 5. Enterprise Applications

- **Metadata Management**: Store and manage database metadata
- **Schema Comparison**: Compare schemas across different environments
- **Compliance**: Track schema changes for audit purposes

## 🔧 Best Practices

### 1. Naming Conventions

```java
// Use descriptive names
Column userId = new Column(1, "user_id", "INT");
Column createdAt = new Column(2, "created_at", "TIMESTAMP");

// Avoid abbreviations when possible
Column firstName = new Column(3, "first_name", "VARCHAR(50)"); // Good
Column fName = new Column(3, "f_name", "VARCHAR(50)"); // Avoid
```

### 2. Data Type Consistency

```java
// Be consistent with data types
Column id1 = new Column(1, "user_id", "INT");
Column id2 = new Column(2, "product_id", "INT"); // Same type for IDs

// Specify lengths for variable types
Column name = new Column(3, "name", "VARCHAR(100)");
Column description = new Column(4, "description", "TEXT");
```

### 3. Position Management

```java
// Leave gaps in positions for future insertions
table.getColumns().put(10, new Column(1, "id", "INT"));
table.getColumns().put(20, new Column(2, "name", "VARCHAR(50)"));
table.getColumns().put(30, new Column(3, "email", "VARCHAR(100)"));

// Now you can insert at position 15 or 25 without reordering
```

## 🚨 Error Handling

The system includes comprehensive error handling:

```java
// Attempt to add duplicate table
if (!manager.addTable(existingTable)) {
    System.out.println("Table already exists!");
}

// Attempt to edit non-existent column
if (!manager.editColumn("nonexistent", 1, newColumn)) {
    System.out.println("Operation failed!");
}
```

## 📊 Sample Output

```
=== TABLE MANAGEMENT SYSTEM DEMO ===

Table 'users' added successfully!
Table 'products' added successfully!

=== ALL TABLES ===

Table: products (ID: 2)
Columns:
  Position 1: product_id (INT) [ID: 5]
  Position 2: product_name (VARCHAR(100)) [ID: 6]
  Position 3: price (DECIMAL(10,2)) [ID: 7]
  Position 4: date_created (TIMESTAMP) [ID: 8]

Table: users (ID: 1)
Columns:
  Position 1: user_id (INT) [ID: 1]
  Position 2: username (VARCHAR(50)) [ID: 2]
  Position 3: email (VARCHAR(100)) [ID: 3]
  Position 4: date_created (TIMESTAMP) [ID: 4]
==================
```

## 🔮 Future Enhancements

1. **Constraints**: Add support for foreign keys, unique constraints
2. **Indexes**: Manage database indexes
3. **Relationships**: Define and manage table relationships
4. **Export/Import**: Save/load schemas to/from files
5. **Validation**: Add column validation rules
6. **Versioning**: Track schema versions and changes

## 📚 Related Technologies

This system can be integrated with:

- **Hibernate/JPA**: Generate entity mappings
- **Spring Boot**: Create REST APIs from schemas
- **Flyway/Liquibase**: Generate migration scripts
- **GraphQL**: Generate GraphQL schemas
- **Documentation Tools**: Generate API documentation

## 🤝 Contributing

This system provides a solid foundation for database schema management. Contributions and enhancements are welcome!

## 📄 License

This project is available for educational and commercial use.
