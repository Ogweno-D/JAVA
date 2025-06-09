
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DatabaseManager{

    // Why use a tree MAp this time? It shall maintain the order

    private Map <String,Table> tables = new TreeMap<>();

    // Add a new Table and its columns
    public void addTable(int tableId, String tableName, Map<Integer, Column> columns){
        Table table = new Table(tableId, tableName);

        table.setColumns(columns);
        tables.put(tableName, table);
    }

    // b.1 Add a column to a particular table
    public boolean addColumn(String tableName, int position, Column column){
        if (tables.containsKey(tableName)){
            tables.get(tableName)
                  .getColumns()
                  .put(position, column);

            return true;
        }
        return  false;
    }

    // b.2 Edit a column in a particular table
     public boolean editColumn(String tableName, int position, Column newColumn){
        if (tables.containsKey(tableName) && tables.get(tableName)
                  .getColumns()
                  .containsKey(position)){
            tables.get(tableName).getColumns().put(position, newColumn);

            return true;
        }
        return  false;
    }

    // b.3 Remove a column in a particular table
    public boolean removeColumn( String tableName, int position){
        if(tables.containsKey(tableName) && tables.get(tableName)
                 .getColumns().containsKey(position)){

                    tables.get(tableName).getColumns().remove(position);
                    return true;
                 }
            return false;
    }


    // c.Get tables which have similar_named columns e.g date_created
    public Set<String> getTablesWithColumnName(String columnName) {
        return tables.entrySet().stream()
            .filter(entry -> entry.getValue().getColumns().values().stream()
                .anyMatch(column -> column.getColumnName().equalsIgnoreCase(columnName)))
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(TreeSet::new)); // Maintains order
    }


     // d. Display All tables and their columns

        public void displayAllTables() {
            tables.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Explicit sorting (though TreeMap already does this)
                .forEach(entry -> {
                    System.out.println("Table: " + entry.getKey());
                    System.out.println("Columns:");
                    entry.getValue().getColumns().entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(colEntry -> 
                            System.out.println("  Position " + colEntry.getKey() + ": " + colEntry.getValue()));
                    System.out.println();
                });
        }


    // Helper method to get all tables
    public Map<String, Table> getTables(){
        return tables;
    }

}