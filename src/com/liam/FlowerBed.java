package com.liam;

import com.liam.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FlowerBed extends GameObject {
    GraphicsContext foregroundContext;

    public FlowerBed(GraphicsContext gc, GraphicsContext foregroundContext, double x, double y, int xSize, int ySize) {
        super(gc, x, y, xSize, ySize);
        img = new Image("/Flowerbed.png");
        this.foregroundContext = foregroundContext;
        update();
    }

    @Override
    public void update() {
        super.update();
    }
}
