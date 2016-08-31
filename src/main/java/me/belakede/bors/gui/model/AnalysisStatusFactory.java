package me.belakede.bors.gui.model;

import me.belakede.bors.persistence.domain.Status;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.HashMap;
import java.util.Map;

public class AnalysisStatusFactory {

    private static final Map<Status, AnalysisStatus> CACHE;
    static {
        CACHE = new HashMap<>(7);
        CACHE.put(Status.DEFAULT, new AnalysisStatus(Status.DEFAULT, "", FontAwesome.Glyph.CHECK_SQUARE));
        CACHE.put(Status.SOMETHING_WENT_WRONG, new AnalysisStatus(Status.SOMETHING_WENT_WRONG, "something-went-wrong", FontAwesome.Glyph.MEH_ALT));
        CACHE.put(Status.NEED_TO_RESTART, new AnalysisStatus(Status.NEED_TO_RESTART, "need-to-restart", FontAwesome.Glyph.REFRESH));
        CACHE.put(Status.RUNNING, new AnalysisStatus(Status.NEED_TO_RESTART, "running", FontAwesome.Glyph.SPINNER));
        CACHE.put(Status.ANALYSIS_PENDING, new AnalysisStatus(Status.ANALYSIS_PENDING, "analysis-pending", FontAwesome.Glyph.TIMES));
        CACHE.put(Status.WORK_IN_PROGRESS, new AnalysisStatus(Status.WORK_IN_PROGRESS, "work-in-progress", FontAwesome.Glyph.CIRCLE_ALT_NOTCH));
        CACHE.put(Status.DONE, new AnalysisStatus(Status.DONE, "done", FontAwesome.Glyph.CHECK));
    }

    public static AnalysisStatus getDefaultStatus() {
        return CACHE.get(Status.DEFAULT);
    }

    public static AnalysisStatus getSomethingWentWrongStatus() {
        return CACHE.get(Status.SOMETHING_WENT_WRONG);
    }

    public static AnalysisStatus getNeedToRestartStatus() {
        return CACHE.get(Status.NEED_TO_RESTART);
    }

    public static AnalysisStatus getRunningStatus() {
        return CACHE.get(Status.RUNNING);
    }

    public static AnalysisStatus getAnalysisPendingStatus() {
        return CACHE.get(Status.ANALYSIS_PENDING);
    }

    public static AnalysisStatus getWorkInProgressStatus() {
        return CACHE.get(Status.WORK_IN_PROGRESS);
    }

    public static AnalysisStatus getDoneStatus() {
        return CACHE.get(Status.DONE);
    }

}
