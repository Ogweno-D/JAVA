/**
 * Child interface extending Sport, adding a method specific to team sports.
 */
package com.example.sports.core;

public interface  TeamSport extends Sport {
    
    /**
     * Method to set the team formation specific to the team sport.
     * This method should be implemented by any class that implements the TeamSport interface.
     */
    void SetTeamFormation();
}