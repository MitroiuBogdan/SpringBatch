package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.service.delegate.AccountDelegatingService;
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
public class ToSaltEdgeAccountStepConfig {

    StepBuilderFactory stepBuilderFactory;
    AccountDelegatingService accountDelegatingService;

    @JobScope
    @Bean(name = "toSaltEdgeAccountStep")
    public Step toSaltEdgeAccountStep(@Value("#{jobParameters['connectionId']}") String connectionId) {
        return stepBuilderFactory
                .get("toSaltEdgeAccountStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("toSaltEdgeAccountStep - Start fetching accounts for connectionId: {}", connectionId);
                            List<Account> accountList = accountDelegatingService.getAccounts(Vendor.SALTEDGE, connectionId)
                                    .collectList()
                                    .block();
                            accountList.forEach(account -> log.info("Account:[{}]", account.toString()));
                            return null;
                        }
                ).build();
    }
}
