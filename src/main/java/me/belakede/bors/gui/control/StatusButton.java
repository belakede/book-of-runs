package me.belakede.bors.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import me.belakede.bors.gui.model.AnalysisStatus;
import me.belakede.bors.gui.model.AnalysisStatusFactory;
import org.controlsfx.glyphfont.Glyph;

import java.io.IOException;

public class StatusButton extends Button {

    private final ObjectProperty<AnalysisStatus> status = new SimpleObjectProperty<>();
    @FXML
    private Glyph glyph;

    public StatusButton() {
        loadFxml();
        hookupChangeListeners();
        initFields();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusButton that = (StatusButton) o;

        return status.equals(that.status);

    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

    private void initFields() {
        status.setValue(AnalysisStatusFactory.getDefaultStatus());
    }

    private void loadFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("status_button.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void hookupChangeListeners() {
        status.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                glyph.getStyleClass().remove(oldValue.getStyleClass());
            }
            glyph.getStyleClass().add(newValue.getStyleClass());
            glyph.setIcon(newValue.getGlyph());
        });
    }
}
