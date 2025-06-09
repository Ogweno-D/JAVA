 import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookManager{

    // List to hold Book objects

    // Array List and LinkedLists can be easily interchanged
    // depending on the use case, here we use LinkedList for demonstration
   final private List<Book> books = new ArrayList<>();
    //    final private List<Book> books = new LinkedList<>();

    // Common functions for Array List include
    // add, get, set,
    // a. Add New Book
    public void addBook(Book book) {
        books.add(book);
    }
    

    // b. Get Book by Position
    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) return books.get(index);
        return null;
    }

    // c. Update Book Details
    public void updateBook(int index, Book newBook) {
        if (index >= 0 && index < books.size()) {
            books.set(index, newBook);
        }
    }

    // d.1 Delete Book by Position
    public void deleteBookByIndex(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
        }
    }

    // d.2 Delete Book by Property (e.g., bookId)
    public void deleteBookById(int bookId) {
        books.removeIf(book -> book.getBookId() == bookId);
    }

    // e. Display Books
    public void displayBooks() {
        books.forEach(System.out::println);
    }

    // f. Display Books Ordered By Property
    public void displayBooksSortedBy(String property, boolean ascending) {
        Comparator<Book> comparator = switch (property.toLowerCase()) {
            case "bookId" -> Comparator.comparing(Book::getBookId);
            case "bookname" -> Comparator.comparing(Book::getBookName);
            case "bookauthor" -> Comparator.comparing(Book::getBookAuthor);
            case "numberofcopies" -> Comparator.comparing(Book::getNumberOfCopies);
            case "datepublished" -> Comparator.comparing(Book::getDateOfPublication);
            default -> null;
        };

        if (comparator != null) {
            if (!ascending) comparator = comparator.reversed();
            books.stream().sorted(comparator).forEach(System.out::println);
        } else {
            System.out.println("Invalid property for sorting.");
        }
    }

    // g. Display Books Filtered By Property
    public void filterBooksBy(String property, String value) {
        switch (property.toLowerCase()) {
            case "bookId" -> books.stream()
                    .filter(book -> String.valueOf(book.getBookId()).equals(value))
                    .forEach(System.out::println);
            case "bookname" -> books.stream()
                    .filter(book -> book.getBookName().equalsIgnoreCase(value))
                    .forEach(System.out::println);
            case "bookauthor" -> books.stream()
                    .filter(book -> book.getBookAuthor().equalsIgnoreCase(value))
                    .forEach(System.out::println);
            case "numberofcopies" -> books.stream()
                    .filter(book -> String.valueOf(book.getNumberOfCopies()).equals(value))
                    .forEach(System.out::println);
            case "datepublished" -> books.stream()
                    .filter(book -> book.getDateOfPublication().toString().contains(value))
                    .forEach(System.out::println);
            default -> System.out.println("Invalid filter property.");
        }
    }
}
