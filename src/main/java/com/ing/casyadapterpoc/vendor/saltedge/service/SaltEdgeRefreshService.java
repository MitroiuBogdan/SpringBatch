package com.ing.casyadapterpoc.vendor.saltedge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SaltEdgeRefreshService {

    private final JobLauncher jobLauncher;
    private final Job job;

    public SaltEdgeRefreshService(@Qualifier("jobLauncherPoc") JobLauncher jobLauncher,
                                  @Qualifier("refreshSaltEdgeDataJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void startDataFetching(String connectionId) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("start", new Date());
        jobParametersBuilder.addString("connectionId", connectionId);

        log.info("startDataFetching - jobParameters: {}", jobParametersBuilder.toJobParameters());
        try {
            jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            log.error(e.getCause().getMessage());
        }
    }
}
