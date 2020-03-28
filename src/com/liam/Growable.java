package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Growable extends GameObject{
    protected int age, health;
    protected Text healthUI;
    public Growable(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
    }

    @Override
    public void update() {
        super.update();

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.WHITE);

        gc.setFont(Font.font("Verdana", 20));
        gc.strokeText(Integer.toString(this.health), x + 48, y + 196);
        gc.fillText(Integer.toString(this.health), x + 48, y + 196);
    }
}
