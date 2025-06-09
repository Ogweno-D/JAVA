import java.text.SimpleDateFormat;
                                                                                                                                        
public class Main {
    public static void main(String[] args) throws Exception {
        BookManager manager = new BookManager();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Add books
        manager.addBook(new Book(1, "Java Basics", "John Doe", 5, sdf.parse("2020-01-15")));
        manager.addBook(new Book(2, "Python Guide", "Jane Smith", 3, sdf.parse("2019-05-20")));
        manager.addBook(new Book(3, "Java Advanced", "John Doe", 2, sdf.parse("2021-03-10")));

        System.out.println("All Books:");
        manager.displayBooks();

        System.out.println("\nBook at index 1:");
        System.out.println(manager.getBook(1));

        System.out.println("\nUpdate book at index 2:");
        manager.updateBook(2, new Book(3, "Updated Java Advanced", "John Doe", 4, sdf.parse("2021-03-10")));
        manager.displayBooks();

        System.out.println("\nDelete book with ID 1:");
        manager.deleteBookById(1);
        manager.displayBooks();

        System.out.println("\nBooks sorted by bookName (ascending):");
        manager.displayBooksSortedBy("bookName", true);

        System.out.println("\nBooks filtered by bookAuthor = 'John Doe':");
        manager.filterBooksBy("bookAuthor", "John Doe");
    }
}
