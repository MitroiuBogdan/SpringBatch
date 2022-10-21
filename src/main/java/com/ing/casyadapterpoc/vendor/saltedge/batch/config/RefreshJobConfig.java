package com.ing.casyadapterpoc.vendor.saltedge.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshJobConfig {

    private final JobBuilderFactory jobFactory;

    public RefreshJobConfig(JobBuilderFactory jobFactory) {
        this.jobFactory = jobFactory;
    }

    @Bean(name = "refreshSaltEdgeDataJob")
    public Job refreshSaltEdgeDataJob(Step toSaltEdgeAccountStep) {

        return jobFactory
                .get("refreshSaltEdgeDataJob")
                .incrementer(new RunIdIncrementer())
                .start(toSaltEdgeAccountStep)
                .build();

    }

}
