package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.service.AccountVendorService;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeAccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SaltedgeAccountService implements AccountVendorService {
    private final SaltEdgeClientImpl saltEdgeClient;

    private static final Vendor VENDOR = Vendor.SALTEDGE;

    @Override
    public boolean isForVendor(Vendor vendor) {
        return VENDOR.equals(vendor);
    }

    public Flux<Account> getAccounts(){
        return saltEdgeClient.getAccounts()
                .map(SaltedgeAccountMapper.SALTEDGE_ACCOUNT_MAPPER);
    }

    @Override
    public Mono<Account> getAccount(String accountId) {
        return Mono.empty();
    }


}
