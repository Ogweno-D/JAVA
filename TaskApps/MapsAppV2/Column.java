public class Column{
    int columnId;
    String columnName;
    String dataType;

    public Column(int columnId, String columnName, String dataType){
        this.columnId = columnId;
        this.columnName = columnName;
        this.dataType = dataType;
    }

    // Getters
    public String getColumnName(){
        return columnName;
    }

    public String getDataType(){
        return dataType;
    }

    public int getColumnId(){
        return  columnId;
    }

    // Setters

    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    public void setDataType(String dataType){
        this.dataType = dataType;

    }
    // The question is do we need to set the columnid?
    // SO far the answer is yes
    public void setColumnId(int columnId){
        this.columnId = columnId;
    }

    @Override
    public String toString(){
        return   "\n" + "{" + "\n" +
                "ColumnId" + " " + columnId + "\n" +
                "ColumnName " + " " + columnName + " \n" +
                "Data Type "+ " "+  dataType + "\n" +
                "}";
    }
    

}