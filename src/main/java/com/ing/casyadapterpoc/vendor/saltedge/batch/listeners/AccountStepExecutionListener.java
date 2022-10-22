package com.ing.casyadapterpoc.vendor.saltedge.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class AccountStepExecutionListener implements StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("BEFORE STEP " + stepExecution.getExitStatus());


    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("AFTER STEP " + stepExecution.getExitStatus());
        return ExitStatus.FAILED;
    }
}
