package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Represents a FlowerBed Object which contains an aggregation of FlowerDelegator's
 */
public class FlowerBed extends GameObject {

    /**
     * Used to draw the flowers over the background image of the FlowerBed
     */
    GraphicsContext foregroundContext;

    /**
     * An aggregation of FlowerDelegator Objects
     */
    ArrayList<FlowerDelegator> flowers;

    /**
     *  Instantiates the FlowerBed Object and sets up the necessary conditions to grow the flowers
     * @param gc The context provided by the canvas (layer) the Object resides on
     * @param foregroundContext Context for the foreground layer to allow the flowers to draw over the flowerbed
     * @param x The X position of the FlowerBed
     * @param y The Y position of the FlowerBed
     */
    public FlowerBed(GraphicsContext gc, GraphicsContext foregroundContext, double x, double y) {
        super(gc, x, y);
        img = new Image(Constants.FLOWER_BED);
        super.setSizeProperties();
        this.foregroundContext = foregroundContext;
        flowers = new ArrayList<FlowerDelegator>();
        plantSeeds();
        update();
    }

    /**
     * Draws the Sprite for the Object, clears the foreground layer and checks each flowers growth
     */
    @Override
    public void update() {
        super.update();
        foregroundContext.clearRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        for (FlowerDelegator obj : flowers) {
            obj.checkGrowth();
        }

    }

    /**
     * Plants a flower in each space on the grid
     */
    private void plantSeeds() {
        for (int i = 0; i < Constants.MAX_FLOWER_COUNT; i++) {
            double x = this.x + 128 * i;
            double y = this.y - 96;
            flowers.add(new FlowerDelegator(foregroundContext, x, y));
        }
    }

    /**
     * Retrieves a flower at a given index
     * @param index the location of the flower
     * @return the flower at the given index
     */
    public FlowerDelegator getFlower(int index) {
        return flowers.get(index);
    }
}
