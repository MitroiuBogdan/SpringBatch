package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;


import com.ing.casyadapterpoc.vendor.saltedge.mapper.SessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.SessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/SALTEDGE/session")
public class ConnectSessionController {

    private final SaltEdgeClientImpl saltEdgeClient;
    private final SessionToSaltEdgeSessionMapper toCreateSaltEdgeSession;

    @PostMapping("/create")
    public Mono<SessionData> createSession(@RequestBody SessionRequest request) {
        log.info("createSession - Creating session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toCreateSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.createSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);
    }

    @PutMapping("/reconnect")
    public Mono<SessionData> reconnectSession(@RequestBody SessionRequest request) {
        log.info("reconnectSession - Reconnecting session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toCreateSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.reconnectSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);

    }

    @PutMapping("/refresh")
    public Mono<SessionData> refreshSession(@RequestBody SessionRequest request) {
        log.info("refreshSession - Refreshing session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toCreateSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.refreshSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);
    }

}
