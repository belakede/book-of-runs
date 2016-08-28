package me.belakede.bors.gui.task;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import me.belakede.bors.gui.model.AnalysisStatusFactory;
import me.belakede.bors.gui.model.ComponentDetails;
import me.belakede.bors.gui.model.RunDetails;

import java.util.HashSet;

public class ComponentDetailsCollectorTask extends Task<SetProperty<ComponentDetails>> {

    private final RunDetails details;

    public ComponentDetailsCollectorTask(RunDetails details) {
        this.details = details;
    }

    @Override
    protected SetProperty<ComponentDetails> call() throws Exception {
        SetProperty<ComponentDetails> result = new SimpleSetProperty<>(FXCollections.observableSet(new HashSet<ComponentDetails>(10)));
        ComponentDetails[] components = new ComponentDetails[]{
                new ComponentDetails("atlas", AnalysisStatusFactory.getDoneStatus()),
                new ComponentDetails("admin", AnalysisStatusFactory.getRunningStatus()),
                new ComponentDetails("falcon", AnalysisStatusFactory.getSomethingWentWrongStatus()),
                new ComponentDetails("knox", AnalysisStatusFactory.getWorkInProgressStatus()),
                new ComponentDetails("knox-ha", AnalysisStatusFactory.getNeedToRestartStatus()),
                new ComponentDetails("oozie", AnalysisStatusFactory.getDoneStatus()),
                new ComponentDetails("oozie-ha", AnalysisStatusFactory.getNeedToRestartStatus()),
                new ComponentDetails("xaagents", AnalysisStatusFactory.getAnalysisPendingStatus()),
                new ComponentDetails("xaagents-ha", AnalysisStatusFactory.getDoneStatus()),
                new ComponentDetails("zookeeper", AnalysisStatusFactory.getDoneStatus())};
        for (int i = 0; i < components.length; i++) {
            Thread.sleep(300);
            ComponentDetails component = components[i];
            result.add(component);
            updateProgress(i, components.length);
            updateMessage(component.getName() + ": " + component.getStatus());
        }
        updateProgress(0, 0);
        updateMessage("");
        done();
        return result;
    }

}
