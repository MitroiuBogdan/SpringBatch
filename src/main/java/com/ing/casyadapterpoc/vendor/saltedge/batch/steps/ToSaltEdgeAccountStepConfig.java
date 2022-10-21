package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.AccountData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class ToSaltEdgeAccountStepConfig {

    StepBuilderFactory stepBuilderFactory;

    public ToSaltEdgeAccountStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @JobScope
    @Bean(name = "toSaltEdgeAccountStep")
    public Step toSaltEdgeAccountStep() {
        return stepBuilderFactory
                .get("toSaltEdgeAccountStep")
                .<Account, AccountData>chunk(10)
                .reader(toAccountReader())
                .processor(toAccountProcessor())
                .writer(toAccountWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Account> toAccountReader() {
        return new ListItemReader<>(List.of(new Account("123"), new Account("321")));
    }

    @Bean
    @StepScope
    public ItemProcessor<Account, AccountData> toAccountProcessor() {
        return account -> new AccountData(account.getId());
    }

    @Bean
    @StepScope
    public ItemWriter<AccountData> toAccountWriter() {
        return accountData -> System.out.println("accountData - " + accountData);
    }
}
