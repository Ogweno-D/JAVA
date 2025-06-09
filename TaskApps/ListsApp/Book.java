
import java.util.Date;

public class Book{

    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int numberOfCopies;
    private Date dateOfPublication;

    public Book(int bookId, String bookName, String bookAuthor, int numberOfCopies, Date dateOfPublication) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.numberOfCopies = numberOfCopies;
        this.dateOfPublication = dateOfPublication;
    }


    public int getBookId(){
        return bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public int getNumberOfCopies() {
        return numberOfCopies;
    }
    public Date getDateOfPublication() {
        return dateOfPublication;
    }
    public void setBookid(int bookId) {
        this.bookId = bookId;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    /**
     * String form of the information
     */

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", datePublished=" + dateOfPublication +
                '}';
    }




    

}