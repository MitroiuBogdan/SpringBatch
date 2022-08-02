package com.ing.casyadapterpoc.vendor.saltedge.rest;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.service.AccountDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@AllArgsConstructor
@RestController
@RequestMapping("accounts")
@Log
public class AccountResource {
    private final AccountDelegatingService accountDelegatingService;

    @GetMapping
    public Flux<Account> getAccounts(Vendor vendor) {
        log.info("Getting accounts:");

        return accountDelegatingService.getAccounts(Vendor.SALTEDGE)
                .doOnNext(acc -> log.info(buildLogMessage(acc)));

    }

    @GetMapping(path = "/{accountId}")
    public Mono<Account> getAccount(Vendor vendor, @PathVariable String accountId) {
        log.info("Getting account with id: "+ accountId);
        return accountDelegatingService.getAccount(vendor,accountId)
                .doOnNext(tx -> log.info(buildLogMessage(tx)));
    }

}
