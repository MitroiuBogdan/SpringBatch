package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.domain.Account;
import com.ing.casyadapterpoc.domain.Transaction;
import com.ing.casyadapterpoc.domain.Vendor;
import com.ing.casyadapterpoc.service.AccountVendorService;
import com.ing.casyadapterpoc.vendor.saltedge.client.SaltEdgeClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeAccountMapper.SALTEDGE_ACCOUNT_MAPPER;

@AllArgsConstructor
@Service
public class SaltedgeAccountService implements AccountVendorService {
    private final SaltEdgeClient saltEdgeClient;

    private static final Vendor VENDOR = Vendor.SALTEDGE;

    @Override
    public boolean isForVendor(Vendor vendor) {
        return VENDOR.equals(vendor);
    }

    public Flux<Account> getAccounts(){
        return saltEdgeClient.getAccounts()
                .map(SALTEDGE_ACCOUNT_MAPPER);
    }

    @Override
    public Mono<Account> getAccount(String accountId) {
        return Mono.empty();
    }


}
