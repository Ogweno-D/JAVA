
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        // Converted to try with resources so a bit unique but 
        // the Gurus(VS Code Extensions)  suggested it!
        try (Scanner scanner = new Scanner(System.in)) {
            Employee employee = null;
            
            while (employee == null) {
                try {
                    System.out.println("Enter employee details:");
                    
                    System.out.println("Employee Number (max 5 chars): ");
                    String number = scanner.nextLine();
                    
                    System.out.println("Employee Name (min 3 chars): ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Net Salary (non-negative): ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    
                    // Attempt to create employee (validations happen in constructor)
                    employee = new Employee(name, number, salary);
                    System.out.println("==================================");
                    System.out.println("Employee created successfully! \n");
                    System.out.println(employee);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid salary format. Please enter a valid number.");
                } catch (IllegalArgumentException | EmployeeDataException e) {
                    System.out.println("Error: " + e.getMessage());
                } 
            }
        }
    }
}
