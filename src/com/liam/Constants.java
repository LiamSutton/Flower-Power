package com.liam;

import javafx.scene.text.Font;

public final class Constants {

    private Constants(){}
    // Window Constants
    static final int SCREEN_WIDTH = 1024;
    static final int SCREEN_HEIGHT = 768;

    // Game Constants
    static final int MAX_LIVES = 3;
    static final int MAX_FLOWER_COUNT = 8;
    static final int DIRECTION_LEFT = -1;
    static final int DIRECTION_RIGHT = 1;
    static final int GRID_START = 0;
    static final int GRID_END = 7;
    static final int GRID_COL_WIDTH = 128;
    static final int[] SUN_Y_POSITIONS = {224, 128, 32, 0, 0, 32, 128, 224};

    // Wilting Constants
    static final double SEED_WILT_RATE = 0.04F;
    static final double SPROUT_WILT_RATE = 0.05F;
    static final double BUD_WILT_RATE = 0.07F;
    static final double BLOSSOM_WILT_RATE = 0.08F;
    static final double BLOOM_WILT_RATE = 0.10F;

    // Healing constants
    static final int SEED_HEAL_VALUE = 5;
    static final int SPROUT_HEAL_VALUE = 10;
    static final int BUD_HEAL_VALUE = 20;
    static final int BLOSSOM_HEAL_VALUE = 40;
    static final int BLOOM_HEAL_VALUE = 80;

    // UI Text Constants
    static final String GAME_TITLE = "Flower Power!";
    static final String MENU_TITLE = "Main Menu";
    static final String INFORMATION_TITLE = "How To Play";
    static final String GAME_OVER_TITLE = "GAME OVER";
    static final String GREETING_MESSAGE = "Welcome to Flower Power!";
    static final String OBJECTIVE_MESSAGE = "The aim of flower power is to keep all the plants in your flowerbed healthy\nuntil they grow enough that they can be harvested!\n" +
                                            "Every time a plant gets harvested you get 100 points and any time a plant dies\nyou lose a life, " +
                                            "the game ends when you lose 3 lives.";
    static final String WATERING_CAN_INSTRUCTION = "Watering-can controls: A = LEFT, D = RIGHT";
    static final String SUN_INSTRUCTION = "Sun controls: LEFT ARROW = LEFT, RIGHT ARROW = RIGHT";

    // Font Constants
    static final Font HEALTH_FONT = new Font("Consolas", 20);
    static final Font SCORE_FONT = new Font("Arial Black", 32);
    static final Font MENU_FONT = new Font("Verdana", 24);
    static final Font INSTRUCTIONS_FONT = new Font("Verdana", 22);
    static final Font HEADING_FONT = new Font("Verdana", 32);

    // Image Path Constants
    static final String SEED_HEALTHY = "/Seed.png";
    static final String SEED_WILTED = "/Wilted-Seed.png";
    static final String SPROUT_HEALTHY = "/Sprout.png";
    static final String SPROUT_WILTED = "/Wilted-Sprout.png";
    static final String BUD_HEALTHY = "/Bud.png";
    static final String BUD_WILTED = "/Wilted-Bud.png";
    static final String BLOSSOM_HEALTHY = "/Blossom.png";
    static final String BLOSSOM_WILTED = "/Wilted-Blossom.png";
    static final String BLOOM_HEALTHY = "/Bloom.png";
    static final String BLOOM_WILTED = "/Wilted-Bloom.png";
    static final String SUN = "/Sun.png";
    static final String WATERING_CAN = "/WateringCan.png";
    static final String FLOWER_BED = "/FlowerBed.png";
}
