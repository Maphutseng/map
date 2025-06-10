package com.example.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            URL fxmlLocation = getClass().getResource("/com/example/map/views/main_view.fxml");
            if (fxmlLocation == null) {
                throw new IllegalStateException("FXML file not found at /com/example/map/views/main_view.fxml");
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root, 800, 600);  // Set initial size

            stage.setTitle("Explore Lesotho");
            stage.setScene(scene);
            stage.setResizable(true);  // Allow resizing
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
