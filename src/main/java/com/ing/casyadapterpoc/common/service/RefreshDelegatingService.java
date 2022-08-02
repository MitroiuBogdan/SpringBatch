package com.ing.casyadapterpoc.common.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RefreshDelegatingService {

    public Mono<Void> refresh(){
        return Mono.empty();
    }
}
