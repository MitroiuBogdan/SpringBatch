package com.ing.casyadapterpoc.controller;

import com.ing.casyadapterpoc.domain.Account;
import com.ing.casyadapterpoc.domain.Transaction;
import com.ing.casyadapterpoc.domain.Vendor;
import com.ing.casyadapterpoc.service.AccountDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.logging.LoggingHelper.buildLogMessage;

@AllArgsConstructor
@RestController
@RequestMapping("accounts")
@Log
public class AccountController {
    private final AccountDelegatingService accountDelegatingService;

    @GetMapping
    public Flux<Account> getAccounts(Vendor vendor) {
        log.info("Getting accounts:");

        return accountDelegatingService.getAccounts(vendor)
                .doOnNext(acc -> log.info(buildLogMessage(acc)));

    }

    @GetMapping(path = "/{accountId}")
    public Mono<Account> getAccount(Vendor vendor, @PathVariable String accountId) {
        log.info("Getting account with id: "+ accountId);
        return accountDelegatingService.getAccount(vendor,accountId)
                .doOnNext(tx -> log.info(buildLogMessage(tx)));
    }

}
