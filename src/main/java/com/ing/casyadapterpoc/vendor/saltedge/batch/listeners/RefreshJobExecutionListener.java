package com.ing.casyadapterpoc.vendor.saltedge.batch.listeners;

import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefreshJobExecutionListener implements JobExecutionListener {

    @Autowired
    RefreshJobContext refreshJobContext;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("BEFORE JOB");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("AFTER JOB" + refreshJobContext.getJobStatus());
        jobExecution.getStepExecutions().stream().forEach(stepExecution -> {
            System.out.println(stepExecution.getStepName() + " " + stepExecution.getExitStatus());
        });
    }
}
