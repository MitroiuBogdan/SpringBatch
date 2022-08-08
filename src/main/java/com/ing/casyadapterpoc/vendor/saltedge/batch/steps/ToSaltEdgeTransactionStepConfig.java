package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.service.delegate.TransactionDelegatingService;
import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class ToSaltEdgeTransactionStepConfig {

    RefreshJobContext context;
    StepBuilderFactory stepBuilderFactory;
    TransactionDelegatingService transactionDelegatingService;

    @JobScope
    @Bean(name = "toSaltEdgeTransactionStep")
    public Step toSaltEdgeTransactionStep(@Value("#{jobParameters['connectionId']}") String connectionId) {
        return stepBuilderFactory
                .get("toSaltEdgeTransactionStep")
                .tasklet((contribution, chunkContext) -> {
                            log.info("toSaltEdgeTransactionStep - Start fetching transaction for connectionId: {}", connectionId);
                            List<Transaction> transactionList = transactionDelegatingService.getTransactions(Vendor.SALTEDGE, connectionId, null)
                                    .collectList()
                                    .block();
                            transactionList.forEach(transaction -> log.info("Transaction: [{}]", transaction.toString()));
                            return null;
                        }
                ).build();
    }
}
