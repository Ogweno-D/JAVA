
import java.util.Objects;

public class  Employee{
    int employeeId;
    String employeeNumber;
    String name;
    String department;

    public Employee( int employeeId, String employeeNumber, String name, String department){
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.department = department;
    }

    // Getters
    public int getEmployeeId(){
        return employeeId;
    }

    public String getEmployeeNumber(){
        return  employeeNumber;
    }

    public String getName(){
        return  name;
    }

    public String getDepartment(){
        return department;
    }

    // Setters 
      public int setEmployeeId(int employeeId){
        return this.employeeId;
    }

    public String setEmployeeNumber(String employeeNumber){
        return  this.employeeNumber;
    }

    public String setName(String name){
        return  this.name;
    }

    public String setDepartment(String department){
        return this.department;
    }

    // Override equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && 
               Objects.equals(employeeNumber, employee.employeeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

}