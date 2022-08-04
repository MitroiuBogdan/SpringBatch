package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountDelegatingService {
    private final List<AccountVendorService> accountVendorServices;

    public Flux<Account> getAccounts(Vendor vendor, String providerGrantId) {
        // ++attempts
        return VendorServiceSelector.selectVendorService(accountVendorServices, vendor)
                .getAccounts(providerGrantId);
    }

    public Mono<Account> getAccount(Vendor vendor, String accountId) {
        return VendorServiceSelector.selectVendorService(accountVendorServices, vendor)
                .getAccount(accountId);

    }

}
