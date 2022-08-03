package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;


import com.ing.casyadapterpoc.vendor.saltedge.mapper.CreateSessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.ReconnectSessionToSaltEdgeSessionMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequest;
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

    @PostMapping("/create")
    public Mono<SessionData> createSession(@RequestBody CreateSessionRequest request) {
        log.info("createGrant - create grant for request {}", request.toString());

        SaltEdgeRequest seRequest = toCreateSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.createSaltEdgeSession(seRequest);
    }

    @PostMapping("/reconnect")
    public Mono<SessionData> reconnectSession(@RequestBody ReconnectSessionRequest request) {
        log.info("reconnectGrant - reconnect grant for request {}", request.toString());

        SaltEdgeRequest seRequest = toReconnectSaltEdgeSession
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.reconnectSaltEdgeSession(seRequest);

    }

    @PostMapping("/refresh")
    public Mono<SessionData> refreshSession(@RequestBody CreateSessionRequestSaltEdge request) {
        return null;
    }

}
