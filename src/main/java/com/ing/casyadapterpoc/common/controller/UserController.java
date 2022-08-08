package com.ing.casyadapterpoc.common.controller;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import com.ing.casyadapterpoc.common.service.UserDelegatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@AllArgsConstructor
@RestController
@RequestMapping()
@Slf4j
public class UserController {

    UserDelegatingService userDelegatingService;

    @PostMapping({"{vendor}/users/create"})
    public Mono<User> createUser(@PathVariable Vendor vendor, @RequestParam String identifier) {
        log.info("Create user for vendor: {}, identifier: {}", vendor.name(), identifier);
        return userDelegatingService.createUser(vendor, identifier)
                .doOnNext(user -> log.info(buildLogMessage(user)));
    }
}
