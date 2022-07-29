package com.ing.casyadapterpoc.service;

import reactor.core.publisher.Mono;

public interface UserVendorService extends VendorService{

        Mono<String> createUser();
        Mono<Void> deleteUser(String userId);
}
