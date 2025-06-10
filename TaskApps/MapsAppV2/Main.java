import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // a. Add new tables with columns
        Map<Integer, Column> userColumns = new TreeMap<>();
        userColumns.put(1, new Column(1, " user_id ", "INT"));
        userColumns.put(2, new Column(2, " username ", "VARCHAR(50)"));
        userColumns.put(3, new Column(3, " date_created ", "DATETIME"));
        dbManager.addTable(1, "users", userColumns);

        Map<Integer, Column> orderColumns = new TreeMap<>();
        orderColumns.put(1, new Column(1, "order_id", "INT"));
        orderColumns.put(2, new Column(2, "user_id", "INT"));
        orderColumns.put(3, new Column(3, "date_created", "DATETIME"));
        dbManager.addTable(2, "orders", orderColumns);

        // b.1 Add a new column to users table
        dbManager.addColumn("users", 4, new Column(4, "email", "VARCHAR(100)"));

        // b.2 Edit a column in orders table
        dbManager.editColumn("orders", 2, new Column(2, "customer_id", "INT"));

        // b.3 Remove a column from users table
        // This removes the date_created column
        dbManager.removeColumn(" users ", 3);

        // c. Find tables with specific column name
        Set<String> tablesWithDateCreated = dbManager.getTablesWithColumnName("date_created");
        System.out.println("Tables with 'date_created' column: " + tablesWithDateCreated);

        // d. Display all tables and columns
        dbManager.displayAllTables();
        
    }
}