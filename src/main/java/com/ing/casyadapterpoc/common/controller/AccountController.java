package com.ing.casyadapterpoc.common.controller;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.service.AccountDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@AllArgsConstructor
@RestController
@RequestMapping("casypoc")
@Slf4j
public class AccountController {
    private final AccountDelegatingService accountDelegatingService;

    @GetMapping({"{vendor}/{providerGrantId}/accounts"})
    public Flux<Account> getAccounts(@PathVariable Vendor vendor, @PathVariable String providerGrantId) {
        log.info("Getting accounts for vendor: {}, providerGrantId: {}", vendor.name(), providerGrantId);

        return accountDelegatingService.getAccounts(vendor, providerGrantId)
                .doOnNext(acc -> log.info(buildLogMessage(acc)));

    }

    @GetMapping(path = "/{accountId}")
    public Mono<Account> getAccount(Vendor vendor, @PathVariable String accountId) {
        log.info("Getting account with id: " + accountId);
        return accountDelegatingService.getAccount(vendor, accountId)
                .doOnNext(tx -> log.info(buildLogMessage(tx)));
    }

}
