public class Person{
    /**
     * FOR ENCAPSULATION
     * This class represents a person with various attributes such as name, age, address, phone number, email, and occupation.
     * It provides methods to get and set these attributes, print the person's details, and a method to lead.
     * This class demonstrates encapsulation by using private fields and public getter and setter methods.
     * It also includes a method to print the person's details.
     */

    String name;
    private int age;
    private String address;
    private String phoneNumber;
    String email;
    String occupation;

    Person(String name, int age, String address, String phoneNumber, String email, String occupation){
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.occupation = occupation;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }


    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getOccupation(){
        return occupation;
    }
    public void setOccupation(String occupation){
        this.occupation = occupation;
    }
    public void printDetails(){
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Address: " + this.address);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Email: " + this.email);
        System.out.println("Occupation: " + this.occupation);
    }

    void lead(){
        System.out.println("I am the parent  ");
    }
}

