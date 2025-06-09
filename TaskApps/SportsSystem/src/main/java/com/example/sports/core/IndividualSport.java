package  com.example.sports.core;

public interface IndividualSport extends Sport {

    void setTrainingRegimen();
    
    /**
     * Method to set the individual athlete's strategy for the sport.
     * This method should be implemented by any class that implements the IndividualSport interface.
     */
    //void setAthleteStrategy();
    
    /**
     * Method to train the individual athlete for the sport.
     * This method should be implemented by any class that implements the IndividualSport interface.
     */
    //void trainAthlete();
}