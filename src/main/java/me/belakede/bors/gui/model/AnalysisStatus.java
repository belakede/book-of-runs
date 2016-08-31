package me.belakede.bors.gui.model;


import me.belakede.bors.persistence.domain.Status;
import org.controlsfx.glyphfont.FontAwesome;

public class AnalysisStatus {

    private final Status status;
    private final String styleClass;
    private final FontAwesome.Glyph glyph;

    AnalysisStatus(Status status, String styleClass, FontAwesome.Glyph glyph) {
        this.status = status;
        this.styleClass = styleClass;
        this.glyph = glyph;
    }

    public Status getStatus() {
        return status;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public FontAwesome.Glyph getGlyph() {
        return glyph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysisStatus that = (AnalysisStatus) o;

        return status == that.status && styleClass.equals(that.styleClass) && glyph == that.glyph;

    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + styleClass.hashCode();
        result = 31 * result + glyph.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return status.toString();
    }
}
