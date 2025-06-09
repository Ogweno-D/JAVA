
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Example usage
public class Main {
    public static void main(String[] args) {
        List<HockeyPlayer> hockeyPlayers = Arrays.asList(
            new HockeyPlayer("Connor McDavid", 44, 79),
            new HockeyPlayer("Leon Draisaitl", 52, 58),
            new HockeyPlayer("Nathan MacKinnon", 51, 38),
            new HockeyPlayer("David Pastrnak", 61, 52)
        );

        List<Footballer> footballers = Arrays.asList(
            new Footballer("Lionel Messi", 9, 30),
            new Footballer("Cristiano Ronaldo", 7, 25),
            new Footballer("Kylian Mbappé", 8, 28),
            new Footballer("Neymar Jr.", 10, 20)
        );
        
        System.out.println("Players before sorting:");
        hockeyPlayers.forEach(System.out::println);
        
        // Natural sorting using Comparable
        Collections.sort(hockeyPlayers);
        
        System.out.println("\nPlayers sorted by total points:");
        hockeyPlayers.forEach(System.out::println);
        
        // Reverse order
        Collections.sort(hockeyPlayers, Collections.reverseOrder());
        System.out.println("\nTop scorers first:");
        hockeyPlayers.forEach(System.out::println);

        System.out.println("\nFootballers before sorting:");
        footballers.forEach(System.out::println);

        // Natural sorting using Comparable
        Collections.sort(footballers);
        System.out.println("\nFootballers sorted by total points:");
        footballers.forEach(System.out::println);

        // Reverse order
        Collections.sort(footballers, Collections.reverseOrder());
        System.out.println("\nTop scorers first:"); 
        footballers.forEach(System.out::println);
    }
}