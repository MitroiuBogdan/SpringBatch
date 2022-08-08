package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;


import com.ing.casyadapterpoc.vendor.saltedge.mapper.CreateSessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.ReconnectSessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.RefreshSessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.*;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/saltedge/connect-session")
public class ConnectSessionController {

    private final SaltEdgeClientImpl saltEdgeClient;
    private final CreateSessionToSaltEdgeSessionMapper toCreateSaltEdgeSession;
    private final ReconnectSessionToSaltEdgeSessionMapper toReconnectSaltEdgeSession;
    private final RefreshSessionToSaltEdgeSessionMapper toRefreshSaltEdgeSession;

    @PostMapping("/create")
    public Mono<SessionData> createSession(@RequestBody CreateSessionRequest request) {
        log.info("createSession - Creating session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toCreateSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.createSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);
    }

    @PostMapping("/reconnect")
    public Mono<SessionData> reconnectSession(@RequestBody ReconnectSessionRequest request) {
        log.info("reconnectSession - Reconnecting session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toReconnectSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.reconnectSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);

    }

    @PostMapping("/refresh")
    public Mono<SessionData> refreshSession(@RequestBody RefreshSessionRequest request) {
        log.info("refreshSession - Refreshing session using request: {}", request.toString());

        SaltEdgeRequest seRequest = toRefreshSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.refreshSaltEdgeSession(seRequest)
                .map(SaltEdgeResponse::getData);
    }

}
