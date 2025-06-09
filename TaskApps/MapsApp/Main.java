import java.util.Comparator;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DepartmentManager manager = new DepartmentManager();

        // a. Add employees
        manager.addEmployee(new Employee(1, "E100", "John Doe", "IT"));
        manager.addEmployee(new Employee(2, "E101", "Jane Smith", "HR"));
        manager.addEmployee(new Employee(3, "E102", "Bob Johnson", "IT"));
        manager.addEmployee(new Employee(4, "E103", "Alice Brown", "Finance"));

        // b. Get employee
        Optional<Employee> emp = manager.getEmployee(2);
        emp.ifPresent(e -> System.out.println("Found: " + e));

        // c. Update employee
        manager.updateEmployee(3, new Employee(3, "E102", "Robert Johnson", "IT"));

        // d. Delete employee
        manager.deleteEmployee(1);

        // e. Display by department
        System.out.println("\nEmployees by department:");
        manager.displayEmployeesByDepartment();

        // f. Display ordered by name (ascending)
        System.out.println("\nEmployees ordered by name:");
        manager.displayEmployeesOrderedBy(Comparator.comparing(Employee::getName), true);

        // g. Display filtered by department
        System.out.println("\nIT Department Employees:");
        manager.displayEmployeesFilteredBy(e -> e.getDepartment().equals("IT"));

        // h. Count employees
        System.out.println("\nEmployee counts by department:");
        manager.getEmployeeCountByDepartment().forEach((dept, count) -> 
            System.out.println(dept + ": " + count));
        
        System.out.println("Total employees: " + manager.getTotalEmployeeCount());
    }
}