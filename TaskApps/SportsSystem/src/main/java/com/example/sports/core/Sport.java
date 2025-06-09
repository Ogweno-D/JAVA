/**
 * Defines an interface named Sport with the following methods:
 * - play()
 * - score()
 * - endGame()
 * - Additionally, include:
 * - A default method named startGame() that prints "The game has started!"
 * - A static method named displayRules() that prints "Displaying the rules of the game."
 */
package com.example.sports.core;

public interface  Sport {
    /**
     * Method to play the sport.
     * This method should be implemented by any class that implements the Sport interface.
     */
    void play();
    /**
     * Method to score in the sport.
     * This method should be implemented by any class that implements the Sport interface.
     */
    void score();

    /**
     * Method to end the game.
     * This method should be implemented by any class that implements the Sport interface.
     */
    void endGame();
    
    /**
     * Default method to start the game.
     * Prints a message indicating that the game has started.
     */

    default void startGame() {
        System.out.println("The game has started!");
    }
    
    /**
     * Static method to display the rules of the game.
     * Prints a message indicating that the rules are being displayed.
     */
    static void displayRules() {
        System.out.println("Displaying the rules of the game.");
    }
}