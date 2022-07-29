package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountVendorService extends VendorService{

    Flux<Account> getAccounts();

    Mono<Account> getAccount(String accountId);
}
