# Advanced Java Interfaces: Built-in Interfaces, Lambdas, and Design Patterns

## Table of Contents

1. [Java's Built-in Interfaces](#javas-built-in-interfaces)
2. [Functional Interfaces and Lambda Expressions](#functional-interfaces-and-lambda-expressions)
3. [Design Patterns with Interfaces](#design-patterns-with-interfaces)
4. [Practical Examples and Use Cases](#practical-examples-and-use-cases)

## Java's Built-in Interfaces

Java provides several powerful built-in interfaces that are fundamental to the language. Let's explore the most important ones with practical examples.

### 1. Comparable Interface

The `Comparable` interface allows objects to be compared and sorted naturally.

```java
// Hockey player class implementing Comparable
public class HockeyPlayer implements Comparable<HockeyPlayer> {
    private String name;
    private int goals;
    private int assists;
    
    public HockeyPlayer(String name, int goals, int assists) {
        this.name = name;
        this.goals = goals;
        this.assists = assists;
    }
    
    public int getTotalPoints() {
        return goals + assists;
    }
    
    // Natural ordering by total points (goals + assists)
    @Override
    public int compareTo(HockeyPlayer other) {
        return Integer.compare(this.getTotalPoints(), other.getTotalPoints());
    }
    
    @Override
    public String toString() {
        return name + " (G:" + goals + " A:" + assists + " P:" + getTotalPoints() + ")";
    }
    
    // Getters
    public String getName() { return name; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
}

// Example usage
public class ComparableExample {
    public static void main(String[] args) {
        List<HockeyPlayer> players = Arrays.asList(
            new HockeyPlayer("Connor McDavid", 44, 79),
            new HockeyPlayer("Leon Draisaitl", 52, 58),
            new HockeyPlayer("Nathan MacKinnon", 51, 38),
            new HockeyPlayer("David Pastrnak", 61, 52)
        );
        
        System.out.println("Players before sorting:");
        players.forEach(System.out::println);
        
        // Natural sorting using Comparable
        Collections.sort(players);
        
        System.out.println("\nPlayers sorted by total points:");
        players.forEach(System.out::println);
        
        // Reverse order
        Collections.sort(players, Collections.reverseOrder());
        System.out.println("\nTop scorers first:");
        players.forEach(System.out::println);
    }
}
```

### 2. Comparator Interface (Alternative to Comparable)

`Comparator` allows you to define multiple ways to compare objects:

```java
import java.util.Comparator;

public class ComparatorExamples {
    public static void main(String[] args) {
        List<HockeyPlayer> players = Arrays.asList(
            new HockeyPlayer("Connor McDavid", 44, 79),
            new HockeyPlayer("Leon Draisaitl", 52, 58),
            new HockeyPlayer("Nathan MacKinnon", 51, 38),
            new HockeyPlayer("David Pastrnak", 61, 52)
        );
        
        // Sort by goals only
        players.sort(Comparator.comparingInt(HockeyPlayer::getGoals));
        System.out.println("Sorted by goals:");
        players.forEach(System.out::println);
        
        // Sort by name alphabetically
        players.sort(Comparator.comparing(HockeyPlayer::getName));
        System.out.println("\nSorted by name:");
        players.forEach(System.out::println);
        
        // Complex sorting: by goals descending, then by assists descending
        players.sort(Comparator.comparingInt(HockeyPlayer::getGoals)
                              .reversed()
                              .thenComparing(Comparator.comparingInt(HockeyPlayer::getAssists).reversed()));
        System.out.println("\nSorted by goals (desc), then assists (desc):");
        players.forEach(System.out::println);
    }
}
```

### 3. Runnable Interface

`Runnable` represents a task that can be executed by a thread:

```java
// Traditional way
public class GameSimulator implements Runnable {
    private String teamName;
    private int duration;
    
    public GameSimulator(String teamName, int duration) {
        this.teamName = teamName;
        this.duration = duration;
    }
    
    @Override
    public void run() {
        System.out.println(teamName + " starting practice...");
        try {
            Thread.sleep(duration * 1000); // Simulate practice time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(teamName + " finished practice!");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        // Traditional approach
        Thread team1 = new Thread(new GameSimulator("Team Canada", 3));
        Thread team2 = new Thread(new GameSimulator("Team USA", 2));
        
        team1.start();
        team2.start();
        
        // Lambda approach (more on this below)
        Thread team3 = new Thread(() -> {
            System.out.println("Team India starting practice...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Team India finished practice!");
        });
        team3.start();
    }
}
```

### 4. Serializable Interface

`Serializable` allows objects to be converted to byte streams for storage or transmission:

```java
import java.io.*;

public class HockeyTeam implements Serializable {
    private static final long serialVersionUID = 1L; // Version control
    
    private String teamName;
    private String city;
    private List<String> players;
    private int wins;
    private int losses;
    
    public HockeyTeam(String teamName, String city) {
        this.teamName = teamName;
        this.city = city;
        this.players = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }
    
    // Methods to manage team
    public void addPlayer(String playerName) {
        players.add(playerName);
    }
    
    public void recordWin() { wins++; }
    public void recordLoss() { losses++; }
    
    @Override
    public String toString() {
        return teamName + " (" + city + ") - Record: " + wins + "-" + losses + 
               ", Players: " + players.size();
    }
    
    // Getters and setters...
    public String getTeamName() { return teamName; }
    public String getCity() { return city; }
    public List<String> getPlayers() { return players; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
}

// Serialization example
public class SerializationExample {
    public static void main(String[] args) {
        // Create and populate a team
        HockeyTeam team = new HockeyTeam("Maple Leafs", "Toronto");
        team.addPlayer("Auston Matthews");
        team.addPlayer("Mitch Marner");
        team.addPlayer("William Nylander");
        team.recordWin();
        team.recordWin();
        team.recordLoss();
        
        // Serialize the team
        try {
            FileOutputStream fileOut = new FileOutputStream("team.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(team);
            out.close();
            fileOut.close();
            System.out.println("Team serialized successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialize the team
        try {
            FileInputStream fileIn = new FileInputStream("team.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HockeyTeam deserializedTeam = (HockeyTeam) in.readObject();
            in.close();
            fileIn.close();
            
            System.out.println("Deserialized team: " + deserializedTeam);
            System.out.println("Players: " + deserializedTeam.getPlayers());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## Functional Interfaces and Lambda Expressions

Functional interfaces have exactly one abstract method and can be used with lambda expressions.

### Key Functional Interfaces

```java
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaceExamples {
    public static void main(String[] args) {
        List<HockeyPlayer> players = Arrays.asList(
            new HockeyPlayer("Connor McDavid", 44, 79),
            new HockeyPlayer("Leon Draisaitl", 52, 58),
            new HockeyPlayer("Nathan MacKinnon", 51, 38),
            new HockeyPlayer("David Pastrnak", 61, 52)
        );
        
        // 1. Predicate<T> - returns boolean
        Predicate<HockeyPlayer> isEliteScorer = player -> player.getTotalPoints() > 100;
        Predicate<HockeyPlayer> isGoalScorer = player -> player.getGoals() > 50;
        
        System.out.println("Elite scorers (100+ points):");
        players.stream()
               .filter(isEliteScorer)
               .forEach(System.out::println);
        
        System.out.println("\nGoal scorers (50+ goals):");
        players.stream()
               .filter(isGoalScorer)
               .forEach(System.out::println);
        
        // 2. Function<T, R> - transforms input to output
        Function<HockeyPlayer, String> playerSummary = 
            player -> player.getName() + " has " + player.getTotalPoints() + " points";
        
        System.out.println("\nPlayer summaries:");
        players.stream()
               .map(playerSummary)
               .forEach(System.out::println);
        
        // 3. Consumer<T> - accepts input, returns nothing
        Consumer<HockeyPlayer> announcePlayer = 
            player -> System.out.println("Now playing: " + player.getName() + "!");
        
        System.out.println("\nStarting lineup:");
        players.forEach(announcePlayer);
        
        // 4. Supplier<T> - provides a value
        Supplier<HockeyPlayer> createRandomPlayer = () -> 
            new HockeyPlayer("Rookie " + (int)(Math.random() * 100), 
                           (int)(Math.random() * 30), 
                           (int)(Math.random() * 40));
        
        System.out.println("\nRandom player: " + createRandomPlayer.get());
        
        // 5. BinaryOperator<T> - takes two T, returns T
        BinaryOperator<HockeyPlayer> betterPlayer = (p1, p2) -> 
            p1.getTotalPoints() > p2.getTotalPoints() ? p1 : p2;
        
        HockeyPlayer bestPlayer = players.stream()
                                        .reduce(betterPlayer)
                                        .orElse(null);
        System.out.println("\nBest player: " + bestPlayer);
    }
}
```

### Custom Functional Interfaces

```java
// Custom functional interface for hockey operations
@FunctionalInterface
public interface HockeyOperation {
    int calculate(HockeyPlayer player);
    
    // Default method allowed in functional interfaces
    default String getPlayerInfo(HockeyPlayer player) {
        return player.getName() + ": " + calculate(player);
    }
}

// Usage examples
public class CustomFunctionalExample {
    public static void main(String[] args) {
        HockeyPlayer player = new HockeyPlayer("Sidney Crosby", 31, 53);
        
        // Different operations using lambda expressions
        HockeyOperation pointsCalculator = p -> p.getGoals() + p.getAssists();
        HockeyOperation goalsPerGame = p -> p.getGoals() / 82; // Assuming 82 games
        HockeyOperation assistsDoubled = p -> p.getAssists() * 2;
        
        System.out.println("Total points: " + pointsCalculator.calculate(player));
        System.out.println("Goals per game: " + goalsPerGame.calculate(player));
        System.out.println("Assists doubled: " + assistsDoubled.calculate(player));
        
        // Using default method
        System.out.println(pointsCalculator.getPlayerInfo(player));
    }
}
```

## Design Patterns with Interfaces

Let's explore common design patterns that heavily utilize interfaces.

### 1. Strategy Pattern

The Strategy pattern allows you to define a family of algorithms and make them interchangeable:

```java
// Strategy interface
interface ScoringStrategy {
    int calculatePoints(int goals, int assists, int games);
    String getStrategyName();
}

// Concrete strategies
class StandardScoring implements ScoringStrategy {
    @Override
    public int calculatePoints(int goals, int assists, int games) {
        return goals + assists;
    }
    
    @Override
    public String getStrategyName() {
        return "Standard NHL Scoring";
    }
}

class FantasyScoring implements ScoringStrategy {
    @Override
    public int calculatePoints(int goals, int assists, int games) {
        // Goals worth more in fantasy
        return (goals * 2) + assists;
    }
    
    @Override
    public String getStrategyName() {
        return "Fantasy Hockey Scoring";
    }
}

class PerGameScoring implements ScoringStrategy {
    @Override
    public int calculatePoints(int goals, int assists, int games) {
        return games > 0 ? (goals + assists) / games : 0;
    }
    
    @Override
    public String getStrategyName() {
        return "Points Per Game";
    }
}

// Context class
class PlayerEvaluator {
    private ScoringStrategy strategy;
    
    public PlayerEvaluator(ScoringStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(ScoringStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void evaluatePlayer(HockeyPlayer player, int games) {
        int score = strategy.calculatePoints(player.getGoals(), player.getAssists(), games);
        System.out.println(strategy.getStrategyName() + " - " + 
                          player.getName() + ": " + score + " points");
    }
}

// Strategy pattern example
public class StrategyPatternExample {
    public static void main(String[] args) {
        HockeyPlayer player = new HockeyPlayer("Connor McDavid", 44, 79);
        PlayerEvaluator evaluator = new PlayerEvaluator(new StandardScoring());
        
        // Evaluate using different strategies
        evaluator.evaluatePlayer(player, 82);
        
        evaluator.setStrategy(new FantasyScoring());
        evaluator.evaluatePlayer(player, 82);
        
        evaluator.setStrategy(new PerGameScoring());
        evaluator.evaluatePlayer(player, 82);
    }
}
```

### 2. Observer Pattern

The Observer pattern allows objects to be notified of changes in other objects:

```java
import java.util.*;

// Observer interface
interface GameObserver {
    void onGoalScored(String team, String player, int homeScore, int awayScore);
    void onPeriodEnd(int period, int homeScore, int awayScore);
    void onGameEnd(String winner, int homeScore, int awayScore);
}

// Subject interface
interface GameSubject {
    void addObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyObservers();
}

// Concrete observers
class ScoreboardDisplay implements GameObserver {
    @Override
    public void onGoalScored(String team, String player, int homeScore, int awayScore) {
        System.out.println("SCOREBOARD: GOAL! " + team + " - " + player);
        System.out.println("SCOREBOARD: Score " + homeScore + " - " + awayScore);
    }
    
    @Override
    public void onPeriodEnd(int period, int homeScore, int awayScore) {
        System.out.println("SCOREBOARD: End of Period " + period);
    }
    
    @Override
    public void onGameEnd(String winner, int homeScore, int awayScore) {
        System.out.println("SCOREBOARD: GAME OVER! Winner: " + winner);
    }
}

class RadioAnnouncer implements GameObserver {
    @Override
    public void onGoalScored(String team, String player, int homeScore, int awayScore) {
        System.out.println("RADIO: What a shot by " + player + "! " + team + " takes the lead!");
    }
    
    @Override
    public void onPeriodEnd(int period, int homeScore, int awayScore) {
        System.out.println("RADIO: That's the end of period " + period + 
                          ", folks. Great hockey action!");
    }
    
    @Override
    public void onGameEnd(String winner, int homeScore, int awayScore) {
        System.out.println("RADIO: Ladies and gentlemen, " + winner + 
                          " wins in a fantastic game!");
    }
}

class StatisticsTracker implements GameObserver {
    private Map<String, Integer> teamGoals = new HashMap<>();
    
    @Override
    public void onGoalScored(String team, String player, int homeScore, int awayScore) {
        teamGoals.put(team, teamGoals.getOrDefault(team, 0) + 1);
        System.out.println("STATS: " + team + " now has " + teamGoals.get(team) + " goals");
    }
    
    @Override
    public void onPeriodEnd(int period, int homeScore, int awayScore) {
        System.out.println("STATS: Period " + period + " stats recorded");
    }
    
    @Override
    public void onGameEnd(String winner, int homeScore, int awayScore) {
        System.out.println("STATS: Final statistics compiled for " + winner);
    }
}

// Concrete subject
class HockeyGameSubject implements GameSubject {
    private List<GameObserver> observers = new ArrayList<>();
    private String homeTeam;
    private String awayTeam;
    private int homeScore = 0;
    private int awayScore = 0;
    private int currentPeriod = 1;
    
    public HockeyGameSubject(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
    
    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        // This is called by specific event methods
    }
    
    public void homeGoalScored(String player) {
        homeScore++;
        for (GameObserver observer : observers) {
            observer.onGoalScored(homeTeam, player, homeScore, awayScore);
        }
    }
    
    public void awayGoalScored(String player) {
        awayScore++;
        for (GameObserver observer : observers) {
            observer.onGoalScored(awayTeam, player, homeScore, awayScore);
        }
    }
    
    public void endPeriod() {
        for (GameObserver observer : observers) {
            observer.onPeriodEnd(currentPeriod, homeScore, awayScore);
        }
        currentPeriod++;
    }
    
    public void endGame() {
        String winner = homeScore > awayScore ? homeTeam : awayTeam;
        for (GameObserver observer : observers) {
            observer.onGameEnd(winner, homeScore, awayScore);
        }
    }
}

// Observer pattern example
public class ObserverPatternExample {
    public static void main(String[] args) {
        // Create the game
        HockeyGameSubject game = new HockeyGameSubject("Toronto Maple Leafs", "Montreal Canadiens");
        
        // Create observers
        ScoreboardDisplay scoreboard = new ScoreboardDisplay();
        RadioAnnouncer radio = new RadioAnnouncer();
        StatisticsTracker stats = new StatisticsTracker();
        
        // Register observers
        game.addObserver(scoreboard);
        game.addObserver(radio);
        game.addObserver(stats);
        
        // Simulate game events
        System.out.println("=== Game Start ===");
        game.homeGoalScored("Auston Matthews");
        System.out.println();
        
        game.awayGoalScored("Nick Suzuki");
        System.out.println();
        
        game.endPeriod();
        System.out.println();
        
        game.homeGoalScored("Mitch Marner");
        System.out.println();
        
        game.endGame();
    }
}
```

### 3. Command Pattern

The Command pattern encapsulates requests as objects:

```java
// Command interface
interface GameCommand {
    void execute();
    void undo();
    String getDescription();
}

// Receiver - the object that performs the actual work
class HockeyGameReceiver {
    private String homeTeam;
    private String awayTeam;
    private int homeScore = 0;
    private int awayScore = 0;
    
    public HockeyGameReceiver(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
    
    public void scoreHomeGoal() {
        homeScore++;
        System.out.println(homeTeam + " scores! Score: " + homeScore + "-" + awayScore);
    }
    
    public void scoreAwayGoal() {
        awayScore++;
        System.out.println(awayTeam + " scores! Score: " + homeScore + "-" + awayScore);
    }
    
    public void undoHomeGoal() {
        if (homeScore > 0) {
            homeScore--;
            System.out.println("Home goal removed. Score: " + homeScore + "-" + awayScore);
        }
    }
    
    public void undoAwayGoal() {
        if (awayScore > 0) {
            awayScore--;
            System.out.println("Away goal removed. Score: " + homeScore + "-" + awayScore);
        }
    }
    
    public void displayScore() {
        System.out.println("Current score: " + homeTeam + " " + homeScore + 
                          " - " + awayTeam + " " + awayScore);
    }
}

// Concrete commands
class HomeGoalCommand implements GameCommand {
    private HockeyGameReceiver game;
    
    public HomeGoalCommand(HockeyGameReceiver game) {
        this.game = game;
    }
    
    @Override
    public void execute() {
        game.scoreHomeGoal();
    }
    
    @Override
    public void undo() {
        game.undoHomeGoal();
    }
    
    @Override
    public String getDescription() {
        return "Home team goal";
    }
}

class AwayGoalCommand implements GameCommand {
    private HockeyGameReceiver game;
    
    public AwayGoalCommand(HockeyGameReceiver game) {
        this.game = game;
    }
    
    @Override
    public void execute() {
        game.scoreAwayGoal();
    }
    
    @Override
    public void undo() {
        game.undoAwayGoal();
    }
    
    @Override
    public String getDescription() {
        return "Away team goal";
    }
}

// Invoker
class GameController {
    private List<GameCommand> commandHistory = new ArrayList<>();
    private int currentCommandIndex = -1;
    
    public void executeCommand(GameCommand command) {
        // Remove any commands after current position (for redo functionality)
        if (currentCommandIndex < commandHistory.size() - 1) {
            commandHistory = commandHistory.subList(0, currentCommandIndex + 1);
        }
        
        commandHistory.add(command);
        currentCommandIndex++;
        command.execute();
    }
    
    public void undo() {
        if (currentCommandIndex >= 0) {
            GameCommand command = commandHistory.get(currentCommandIndex);
            command.undo();
            currentCommandIndex--;
            System.out.println("Undid: " + command.getDescription());
        } else {
            System.out.println("Nothing to undo");
        }
    }
    
    public void redo() {
        if (currentCommandIndex < commandHistory.size() - 1) {
            currentCommandIndex++;
            GameCommand command = commandHistory.get(currentCommandIndex);
            command.execute();
            System.out.println("Redid: " + command.getDescription());
        } else {
            System.out.println("Nothing to redo");
        }
    }
    
    public void showHistory() {
        System.out.println("Command History:");
        for (int i = 0; i < commandHistory.size(); i++) {
            String marker = (i == currentCommandIndex) ? " -> " : "    ";
            System.out.println(marker + commandHistory.get(i).getDescription());
        }
    }
}

// Command pattern example
public class CommandPatternExample {
    public static void main(String[] args) {
        // Create receiver and invoker
        HockeyGameReceiver game = new HockeyGameReceiver("Bruins", "Rangers");
        GameController controller = new GameController();
        
        // Create commands
        GameCommand homeGoal = new HomeGoalCommand(game);
        GameCommand awayGoal = new AwayGoalCommand(game);
        
        // Execute commands
        controller.executeCommand(homeGoal);
        controller.executeCommand(awayGoal);
        controller.executeCommand(homeGoal);
        
        game.displayScore();
        controller.showHistory();
        
        // Undo operations
        System.out.println("\n--- Undo Operations ---");
        controller.undo();
        controller.undo();
        
        // Redo operations
        System.out.println("\n--- Redo Operations ---");
        controller.redo();
        
        game.displayScore();
    }
}
```

## Practical Examples and Use Cases

### Stream API with Functional Interfaces

```java
public class StreamApiExample {
    public static void main(String[] args) {
        List<HockeyPlayer> players = Arrays.asList(
            new HockeyPlayer("Connor McDavid", 44, 79),
            new HockeyPlayer("Leon Draisaitl", 52, 58),
            new HockeyPlayer("Nathan MacKinnon", 51, 38),
            new HockeyPlayer("David Pastrnak", 61, 52),
            new HockeyPlayer("Artemi Panarin", 29, 63),
            new HockeyPlayer("Nikita Kucherov", 30, 74)
        );
        
        System.out.println("=== Stream API Examples ===");
        
        // Filter and collect elite players
        List<HockeyPlayer> elitePlayers = players.stream()
            .filter(p -> p.getTotalPoints() > 100)
            .sorted(Comparator.comparingInt(HockeyPlayer::getTotalPoints).reversed())
            .collect(Collectors.toList());
        
        System.out.println("Elite players (100+ points):");
        elitePlayers.forEach(System.out::println);
        
        // Group players by goal scoring ability
        Map<String, List<HockeyPlayer>> playersByGoalTier = players.stream()
            .collect(Collectors.groupingBy(p -> {
                if (p.getGoals() >= 50) return "Elite Goal Scorer";
                else if (p.getGoals() >= 30) return "Good Goal Scorer";
                else return "Playmaker";
            }));
        
        System.out.println("\nPlayers grouped by scoring style:");
        playersByGoalTier.forEach((tier, playerList) -> {
            System.out.println(tier + ":");
            playerList.forEach(p -> System.out.println("  " + p));
        });
        
        // Statistical analysis
        OptionalDouble avgPoints = players.stream()
            .mapToInt(HockeyPlayer::getTotalPoints)
            .average();
        
        int totalGoals = players.stream()
            .mapToInt(HockeyPlayer::getGoals)
            .sum();
        
        HockeyPlayer topAssister = players.stream()
            .max(Comparator.comparingInt(HockeyPlayer::getAssists))
            .orElse(null);
        
        System.out.println("\n=== Statistics ===");
        System.out.println("Average points: " + avgPoints.orElse(0.0));
        System.out.println("Total goals: " + totalGoals);
        System.out.println("Top assist leader: " + topAssister);
    }
}
```

### Event-Driven Architecture with Functional Interfaces

```java
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

// Event-driven hockey game system
public class EventDrivenHockeyGame {
    private List<Consumer<GameEvent>> eventListeners = new CopyOnWriteArrayList<>();
    private String homeTeam;
    private String awayTeam;
    
    public EventDrivenHockeyGame(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
    
    // Subscribe to events using functional interfaces
    public void onGameEvent(Consumer<GameEvent> listener) {
        eventListeners.add(listener);
    }
    
    // Publish events
    private void publishEvent(GameEvent event) {
        eventListeners.forEach(listener -> listener.accept(event));
    }
    
    // Game actions
    public void scoreGoal(String team, String player) {
        publishEvent(new GameEvent("GOAL", team, player, System.currentTimeMillis()));
    }
    
    public void penalty(String team, String player, String infraction) {
        publishEvent(new GameEvent("PENALTY", team, player + " - " + infraction, 
                                  System.currentTimeMillis()));
    }
    
    public void periodEnd(int period) {
        publishEvent(new GameEvent("PERIOD_END", "GAME", "Period " + period, 
                                  System.currentTimeMillis()));
    }
    
    // Event data class
    public static class GameEvent {
        public final String type;
        public final String team;
        public final String details;
        public final long timestamp;
        
        public GameEvent(String type, String team, String details, long timestamp) {
            this.type = type;
            this.team = team;
            this.details = details;
            this.timestamp = timestamp;
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s: %s - %s", 
                               new java.util.Date(timestamp), type, team, details);
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        EventDrivenHockeyGame game = new EventDrivenHockeyGame("Maple Leafs", "Bruins");
        
        // Set up event listeners using lambda expressions
        game.onGameEvent(event -> {
            if ("GOAL".equals(event.type)) {
                System.out.println("🚨 GOAL ALERT: " + event.details + " for " + event.team);
            }
        });
        
        game.onGameEvent(event -> {
            if ("PENALTY".equals(event.type)) {
                System.out.println("⚠️ PENALTY: " + event.team + " - " + event.details);
            }
        });
        
        game.onGameEvent(event -> {
            System.out.println("📊 Event logged: " + event);
        });
        
        // Simulate game events
        game.scoreGoal("Maple Leafs", "Auston Matthews");
        game.penalty("Bruins", "Brad Marchand", "Slashing");
        game.scoreGoal("Bruins", "David Pastrnak");
        game.periodEnd(1);
    }
}
```

### Builder Pattern with Functional Interfaces

```java
import java.util.function.Consumer;

// Builder pattern enhanced with functional interfaces
public class HockeyPlayerBuilder {
    private String name;
    private int jersey;
    private String position;
    private int goals;
    private int assists;
    private String team;
    private boolean captain;
    
    public HockeyPlayerBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public HockeyPlayerBuilder jersey(int jersey) {
        this.jersey = jersey;
        return this;
    }
    
    public HockeyPlayerBuilder position(String position) {
        this.position = position;
        return this;
    }
    
    public HockeyPlayerBuilder stats(int goals, int assists) {
        this.goals = goals;
        this.assists = assists;
        return this;
    }
    
    public HockeyPlayerBuilder team(String team) {
        this.team = team;
        return this;
    }
    
    public HockeyPlayerBuilder captain(boolean captain) {
        this.captain = captain;
        return this;
    }
    
    // Functional interface approach for configuration
    public HockeyPlayerBuilder configure(Consumer<HockeyPlayerBuilder> configurator) {
        configurator.accept(this);
        return this;
    }
    
    public DetailedHockeyPlayer build() {
        return new DetailedHockeyPlayer(name, jersey, position, goals, assists, team, captain);
    }
    
    // Enhanced player class
    public static class DetailedHockeyPlayer {
        private final String name;
        private final int jersey;
        private final String position;
        private final int goals;
        private final int assists;
        private final String team;
        private final boolean captain;
        
        public DetailedHockeyPlayer(String name, int jersey, String position, 
                                  int goals, int assists, String team, boolean captain) {
            this.name = name;
            this.jersey = jersey;
            this.position = position;
            this.goals = goals;
            this.assists = assists;
            this.team = team;
            this.captain = captain;
        }
        
        @Override
        public String toString() {
            String captainBadge = captain ? " (C)" : "";
            return String.format("#%d %s%s - %s (%s) - %dG %dA %dP", 
                               jersey, name, captainBadge, position, team, 
                               goals, assists, goals + assists);
        }
        
        // Getters
        public String getName() { return name; }
        public int getJersey() { return jersey; }
        public String getPosition() { return position; }
        public int getGoals() { return goals; }
        public int getAssists() { return assists; }
        public int getTotalPoints() { return goals + assists; }
        public String getTeam() { return team; }
        public boolean isCaptain() { return captain; }
    }
    
    // Usage examples
    public static void main(String[] args) {
        // Traditional builder pattern
        DetailedHockeyPlayer player1 = new HockeyPlayerBuilder()
            .name("Connor McDavid")
            .jersey(97)
            .position("Center")
            .team("Edmonton Oilers")
            .stats(44, 79)
            .captain(true)
            .build();
        
        // Functional configuration approach
        DetailedHockeyPlayer player2 = new HockeyPlayerBuilder()
            .configure(builder -> {
                builder.name("Leon Draisaitl")
                       .jersey(29)
                       .position("Center")
                       .team("Edmonton Oilers")
                       .stats(52, 58);
            })
            .build();
        
        // Multiple configuration steps
        DetailedHockeyPlayer player3 = new HockeyPlayerBuilder()
            .name("David Pastrnak")
            .configure(builder -> builder.jersey(88).position("Right Wing"))
            .configure(builder -> builder.team("Boston Bruins").stats(61, 52))
            .build();
        
        System.out.println("Built players:");
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
    }
}
```

### Validation Framework with Functional Interfaces

```java
import java.util.function.Predicate;
import java.util.function.Function;

// Validation framework using functional interfaces
public class PlayerValidationFramework {
    
    // Validation result
    public static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;
        
        private ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }
        
        public static ValidationResult valid() {
            return new ValidationResult(true, null);
        }
        
        public static ValidationResult invalid(String message) {
            return new ValidationResult(false, message);
        }
        
        public boolean isValid() { return valid; }
        public String getErrorMessage() { return errorMessage; }
        
        // Combine validation results
        public ValidationResult and(ValidationResult other) {
            if (!this.valid) return this;
            return other;
        }
        
        @Override
        public String toString() {
            return valid ? "VALID" : "INVALID: " + errorMessage;
        }
    }
    
    // Validator interface
    @FunctionalInterface
    public interface Validator<T> {
        ValidationResult validate(T item);
        
        // Combine validators
        default Validator<T> and(Validator<T> other) {
            return item -> this.validate(item).and(other.validate(item));
        }
    }
    
    // Predefined validators using functional interfaces
    public static class PlayerValidators {
        public static Validator<DetailedHockeyPlayer> hasName() {
            return player -> player.getName() != null && !player.getName().trim().isEmpty()
                ? ValidationResult.valid()
                : ValidationResult.invalid("Player must have a name");
        }
        
        public static Validator<DetailedHockeyPlayer> validJerseyNumber() {
            return player -> player.getJersey() >= 1 && player.getJersey() <= 99
                ? ValidationResult.valid()
                : ValidationResult.invalid("Jersey number must be between 1 and 99");
        }
        
        public static Validator<DetailedHockeyPlayer> hasPosition() {
            return player -> {
                String pos = player.getPosition();
                return pos != null && (pos.equals("Center") || pos.equals("Left Wing") || 
                                     pos.equals("Right Wing") || pos.equals("Defense") || 
                                     pos.equals("Goalie"))
                    ? ValidationResult.valid()
                    : ValidationResult.invalid("Invalid position: " + pos);
            };
        }
        
        public static Validator<DetailedHockeyPlayer> reasonableStats() {
            return player -> {
                int goals = player.getGoals();
                int assists = player.getAssists();
                return goals >= 0 && assists >= 0 && (goals + assists) <= 200
                    ? ValidationResult.valid()
                    : ValidationResult.invalid("Unreasonable statistics");
            };
        }
        
        // Custom validator using predicate
        public static Validator<DetailedHockeyPlayer> custom(
                Predicate<DetailedHockeyPlayer> predicate, String errorMessage) {
            return player -> predicate.test(player) 
                ? ValidationResult.valid() 
                : ValidationResult.invalid(errorMessage);
        }
    }
    
    // Validation service
    public static class PlayerValidationService {
        private final Validator<DetailedHockeyPlayer> validator;
        
        public PlayerValidationService() {
            // Combine all standard validations
            this.validator = PlayerValidators.hasName()
                .and(PlayerValidators.validJerseyNumber())
                .and(PlayerValidators.hasPosition())
                .and(PlayerValidators.reasonableStats());
        }
        
        public PlayerValidationService(Validator<DetailedHockeyPlayer> customValidator) {
            this.validator = customValidator;
        }
        
        public ValidationResult validate(DetailedHockeyPlayer player) {
            return validator.validate(player);
        }
        
        public List<DetailedHockeyPlayer> validatePlayers(List<DetailedHockeyPlayer> players) {
            return players.stream()
                .filter(player -> {
                    ValidationResult result = validate(player);
                    if (!result.isValid()) {
                        System.err.println("Invalid player " + player.getName() + ": " + 
                                         result.getErrorMessage());
                    }
                    return result.isValid();
                })
                .collect(Collectors.toList());
        }
    }
    
    // Usage example
    public static void main(String[] args) {
        // Create test players
        List<HockeyPlayerBuilder.DetailedHockeyPlayer> players = Arrays.asList(
            new HockeyPlayerBuilder()
                .name("Connor McDavid")
                .jersey(97)
                .position("Center")
                .team("Edmonton Oilers")
                .stats(44, 79)
                .build(),
            
            new HockeyPlayerBuilder()
                .name("") // Invalid - no name
                .jersey(88)
                .position("Right Wing")
                .team("Boston Bruins")
                .stats(61, 52)
                .build(),
            
            new HockeyPlayerBuilder()
                .name("Invalid Jersey")
                .jersey(150) // Invalid - jersey number too high
                .position("Defense")
                .team("Some Team")
                .stats(10, 20)
                .build(),
            
            new HockeyPlayerBuilder()
                .name("Leon Draisaitl")
                .jersey(29)
                .position("Center")
                .team("Edmonton Oilers")
                .stats(52, 58)
                .build()
        );
        
        // Standard validation
        PlayerValidationService standardValidator = new PlayerValidationService();
        System.out.println("=== Standard Validation ===");
        List<HockeyPlayerBuilder.DetailedHockeyPlayer> validPlayers = 
            standardValidator.validatePlayers(players);
        
        System.out.println("Valid players:");
        validPlayers.forEach(System.out::println);
        
        // Custom validation - only elite players
        PlayerValidationService eliteValidator = new PlayerValidationService(
            PlayerValidators.hasName()
                .and(PlayerValidators.validJerseyNumber())
                .and(PlayerValidators.hasPosition())
                .and(PlayerValidators.custom(
                    player -> player.getTotalPoints() > 100,
                    "Player must have more than 100 points to be elite"
                ))
        );
        
        System.out.println("\n=== Elite Player Validation ===");
        List<HockeyPlayerBuilder.DetailedHockeyPlayer> elitePlayers = 
            eliteValidator.validatePlayers(validPlayers);
        
        System.out.println("Elite players:");
        elitePlayers.forEach(System.out::println);
    }
}
```

## Key Takeaways and Best Practices

### 1. When to Use Each Interface Type

**Built-in Interfaces:**

- Use `Comparable` when objects have a natural ordering
- Use `Comparator` when you need multiple ways to compare objects
- Use `Runnable` for simple background tasks
- Use `Serializable` when objects need to be persisted or transmitted

**Functional Interfaces:**

- Use for simple, single-purpose operations
- Combine with Stream API for powerful data processing
- Great for event handling and callback mechanisms
- Perfect for customizable behavior injection

**Design Patterns:**

- **Strategy**: When you have multiple algorithms for the same task
- **Observer**: When multiple objects need to react to state changes
- **Command**: When you need to queue, log, or undo operations

### 2. Performance Considerations

```java
// Performance comparison example
public class PerformanceComparison {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(1, 1_000_000).boxed().collect(Collectors.toList());
        
        // Traditional approach
        long start = System.nanoTime();
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num * 2);
            }
        }
        long traditional = System.nanoTime() - start;
        
        // Stream approach
        start = System.nanoTime();
        List<Integer> evenNumbersStream = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .collect(Collectors.toList());
        long stream = System.nanoTime() - start;
        
        // Parallel stream approach
        start = System.nanoTime();
        List<Integer> evenNumbersParallel = numbers.parallelStream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .collect(Collectors.toList());
        long parallel = System.nanoTime() - start;
        
        System.out.println("Traditional loop: " + traditional / 1_000_000 + " ms");
        System.out.println("Stream API: " + stream / 1_000_000 + " ms");
        System.out.println("Parallel stream: " + parallel / 1_000_000 + " ms");
    }
}
```

### 3. Testing with Interfaces

```java
// Mock implementations for testing
public class TestableHockeyGame {
    private final GameDataSource dataSource;
    private final NotificationService notificationService;
    
    public TestableHockeyGame(GameDataSource dataSource, NotificationService notificationService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
    }
    
    public void scoreGoal(String playerName) {
        // Business logic
        dataSource.saveGoal(playerName);
        notificationService.notifyGoal(playerName);
    }
}

// Interfaces for dependencies
interface GameDataSource {
    void saveGoal(String player);
    List<String> getGoals();
}

interface NotificationService {
    void notifyGoal(String player);
}

// Test example with mock implementations
public class HockeyGameTest {
    public static void main(String[] args) {
        // Mock implementations using lambda expressions
        GameDataSource mockDataSource = new GameDataSource() {
            private List<String> goals = new ArrayList<>();
            
            @Override
            public void saveGoal(String player) {
                goals.add(player);
                System.out.println("Saved goal for: " + player);
            }
            
            @Override
            public List<String> getGoals() {
                return new ArrayList<>(goals);
            }
        };
        
        NotificationService mockNotificationService = player -> 
            System.out.println("Notification sent for goal by: " + player);
        
        // Test the game
        TestableHockeyGame game = new TestableHockeyGame(mockDataSource, mockNotificationService);
        game.scoreGoal("Connor McDavid");
        game.scoreGoal("Leon Draisaitl");
        
        System.out.println("Total goals: " + mockDataSource.getGoals().size());
    }
}
```

This comprehensive guide demonstrates how interfaces form the backbone of flexible, maintainable Java applications. From built-in interfaces that provide essential functionality, to functional interfaces that enable modern programming paradigms, to design patterns that solve common architectural challenges - interfaces are essential tools for any Java developer.

The key is understanding when and how to use each type of interface, combining them effectively to create robust, testable, and scalable applications.
