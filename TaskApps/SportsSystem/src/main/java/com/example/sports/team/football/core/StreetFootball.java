package com.example.sports.team.football.core;


public interface  StreetFootball extends Football {
    /**
     * Method to set the play style for street football.
     * This method should be implemented by any class that implements the StreetFootball interface.
     */
    void setPlayStyle();

    /**
     * Method to set the type of street for street football.
     * This method should be implemented by any class that implements the StreetFootball interface.
     */

    void setStreetType(String streetType);
}