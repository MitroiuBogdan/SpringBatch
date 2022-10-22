package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import com.ing.casyadapterpoc.vendor.saltedge.batch.listeners.AccountStepExecutionListener;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.AccountData;
import com.ing.casyadapterpoc.vendor.saltedge.batch.service.AccountService;
import io.vavr.control.Try;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ToAccount2StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AccountService accountService;
    private final RefreshJobContext refreshJobContext;

    public ToAccount2StepConfig(StepBuilderFactory stepBuilderFactory,
                                AccountService accountService,
                                RefreshJobContext refreshJobContext) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.accountService = accountService;
        this.refreshJobContext = refreshJobContext;
    }


    @JobScope
    @Bean(name = "toAccount2Step")
    public Step toAccount2Step() {
        return stepBuilderFactory
                .get("toAccount2Step")
                .<Account, AccountData>chunk(70)
                .reader(toAccountReader2())
                .processor(toAccountProcessor2())
                .writer(toAccountWriter2())
                .listener(new AccountStepExecutionListener())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Account> toAccountReader2() {
        List<Account> accountList = Try.of(() -> accountService.getAccounts())
                .getOrElseGet(throwable -> {
                    System.out.println("Some error occured");
                    return new ArrayList<>();
                });
        return new ListItemReader<>(accountList);


    }

    @Bean
    @StepScope
    public ItemProcessor<Account, AccountData> toAccountProcessor2() {
        return account ->

        {
            return Try.of(() -> new AccountData(account.getId()))
                    .getOrNull();
        };
    }

    @Bean
    @StepScope
    public ItemWriter<AccountData> toAccountWriter2() {
        return accountData -> {
            System.out.println(refreshJobContext.getJobStatus());
            System.out.println("accountData - " + accountData.stream().map(accountData1 -> accountData1.getId()).collect(Collectors.toList()));
        };
    }
}


