
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseManager{

// In this map (The course name is the key, while the
// students are the values in this key Value pair)
    private final Map<String, List<Student>> courseMap;

    // The hashmap is then initialized in this following constructor
    public CourseManager(){
        this.courseMap = new HashMap<>();
    }

    // CRUD ON A HASHMAP

    // 1. Add a new student to the course
    public void AddStudent(Student student){
        // Check if it's not null
        Objects.requireNonNull(
            student, "Student Cannot be null"
            );

        courseMap.computeIfAbsent(
            student.getcourse(),
             k-> new ArrayList<>())
             .add(student);
    }

    // Get student ( By anything, any property of the student)
    public Optional<Student> getStudent(int studentId){
        return courseMap.values().stream()
                    .flatMap(List::stream)
                    .filter(s -> s.getStudentId() == studentId)
                    .findFirst();
    }

    // Get student By name

    public List<Student> getStudentsByName(String name){
        return courseMap.values().stream()
                    .flatMap(List::stream)
                    .filter(s -> s.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
    }

    // 3. Update the student Details
    public boolean updateStudent(int studentId, Student updatedStudent){
        Optional<Student> existing = getStudent(studentId);
        if (existing.isPresent()) {
            Student oldStudent = existing.get();
            if(!oldStudent.getcourse().equals(updatedStudent.getcourse())){
                courseMap.get(oldStudent.getcourse()).remove(oldStudent);
                AddStudent(oldStudent);
            } else{
                 // Update the course

                int index = courseMap.get(oldStudent.getcourse()).indexOf(oldStudent);
                courseMap.get(oldStudent.getcourse()).set(index, updatedStudent);
            }
             return  true;  
        } 
        return  false;

    }

    // 4. Delete Students grouped by the course
    public boolean deleteStudents(int studentId){
        Optional<Student> student =getStudent(studentId);
        
        if(student.isPresent()){
            String course  = student.get().getcourse();
            boolean removed = courseMap.get(course).remove(student.get());

            // If the course part is empty!
            if(courseMap.get(course).isEmpty()){
                courseMap.remove(course);
            } 
            return removed;
        }
        return false;
    }

    // #######################################################################################

    // Display students grouped by the course

    public void displayStudentsByCourse()
    {
            courseMap.forEach((course, students)->{
                System.out.println("\n Courses" + course);
                students.forEach(System.out::println);
            });
    }



}