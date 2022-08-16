package com.ing.casyadapterpoc.common.controller;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import com.ing.casyadapterpoc.common.service.delegate.UserDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping()
public class UserController {

    UserDelegatingService userDelegatingService;

    @PostMapping({"{vendor}/users/{identifier}"})
    public Mono<User> createUser(@PathVariable Vendor vendor,
                                 @PathVariable String identifier) {
        log.info("Create user for vendor: {}, identifier: {}", vendor.name(), identifier);
        return userDelegatingService.createUser(vendor, identifier)
                .doOnNext(user -> log.info(buildLogMessage(user)));
    }

    @DeleteMapping({"{vendor}/users/{userId}"})
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable Vendor vendor,
                                                 @PathVariable String userId) {
        log.info("Delete user for vendor: {}, userId: {}", vendor.name(), userId);
        return userDelegatingService.deleteUser(vendor, userId)
                .doOnNext(user -> log.info(buildLogMessage(user)));
    }

    @GetMapping({"{vendor}/users"})
    public Flux<User> getUsers(@PathVariable Vendor vendor) {
        log.info("Get all users for vendor: {}", vendor.name());
        return userDelegatingService.getUsers(vendor)
                .doOnNext(user -> log.info(buildLogMessage(user)));
    }

    @GetMapping({"{vendor}/users/{userId}"})
    public Mono<User> getUserById(@PathVariable Vendor vendor,
                                  @PathVariable String userId) {
        log.info("Get  users for vendor: {}, userId: {}", vendor.name(), userId);
        return userDelegatingService.getUserById(vendor, userId)
                .doOnNext(user -> log.info(buildLogMessage(user)));
    }

}
