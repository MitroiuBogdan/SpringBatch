package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class ToSaltEdgeTransactionStepConfig {

    RefreshJobContext context;
    StepBuilderFactory stepBuilderFactory;

    @JobScope
    @Bean(name = "toSaltEdgeTransactionStep")
    public Step toSaltEdgeTransactionStep() {
        return stepBuilderFactory
                .get("toSaltEdgeTransactionStep")
                .tasklet((contribution, chunkContext) -> {

                            log.info("FETCH OF TRANSACTIONS HAVE STARTED");
                            List<String> accountIds = context.getAccountIdList();
                            accountIds.stream().forEach(accountId -> {
                                //TODO: Take transaction from SaltEdge based on accounts id
                                //TODO: Map to saltEdgeTrx to trx
                                //TODO: Write trx to file
                            });
                            return null;
                        }
                ).build();
    }
}
