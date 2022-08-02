package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Transaction;
import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.service.TransactionVendorService;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.domain.Vendor.SALTEDGE;
import static com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeTransactionMapper.SALTEDGE_TX_MAPPER;
@AllArgsConstructor
@Service
public class  SaltedgeTransactionService implements TransactionVendorService {

    private final SaltEdgeClientImpl saltEdgeClient;

    @Override
    public Flux<Transaction> getTransactions() {

        return saltEdgeClient.getTransactions()
                .map(SALTEDGE_TX_MAPPER);
    }

    @Override
    public Mono<Transaction> getTransaction(String transactionId) {
        return Mono.empty();
    }

    @Override
    public boolean isForVendor(Vendor vendor) {
        return SALTEDGE.equals(vendor);
    }
}
