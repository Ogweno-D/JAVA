import java.util.ArrayList;
import java.util.Comparator;
import  java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DepartmentManager{
    private final Map<String, List<Employee>> departmentMap;

    public DepartmentManager(){
        this.departmentMap = new HashMap<>();
    }


    // A. Add a new employee
    public void addEmployee(Employee employee){
        // require non null
        Objects.requireNonNull(employee, "Employee cannot be null");
        
        departmentMap.computeIfAbsent(employee.getDepartment(), 
            k-> new ArrayList<>())
                .add(employee);
    }

    // Get employee - search by any property
    public Optional<Employee> getEmployeer(int employeeId){
        return departmentMap.values().stream()
                    .flatMap(List::stream)
                    .filter(e -> e.getEmployeeId() == employeeId)
                    .findFirst();
    }

    public Optional<Employee> getEmployee(String employeeNumber) {
        return departmentMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getEmployeeNumber().equals(employeeNumber))
                .findFirst();
    }

    public List<Employee> getEmployeesByName(String name) {
        return departmentMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // c. Update Employee Details
    public boolean updateEmployee(int employeeId, Employee updatedEmployee) {
        Optional<Employee> existing = getEmployee(employeeId);
        if (existing.isPresent()) {
            Employee oldEmployee = existing.get();
            
            // Remove from old department if department changed
            if (!oldEmployee.getDepartment().equals(updatedEmployee.getDepartment())) {
                departmentMap.get(oldEmployee.getDepartment()).remove(oldEmployee);
                addEmployee(updatedEmployee);
            } else {
                // Update in place
                int index = departmentMap.get(oldEmployee.getDepartment()).indexOf(oldEmployee);
                departmentMap.get(oldEmployee.getDepartment()).set(index, updatedEmployee);
            }
            return true;
        }
        return false;
    }

    // d. Delete Employee
    public boolean deleteEmployee(int employeeId) {
        Optional<Employee> employee = getEmployee(employeeId);
        if (employee.isPresent()) {
            String department = employee.get().getDepartment();
            boolean removed = departmentMap.get(department).remove(employee.get());
            
            // Remove department if empty
            if (departmentMap.get(department).isEmpty()) {
                departmentMap.remove(department);
            }
            return removed;
        }
        return false;
    }

    // e. Display Employees grouped by department
    public void displayEmployeesByDepartment() {
        departmentMap.forEach((dept, employees) -> {
            System.out.println("\nDepartment: " + dept);
            employees.forEach(System.out::println);
        });
    }

    // f. Display Employees ordered by property
    public void displayEmployeesOrderedBy(Comparator<Employee> comparator, boolean ascending) {
        Comparator<Employee> comp = ascending ? comparator : comparator.reversed();
        
        departmentMap.values().stream()
            .flatMap(List::stream)
            .sorted(comp)
            .forEach(System.out::println);
    }

    // g. Display Employees filtered by property
    public void displayEmployeesFilteredBy(Predicate<Employee> predicate) {
        departmentMap.values().stream()
            .flatMap(List::stream)
            .filter(predicate)
            .forEach(System.out::println);
    }

    // h. Count Employees
    public Map<String, Long> getEmployeeCountByDepartment() {
        return departmentMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> (long) e.getValue().size()
            ));
    }

    public long getTotalEmployeeCount() {
        return departmentMap.values().stream()
            .mapToLong(List::size)
            .sum();
    }

    // Utility method to get all employees
    public List<Employee> getAllEmployees() {
        return departmentMap.values().stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    // Get employee by Id
    public Optional<Employee> getEmployee(int employeeId){
        return  departmentMap.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst();
    }
}
