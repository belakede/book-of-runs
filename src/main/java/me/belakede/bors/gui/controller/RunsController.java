package me.belakede.bors.gui.controller;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import me.belakede.bors.gui.control.RunSummary;
import me.belakede.bors.gui.model.RunDetails;
import me.belakede.bors.gui.task.ComponentDetailsCollectorTask;
import me.belakede.bors.gui.task.RunDetailsCollectorTask;
import org.controlsfx.control.StatusBar;

import java.net.URL;
import java.util.ResourceBundle;

public class RunsController implements Initializable {

    @FXML
    TextField runId;
    @FXML
    ScrollPane scrollPane;
    @FXML
    FlowPane runContainer;
    @FXML
    StatusBar statusBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fixElementsWidth();
    }

    public void addNewRun(ActionEvent actionEvent) {
        Task<RunDetails> task = new RunDetailsCollectorTask(Long.valueOf(runId.getText()));
        statusBar.textProperty().bind(task.messageProperty());
        statusBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {
            statusBar.textProperty().unbind();
            statusBar.progressProperty().unbind();
            runContainer.getChildren().add(new RunSummary(task.getValue()));
            downloadComponents(task.getValue());
        });
        new Thread(task).start();
    }

    private void downloadComponents(RunDetails details) {
        ComponentDetailsCollectorTask task = new ComponentDetailsCollectorTask(details);
        statusBar.textProperty().bind(task.messageProperty());
        statusBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {
            statusBar.textProperty().unbind();
            statusBar.progressProperty().unbind();
            details.componentsDetailsProperty().addAll(task.getValue().get());
            runId.clear();
        });
        new Thread(task).start();
    }

    private void fixElementsWidth() {
        scrollPane.viewportBoundsProperty().addListener((ov, oldBounds, bounds) -> {
            runContainer.setPrefWidth(bounds.getWidth());
            runContainer.setPrefHeight(bounds.getHeight());
        });
    }

}
