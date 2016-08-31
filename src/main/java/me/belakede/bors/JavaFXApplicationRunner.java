package me.belakede.bors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JavaFXApplicationRunner extends Application implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/main.fxml"));

        Scene scene = new Scene(root, 500, 650);

        stage.setTitle("Book of Runs");
        stage.setMinWidth(250);
        stage.setScene(scene);
        stage.show();
    }

}
