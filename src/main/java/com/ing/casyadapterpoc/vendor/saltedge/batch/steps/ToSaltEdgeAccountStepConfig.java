package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.AccountData;
import com.ing.casyadapterpoc.vendor.saltedge.batch.service.AccountService;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ToSaltEdgeAccountStepConfig extends ItemListenerSupport {

    private final StepBuilderFactory stepBuilderFactory;
    private final AccountService accountService;


    public ToSaltEdgeAccountStepConfig(StepBuilderFactory stepBuilderFactory, AccountService accountService) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.accountService = accountService;

    }

    @JobScope
    @Bean(name = "toSaltEdgeAccountStep")
    public Step toSaltEdgeAccountStep() {
        return stepBuilderFactory
                .get("toSaltEdgeAccountStep")
                .<Account, AccountData>chunk(70)
                .reader(toAccountReader())
                .processor(toAccountProcessor())
                .writer(toAccountWriter())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Account> toAccountReader() {
        List<Account> accountList = Try.of(() -> accountService.getAccounts())
                .getOrElseGet(throwable -> {
                    System.out.println("Some error occured");
                    return new ArrayList<>();
                });

        return new ListItemReader<>(accountList);


    }

    @Bean
    @StepScope
    public ItemProcessor<Account, AccountData> toAccountProcessor() {
        return account -> Try.of(() -> new AccountData(account.getId()))
                .getOrNull();
    }

    @Bean
    @StepScope
    public ItemWriter<AccountData> toAccountWriter() {
        return accountData -> System.out.println("accountData - " + accountData.stream().map(accountData1 -> accountData1.getId()).collect(Collectors.toList()));
    }


}
