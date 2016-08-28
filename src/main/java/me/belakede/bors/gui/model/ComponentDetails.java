package me.belakede.bors.gui.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ComponentDetails {

    private final ObjectProperty<AnalysisStatus> status = new SimpleObjectProperty<>();
    private final StringProperty name = new SimpleStringProperty();

    public ComponentDetails() {
        setName("");
        setStatus(AnalysisStatusFactory.getDefaultStatus());
    }

    public ComponentDetails(String name, AnalysisStatus status) {
        setName(name);
        setStatus(status);
    }

    public AnalysisStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<AnalysisStatus> statusProperty() {
        return status;
    }

    public void setStatus(AnalysisStatus status) {
        this.status.set(status);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentDetails that = (ComponentDetails) o;

        return status.equals(that.status) && name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
