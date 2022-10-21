package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class AccountStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String x = (String) stepExecution.getExecutionContext().get("stepStatus");
        return ExitStatus.FAILED;
    }
}
