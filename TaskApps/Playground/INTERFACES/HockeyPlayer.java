
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

