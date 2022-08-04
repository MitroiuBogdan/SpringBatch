package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionDelegatingService {
    List<TransactionVendorService> transactionVendorServices;

    public Flux<Transaction> getTransactions(Vendor vendor, String connectionId) {
        return VendorServiceSelector.selectVendorService(transactionVendorServices, vendor)
                .getTransactions(connectionId);
    }

    public Mono<Transaction> getTransaction(Vendor vendor, String accountId) {
        return VendorServiceSelector.selectVendorService(transactionVendorServices, vendor)
                .getTransaction(accountId);
    }

}
