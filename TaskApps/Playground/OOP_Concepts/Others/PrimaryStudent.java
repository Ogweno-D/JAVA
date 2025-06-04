public class PrimaryStudent extends Student {

    String favoriteSubject;

    PrimaryStudent(String name, int age, String address, String phoneNumber, String email, String occupation, int admissionNumber, String favoriteSubject) {
        super(name, age, address, phoneNumber, email, occupation, admissionNumber);
        this.favoriteSubject = favoriteSubject;
    }

    public String getFavoriteSubject() {
        return favoriteSubject;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Favorite Subject: " + this.favoriteSubject);
    }

    public  void play() {
        System.out.println(this.getName() + " is playing.");
    }
}