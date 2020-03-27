package com.liam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlowerPower extends Application {
    Pane root;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();
        scene = new Scene(root, 1024, 768);

        stage.setScene(scene);
        stage.show();
    }
}
