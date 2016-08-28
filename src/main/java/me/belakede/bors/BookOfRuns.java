package me.belakede.bors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookOfRuns extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/runs.fxml"));

        Scene scene = new Scene(root, 500, 650);

        stage.setTitle("Book of Runs");
        stage.setMinWidth(250);
        stage.setScene(scene);
        stage.show();
    }
}
