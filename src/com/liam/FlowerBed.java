package com.liam;

import com.liam.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FlowerBed extends GameObject {
    GraphicsContext foregroundContext;
    ArrayList<Growable> flowers;

    public FlowerBed(GraphicsContext gc, GraphicsContext foregroundContext, double x, double y) {
        super(gc, x, y);
        img = new Image("/Flowerbed.png");
        super.setSizeProperties();
        this.foregroundContext = foregroundContext;
        flowers = new ArrayList<Growable>();
        plantSeeds(8);
        update();
    }

    @Override
    public void update() {
        super.update();
        foregroundContext.clearRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        for (Growable obj : flowers) {
            ((Seed)obj).grow();
        }
    }

    public void plantSeeds(int amount) {
        for (int i = 0; i < amount; i++) {
            double x = this.x + 128 * i;
            double y = this.y - 96;
            flowers.add(new Seed(foregroundContext, x, y));
        }
    }
}
