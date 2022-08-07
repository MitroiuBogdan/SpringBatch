package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;

import com.ing.casyadapterpoc.common.domain.casy_entity.Grant;
import com.ing.casyadapterpoc.vendor.saltedge.service.SaltedgeConnectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/saltedge/connections")
@AllArgsConstructor
public class ConnectionController {

    private final SaltedgeConnectionService saltedgeConnectionService;

    @PutMapping("/{connectionId}/refresh")
    public Mono<Grant> refreshConnection(@PathVariable String connectionId) {
        log.info("refreshConnection - refreshing grant with id {}", connectionId);
        return saltedgeConnectionService.refreshById(connectionId);
    }
}
