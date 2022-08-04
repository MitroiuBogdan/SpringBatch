package com.ing.casyadapterpoc.common.controller;

import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.service.TransactionDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@AllArgsConstructor
@RestController
@RequestMapping("casypoc")
@Slf4j
public class TransactionController {
    private final TransactionDelegatingService transactionDelegatingService;

    @GetMapping({"{vendor}/{providerGrantId}/transactions"})
    public Flux<Transaction> getTransactions(@PathVariable Vendor vendor,
                                             @PathVariable String providerGrantId,
                                             @RequestParam(required = false) String providerAccountId) {
        log.info("Getting transactions for vendor: {}, providerGrantId: {}, providerAccountId: {}", vendor.name(), providerGrantId, providerAccountId);

        return transactionDelegatingService.getTransactions(vendor, providerGrantId, providerAccountId)
                .doOnNext(tx -> log.info(buildLogMessage(tx)));

    }

//    @GetMapping(path = "/{accountId}")
//    public Mono<Transaction> getTransaction(Vendor vendor, @PathVariable String accountId) {
//        log.info("Getting transaction with id: " + accountId);
//        return transactionDelegatingService.getTransaction(vendor, accountId)
//                .doOnNext(tx -> log.info(buildLogMessage(tx)));
//    }

}
