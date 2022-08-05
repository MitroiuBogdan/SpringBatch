package com.ing.casyadapterpoc.vendor.saltedge.batch.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RefreshJobConfig {

    private final JobBuilderFactory jobFactory;

    @Bean(name = "refreshSaltEdgeDataJob")
    public Job refreshSaltEdgeDataJob(Step toSaltEdgeAccountStep,
                                      Step toSaltEdgeTransactionStep) {

        return jobFactory
                .get("refreshSaltEdgeDataJob")
                .incrementer(new RunIdIncrementer())
                .start(toSaltEdgeAccountStep)
                .next(toSaltEdgeTransactionStep)
                .build();

    }
}
