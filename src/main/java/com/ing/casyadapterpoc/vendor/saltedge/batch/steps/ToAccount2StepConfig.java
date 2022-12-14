package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.exceptions.InternalExceptionDictionary;
import com.ing.casyadapterpoc.vendor.saltedge.batch.RefreshJobContext;
import com.ing.casyadapterpoc.vendor.saltedge.batch.listeners.AccountSkipListener;
import com.ing.casyadapterpoc.vendor.saltedge.batch.listeners.AccountStepExecutionListener;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.AccountData;
import com.ing.casyadapterpoc.vendor.saltedge.batch.service.AccountService;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemReaderAdapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ToAccount2StepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final AccountService accountService;
    private final RefreshJobContext refreshJobContext;
    private final AccountStepExecutionListener accountStepExecutionListener;
    private final AccountReaderAdapter accountReaderAdapter;

    public ToAccount2StepConfig(StepBuilderFactory stepBuilderFactory, AccountService accountService, RefreshJobContext refreshJobContext, AccountStepExecutionListener accountStepExecutionListener, AccountReaderAdapter accountReaderAdapter) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.accountService = accountService;
        this.refreshJobContext = refreshJobContext;
        this.accountStepExecutionListener = accountStepExecutionListener;
        this.accountReaderAdapter = accountReaderAdapter;
    }

    @JobScope
    @Bean(name = "toAccount2Step")
    public Step toAccount2Step() {
        return stepBuilderFactory
                .get("toAccount2Step")
                .<Account, AccountData>chunk(3)
                .reader(toAccountReader2())
                .processor(toAccountProcessor2(null))
                .writer(toAccountWriter2())
                .faultTolerant()
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .listener(new AccountSkipListener())
//                .listener(accountStepExecutionListener)
                .build();
    }

    @Bean
    @StepScope
    public ItemReaderAdapter<Account> toAccountReader2() {
//        List<Account> accountList = accountService.getAccounts();
        ItemReaderAdapter readerAdapter = new ItemReaderAdapter();
        readerAdapter.setTargetObject(accountReaderAdapter);
        readerAdapter.setTargetMethod("getNextAccount");

//        List<Account> accountList = Try.of(() -> accountService.getAccounts())
//                .getOrElseGet(throwable -> {
//                    System.out.println("Some error occured");
//                    return new ArrayList<>();
//                });
//        throw new RuntimeException(InternalExceptionDictionary.EXCEPTION.getMessage());
//        return new ListItemReader<>(accountService.getAccounts());

        return readerAdapter;
    }

    @Bean
    @StepScope
    public ItemProcessor<Account, AccountData> toAccountProcessor2(@Value("#{jobParameters['connectionId']}") String connectionId) {
        return account -> {
            System.out.println(account + "ItemProcessor");
            return new AccountData(account.getId());

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


