import java.util.Map;
import java.util.TreeMap;

public class Table{
    int tableId;
    String tableName;
    Map <Integer, Column> columns;

    public Table( int tableId, String tableName){
        this.tableId = tableId;
        this.tableName = tableName;
        this.columns = new TreeMap<>();
    }

    // Getters and  Setters
    public int getTableId(){
        return tableId;
    }
    public void setTableId(int tableId){
        this.tableId = tableId;
    } 


    public String getTableName(){
        return  tableName;
    }
    public void setTableName(String tableName){
        this.tableName = tableName;   
    }

    public Map<Integer, Column> getColumns(){
        return columns;
    }
    public void setColumns(Map<Integer, Column>columns){
        this.columns = columns;
    }

    @Override
    public  String toString(){
        return "Table {" +
                "tableid" + tableId +
                " tableName " + tableName +"\'" +
                " columns " + columns +
                "}";
    }
}