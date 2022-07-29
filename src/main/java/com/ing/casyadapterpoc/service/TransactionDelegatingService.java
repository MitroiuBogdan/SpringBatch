package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Transaction;
import com.ing.casyadapterpoc.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.service.VendorServiceSelector.selectVendorService;

@AllArgsConstructor
@Service
public class TransactionDelegatingService {
    List<TransactionVendorService> transactionVendorServices;

    public Flux<Transaction> getTransactions(Vendor vendor){
        return selectVendorService(transactionVendorServices,vendor)
                .getTransactions();
    }

    public Mono<Transaction> getTransaction(Vendor vendor, String accountId){
        return selectVendorService(transactionVendorServices,vendor)
                .getTransaction(accountId);
    }

}
