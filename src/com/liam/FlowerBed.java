package com.liam;

import com.liam.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FlowerBed extends GameObject {
    GraphicsContext foregroundContext;
    ArrayList<Growable> flowers;

    public FlowerBed(GraphicsContext gc, GraphicsContext foregroundContext, double x, double y, int xSize, int ySize) {
        super(gc, x, y, xSize, ySize);
        img = new Image("/Flowerbed.png");
        this.foregroundContext = foregroundContext;
        flowers = new ArrayList<Growable>();
        plantSeeds(8);
        update();
    }

    @Override
    public void update() {
        super.update();
    }

    public void plantSeeds(int amount) {
        for (int i = 0; i < amount; i++) {
            double x = this.x + 128 * i;
            double y = this.y - 96;
            flowers.add(new Seed(foregroundContext, x, y, 128, 128));
        }
    }
}
