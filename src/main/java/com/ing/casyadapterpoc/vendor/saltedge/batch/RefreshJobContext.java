package com.ing.casyadapterpoc.vendor.saltedge.batch;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@JobScope
public class RefreshJobContext {

    private String jobStatus;


    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("Destroyed");
    }
}
