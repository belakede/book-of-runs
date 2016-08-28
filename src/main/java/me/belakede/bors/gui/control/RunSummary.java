package me.belakede.bors.gui.control;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import me.belakede.bors.gui.model.ComponentDetails;
import me.belakede.bors.gui.model.RunDetails;

import java.io.IOException;

public class RunSummary extends BorderPane {

    private final ObjectProperty<RunDetails> runDetails = new SimpleObjectProperty<>();
    private final ObservableMap<ComponentDetails, ComponentSummary> components = FXCollections.observableHashMap();

    @FXML
    private Label runId;
    @FXML
    private Label environment;
    @FXML
    private Label operationSystem;
    @FXML
    private Label secure;
    @FXML
    private Label kerberosServerType;
    @FXML
    private Label startTime;
    @FXML
    private Label releaseName;
    @FXML
    private Label buildNumber;
    @FXML
    private VBox componentHolder;

    public RunSummary(RunDetails details) {
        runDetails.setValue(details);
        loadFxml();
        hookupChangeListeners();
        bindLabels(details);
        initFields(details);
        // TODO calculate style class from components status
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunSummary that = (RunSummary) o;

        return runDetails.equals(that.runDetails);
    }

    @Override
    public int hashCode() {
        return runDetails.hashCode();
    }

    private void initFields(RunDetails details) {
        details.getComponentsDetails().forEach(cd -> components.put(cd, new ComponentSummary(cd)));
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("run_summary.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void bindLabels(RunDetails details) {
        runId.textProperty().bind(details.runIdProperty().asString("#%d"));
        environment.textProperty().bind(details.environmentProperty().asString());
        operationSystem.textProperty().bind(details.operationSystemProperty());
        secure.textProperty().bind(Bindings.when(details.secureProperty()).then("Secure").otherwise("Unsecure"));
        kerberosServerType.textProperty().bind(details.kerberosServerTypeProperty().asString());
        startTime.textProperty().bind(details.startedProperty().asString());
        releaseName.textProperty().bind(details.releaseNameProperty());
        buildNumber.textProperty().bind(details.buildNumberProperty().asString());
    }

    private void hookupChangeListeners() {
        setComponentsListener();
        setRunDetailsListener();
    }

    private void setRunDetailsListener() {
        runDetails.getValue().getComponentsDetails().addListener(new SetChangeListener<ComponentDetails>() {
            @Override
            public void onChanged(Change<? extends ComponentDetails> change) {
                if (change.wasAdded()) {
                    components.put(change.getElementAdded(), new ComponentSummary(change.getElementAdded()));
                } else {
                    components.remove(change.getElementRemoved());
                }
            }
        });
    }

    private void setComponentsListener() {
        components.addListener(new MapChangeListener<ComponentDetails, ComponentSummary>() {
            @Override
            public void onChanged(Change<? extends ComponentDetails, ? extends ComponentSummary> change) {
                if (change.wasAdded()) {
                    componentHolder.getChildren().add(change.getValueAdded());
                } else {
                    componentHolder.getChildren().remove(change.getValueRemoved());
                }
            }
        });
    }

}
