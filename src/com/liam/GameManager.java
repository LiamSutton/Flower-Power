package com.liam;

/**
 * Holds static values key to the state of the game loop
 */
public class GameManager {
    /**
     * The score of the player
     */
    static int score = 0;

    /**
     * How many lives the player has remaining
     */
    static int lives = Constants.MAX_LIVES;

    /**
     * Used to determine whether the game should end
     * @return if the user has 0 or less lives (we check for <= 0 in case 2 flowers died at the same time)
     */
    static boolean gameOver() {
        return lives <= 0;
    }

    /**
     * Sets the game to its initial state
     */
    static void resetGameVariables() {
        score = 0;
        lives = Constants.MAX_LIVES;
    }
}
