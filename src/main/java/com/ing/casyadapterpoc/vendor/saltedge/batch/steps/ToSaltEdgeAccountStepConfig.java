package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class ToSaltEdgeAccountStepConfig {

    RefreshJobContext context;
    StepBuilderFactory stepBuilderFactory;

    @JobScope
    @Bean(name = "toSaltEdgeAccountStep")
    public Step toSaltEdgeAccountStep(@Value("#{jobParameters['connectionId']}") String connectionId) {
        context.setConnectionId(connectionId);
        return stepBuilderFactory
                .get("toSaltEdgeAccountStep")
                .tasklet((contribution, chunkContext) -> {

                            log.info("FETCH OF ACCOUNTS HAVE STARTED");
                            //TODO: Take accounts from SaltEdge based on connection_id
                            //TODO: Add account ids in RefreshJobContext
                            //TODO: Map SaltEdgeAccount to Account
                            //TODO: Write Account to file
                            return null;
                        }
                ).build();
    }
}
