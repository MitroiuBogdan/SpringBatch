package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserVendorService extends VendorService {

    Mono<User> createUser(String identifier);

    Mono<ResponseEntity<Void>> deleteUser(String userId);

    Flux<User> getUsers();

    Mono<User> getUserById(String userId);
}
