package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Account;
import com.ing.casyadapterpoc.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.service.VendorServiceSelector.selectVendorService;

@AllArgsConstructor
@Service
public class AccountDelegatingService {
    private final List<AccountVendorService> accountVendorServices;

    public Flux<Account> getAccounts(Vendor vendor){
        // ++attempts
        return selectVendorService(accountVendorServices,vendor)
                .getAccounts();
    }

    public Mono<Account> getAccount(Vendor vendor, String accountId){
        return selectVendorService(accountVendorServices,vendor)
                .getAccount(accountId);

    }

}
