public class Main {
    public static void main(String[] args) {
        Student student0 = new Student("Ogweno", 23, "123 Main St", "123-456-7890", "sturudent@gmail.com", "Student", 12346);
        Student student1 = new Student("Alice", 20, "456 Elm St", "987-654-3210", "", "Student", 12345);
        Student student2 = new Student("Bob", 22, "789 Oak St", "555-555-5555", "", "Student", 12346);
        System.out.println("Student Details:");
        System.out.println("-----------------");
        student0.setName("Ogweno Ochieng");
        student0.setAge(24);
        student0.setAdmissionNumber(12347);
        System.out.println("Updated Student Details: \n");
        student0.printDetails();
        System.out.println("==================== \n");
        System.out.println("Student 1 Details:");
        student1.printDetails(false);
        System.out.println("==================== \n");
        System.out.println("Student 2 Details:");
        student2.printDetails(true);

        // Multilevel Inheritance Example
        PrimaryStudent primaryStudent = new PrimaryStudent("John", 10, "123 School St", "111-222-3333", "", "Primary Student", 12348, "Math");
        System.out.println("Multilevel Inheritance Example: \n");
        System.out.println("====================");
        System.out.println("Primary Student Details:");
        primaryStudent.printDetails();
        System.out.println("==================== \n");

        // Hierachial Inheritance Example
        System.out.println("Hierarchical Inheritance Example: \n");
        primaryStudent.play();

        // This method is defined in the Parent class Person
        primaryStudent.lead();
    }
}