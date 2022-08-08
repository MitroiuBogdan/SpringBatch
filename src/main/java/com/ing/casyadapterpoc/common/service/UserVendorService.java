package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import reactor.core.publisher.Mono;

public interface UserVendorService extends VendorService{

        Mono<User> createUser(String identifier);
        Mono<Void> deleteUser(String userId);
}
