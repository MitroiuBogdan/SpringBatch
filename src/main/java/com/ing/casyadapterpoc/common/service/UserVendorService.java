package com.ing.casyadapterpoc.common.service;

import reactor.core.publisher.Mono;

public interface UserVendorService extends VendorService{

        Mono<String> createUser();
        Mono<Void> deleteUser(String userId);
}
