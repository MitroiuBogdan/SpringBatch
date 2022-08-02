package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionVendorService extends VendorService{


    Flux<Transaction> getTransactions();
    Mono<Transaction> getTransaction(String transactionId);
}
