package com.liam;

public class GameManager {
    static int score = 0;
    static int lives = 3;

    static boolean gameOver() {
        return lives <= 0;
    }
    static void resetGameVariables() {
        score = 0;
        lives = Constants.MAX_LIVES;
    }
}
