package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountVendorService extends VendorService{

    Flux<Account> getAccounts(String connectionId);

    Mono<Account> getAccount(String accountId);
}
