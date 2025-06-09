
import java.util.Optional;

public class  Main{

    public static void main(String[] args){

        CourseManager manager = new  CourseManager();

        // Add students to the course
        manager.AddStudent(new Student("Dennis", 1123, "IT", 2));
        manager.AddStudent(new Student("Abel", 1124, "IT", 1));
        manager.AddStudent(new Student("Rado", 1125, "CS", 3));


        // Get Student
        Optional<Student> st = manager.getStudent(2);
        st.ifPresent(s -> System.out.println("Found: "+ s));

        manager.displayStudentsByCourse();


    }
    
}