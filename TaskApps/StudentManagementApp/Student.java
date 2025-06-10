
public class Student{
    int studentId;
    String name;
    int AdmNo;
    String course;

    // Do the constructor
    public  Student(String name, int AdmNo, String course, int studentId ){
        this.name = name;
        this.AdmNo = AdmNo;
        this.course = course;
        this.studentId = studentId;
    }

    // Getters 
    public String getName(){
        return name;
    }

    public String getcourse(){
        return  course;
    }

    public int getAdmNo(){
        return  AdmNo;
    }

    public int getStudentId(){
        return studentId;
    }


    // Setters

    public void setName(String name){
        this.name = name;
    }

    public void setcourse( String course){
        this.course = course;
    }

    public void setAdmNo(int AdmNo){
        this.AdmNo = AdmNo;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    // Overriding  functions for a better search
    @Override
    public String toString(){
        return "Course " + course + " "+
                "Student ID " + " "+ studentId + " " +
                "Student name " + name +
                " ";
    }

    
}