package me.belakede.bors.gui.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import me.belakede.bors.persistence.model.Environment;
import me.belakede.bors.persistence.model.KerberosServerType;

import java.time.LocalDateTime;
import java.util.HashSet;

public class RunDetails {

    private final LongProperty runId = new SimpleLongProperty(0);
    private final StringProperty operationSystem = new SimpleStringProperty("");
    private final ObjectProperty<Environment> environment = new SimpleObjectProperty<>(Environment.UNKNOWN);
    private final ObjectProperty<KerberosServerType> kerberosServerType = new SimpleObjectProperty<>(KerberosServerType.NONE);
    private final BooleanProperty secure = new SimpleBooleanProperty(false);
    private final SetProperty<ComponentDetails> componentsDetails = new SimpleSetProperty<>(FXCollections.observableSet(new HashSet<ComponentDetails>(10)));
    private final ObjectProperty<LocalDateTime> started = new SimpleObjectProperty<>(LocalDateTime.now());
    private final StringProperty releaseName = new SimpleStringProperty("");
    private final IntegerProperty buildNumber = new SimpleIntegerProperty(0);

    public RunDetails() {
        secure.bind(Bindings.notEqual(KerberosServerType.NONE, kerberosServerType));
        // TODO calculate status
    }

    public long getRunId() {
        return runId.get();
    }

    public void setRunId(long runId) {
        this.runId.set(runId);
    }

    public LongProperty runIdProperty() {
        return runId;
    }

    public String getOperationSystem() {
        return operationSystem.get();
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem.set(operationSystem);
    }

    public StringProperty operationSystemProperty() {
        return operationSystem;
    }

    public Environment getEnvironment() {
        return environment.get();
    }

    public void setEnvironment(Environment environment) {
        this.environment.set(environment);
    }

    public ObjectProperty<Environment> environmentProperty() {
        return environment;
    }

    public KerberosServerType getKerberosServerType() {
        return kerberosServerType.get();
    }

    public void setKerberosServerType(KerberosServerType kerberosServerType) {
        this.kerberosServerType.set(kerberosServerType);
    }

    public ObjectProperty<KerberosServerType> kerberosServerTypeProperty() {
        return kerberosServerType;
    }

    public boolean isSecure() {
        return secure.get();
    }

    public BooleanProperty secureProperty() {
        return secure;
    }

    public ObservableSet<ComponentDetails> getComponentsDetails() {
        return componentsDetails.get();
    }

    public SetProperty<ComponentDetails> componentsDetailsProperty() {
        return componentsDetails;
    }

    public void setComponentsDetails(ObservableSet<ComponentDetails> componentsDetails) {
        this.componentsDetails.set(componentsDetails);
    }

    public void addComponentDetails(ComponentDetails componentDetails) {
        componentsDetails.add(componentDetails);
    }

    public LocalDateTime getStarted() {
        return started.get();
    }

    public void setStarted(LocalDateTime started) {
        this.started.set(started);
    }

    public ObjectProperty<LocalDateTime> startedProperty() {
        return started;
    }

    public String getReleaseName() {
        return releaseName.get();
    }

    public void setReleaseName(String releaseName) {
        this.releaseName.set(releaseName);
    }

    public StringProperty releaseNameProperty() {
        return releaseName;
    }

    public int getBuildNumber() {
        return buildNumber.get();
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber.set(buildNumber);
    }

    public IntegerProperty buildNumberProperty() {
        return buildNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunDetails that = (RunDetails) o;

        return runId.equals(that.runId) && started.equals(that.started) && releaseName.equals(that.releaseName)
                && buildNumber.equals(that.buildNumber);
    }

    @Override
    public int hashCode() {
        int result = runId.hashCode();
        result = 31 * result + started.hashCode();
        result = 31 * result + releaseName.hashCode();
        result = 31 * result + buildNumber.hashCode();
        return result;
    }
}
