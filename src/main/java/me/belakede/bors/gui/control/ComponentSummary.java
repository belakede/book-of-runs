package me.belakede.bors.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import me.belakede.bors.gui.model.ComponentDetails;

import java.io.IOException;

public class ComponentSummary extends HBox {

    private final ObjectProperty<ComponentDetails> componentDetails = new SimpleObjectProperty<>();

    @FXML
    private Label name;
    @FXML
    private StatusButton status;

    public ComponentSummary() {
        this(new ComponentDetails());
    }

    public ComponentSummary(ComponentDetails componentDetails) {
        initFields(componentDetails);
        loadFxml();
        hookupChangeListeners();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentSummary that = (ComponentSummary) o;

        return componentDetails.equals(that.componentDetails);
    }

    @Override
    public int hashCode() {
        return componentDetails.hashCode();
    }

    private void initFields(ComponentDetails component) {
        componentDetails.setValue(component);
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("component_summary.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void hookupChangeListeners() {
        name.textProperty().bind(componentDetails.getValue().nameProperty());
        status.statusProperty().bind(componentDetails.getValue().statusProperty());
    }
}
