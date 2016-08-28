package me.belakede.bors.gui.task;

import javafx.concurrent.Task;
import me.belakede.bors.gui.model.*;
import me.belakede.bors.persistence.model.Environment;
import me.belakede.bors.persistence.model.KerberosServerType;

public class RunDetailsCollectorTask extends Task<RunDetails> {

    private final Long runId;

    public RunDetailsCollectorTask(Long runId) {
        this.runId = runId;
    }

    @Override
    protected RunDetails call() throws Exception {
        RunDetails details = new RunDetails();
        updateMessage("Waiting for server...");
        Thread.sleep(400);
        details.setRunId(runId);
        updateMessage("Downloading info for run: " + runId);
        updateProgress(1, 3);
        details.setEnvironment(Environment.YCLOUD);
        details.setOperationSystem("CentOS7");
        Thread.sleep(200);
        updateProgress(2, 3);
        details.setKerberosServerType(KerberosServerType.MIT);
        details.setReleaseName("Derg-M20");
        details.setBuildNumber(42);
        updateProgress(0, 0);
        updateMessage("");
        done();
        return details;
    }


}
