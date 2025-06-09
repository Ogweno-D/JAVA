public class  Footballer implements  Comparable<Footballer>{
    private String name;
    private int assists;
    private int goals;


    public Footballer(String name, int assists, int goals){
        this.name = name;
        this.assists = assists;
        this.goals = goals;
    }

    // Getters for the Footballer attributes
    // These are used to access the private attributes of the Footballer class
    public String getName(){
        return name;
    }

    public int getAssists(){
        return assists;
    }

    public int getGoals(){
        return goals;
    }


    public int getTotalPoints(){
        return goals + assists;
    }

    // We can override the default comparable To function to
    // Compare the Footballers

    @Override
    public int compareTo( Footballer other){
        return Integer.compare(this.getTotalPoints(), other.getTotalPoints());
    }

    @Override
    public String toString(){
        return name + " - " + assists + " assists, " + goals + " goals, Total Points: " + getTotalPoints();
    }

}