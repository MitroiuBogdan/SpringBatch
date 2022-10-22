package com.ing.casyadapterpoc.vendor.saltedge.batch.config;

import com.ing.casyadapterpoc.vendor.saltedge.batch.listeners.RefreshJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshJobConfig {

    private final JobBuilderFactory jobFactory;
    private final RefreshJobExecutionListener refreshJobExecutionListener;

    public RefreshJobConfig(JobBuilderFactory jobFactory, RefreshJobExecutionListener refreshJobExecutionListener) {
        this.jobFactory = jobFactory;
        this.refreshJobExecutionListener = refreshJobExecutionListener;
    }


    @Bean(name = "refreshSaltEdgeDataJob")
    public Job refreshSaltEdgeDataJob(Step toSaltEdgeAccountStep,
                                      Step  toAccount2Step) {

        return jobFactory
                .get("refreshSaltEdgeDataJob")
                .listener(refreshJobExecutionListener)
                .incrementer(new RunIdIncrementer())
//                .start(toSaltEdgeAccountStep)
                .start(toAccount2Step)
                .build();

    }

}
