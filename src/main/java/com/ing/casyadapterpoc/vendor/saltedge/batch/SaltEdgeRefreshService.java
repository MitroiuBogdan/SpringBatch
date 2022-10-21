package com.ing.casyadapterpoc.vendor.saltedge.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;


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

        System.out.println("startDataFetching - jobParameters:" + jobParametersBuilder.toJobParameters());
        try {
            jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
