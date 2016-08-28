package me.belakede.bors.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final Map<String, BorderPane> cache;

    @FXML
    private BorderPane parent;

    public MainController() {
        this.cache = new HashMap<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadContent("runs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showRuns(ActionEvent actionEvent) throws IOException {
        loadContent("runs");
    }

    public void showStatistics(ActionEvent actionEvent) throws IOException {
        loadContent("statistics");
    }

    private void loadContent(String key) throws IOException {
        if (!cache.containsKey(key)) {
            cache.put(key, FXMLLoader.load(getClass().getResource("../" + key + ".fxml")));
        }
        parent.setCenter(cache.get(key));
    }
}
