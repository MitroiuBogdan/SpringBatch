package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionVendorService extends VendorService{


    Flux<Transaction> getTransactions();
    Mono<Transaction> getTransaction(String transactionId);
}
