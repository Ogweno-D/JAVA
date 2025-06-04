
//     String name;
//     int age;
//     int admissionNumber;


//     Student(String name, int age, int admissionNumber){
//         this.name = name;
//         this.age = age;
//         this.admissionNumber = admissionNumber;
//     }

//     public String getName() {
//         return name;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public int getAge() {
//         return age;
//     }
//     public void setAge(int age) {
//         this.age = age;
//     }
//     public int getAdmissionNumber() {
//         return admissionNumber;
//     }
//     public void setAdmissionNumber(int admissionNumber) {
//         this.admissionNumber = admissionNumber;
//     }

//     public void PrintDetails() {
//         System.out.println("Name: " + this.name);
//         System.out.println("Age: " + this.age);
//         System.out.println("Admission Number: " + this.admissionNumber);
//     }

//     public static void main(String[] args) {
//         Student student0 = new Student("Ogweno",23,12346);
//         Student student1 = new Student("Alice", 20, 12345);
//         Student student2 = new Student("Bob", 22, 12346);
//         System.out.println("Student Details:");
//         System.out.println("-----------------");
//         student0.PrintDetails();
//         student1.PrintDetails(); 
//         student2.PrintDetails();
//         student0.setName("Ogweno Ochieng");
//         student0.setAge(24);
//         student0.setAdmissionNumber(12347);
//         student0.PrintDetails();
//         System.out.println("Updated Student Details:");
//     }
// }

public class Student extends Person {

    int admissionNumber;

    Student(String name, int age, String address, String phoneNumber, String email, String occupation, int admissionNumber) {
        super(name, age, address, phoneNumber, email, occupation);
        this.admissionNumber = admissionNumber;
    }

    public int getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(int admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    /**
     * This right here is an example of METHOD OVERRIDING.
     * It allows the subclass to provide a specific implementation of a method that is already defined in its superclass.
     * In this case, the Student class overrides the printDetails method from the Person class to include the admission number.
     * This is a key feature of polymorphism in object-oriented programming.
     * It allows for dynamic method resolution, where the method that gets executed is determined at runtime based on the actual object type.
     * This means that when you call printDetails on a Student object, it will execute the Student's version of the method, not the Person's version.
     * This is useful for providing more specific behavior in subclasses while still maintaining the interface defined by the superclass.
     */
    @Override
    public void printDetails() {
        super.printDetails();
        // This calls the printDetails method from the Person class
        // and then adds the admission number specific to the Student class.
        System.out.println("Admission Number: " + this.admissionNumber);
    }

    public void printDetails(boolean includeAdmissionNumber) {
        super.printDetails();
        System.out.println("Admission Number: " + (includeAdmissionNumber? "" :this.admissionNumber));
        if (includeAdmissionNumber) {
            System.out.println("Admission Number: " + this.admissionNumber);
        }
    }

    
}