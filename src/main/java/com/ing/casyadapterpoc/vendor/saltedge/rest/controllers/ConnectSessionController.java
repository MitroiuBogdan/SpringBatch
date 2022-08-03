package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;


import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeCreateSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeReconnectSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
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
@RequestMapping("/connect-flow")
public class ConnectSessionController {

    private final SaltEdgeClientImpl saltEdgeClient;
    private final SaltEdgeCreateSessionRequestMapper toSaltegeCreateMapper;
    private final SaltEdgeReconnectSessionRequestMapper toSaltegeReconnectMapper;

    @PostMapping("/create")
    public Mono<ConnectSessionData> createGrant(@RequestBody CreateSessionRequest request) {
        log.info("createGrant - create grant for request {}", request.toString());

        SaltEdgeRequest saltEdgeRequest = toSaltegeCreateMapper
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.createConnectSession(saltEdgeRequest);
    }

    @PostMapping("/reconnect")
    public Mono<ConnectSessionData> reconnectGrant(@RequestBody ReconnectSessionRequest request) {
        log.info("reconnectGrant - reconnect grant for request {}", request.toString());

        SaltEdgeRequest saltEdgeRequest = toSaltegeReconnectMapper
                .mapTo(request)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        return saltEdgeClient.reconnectConnectSession(saltEdgeRequest);

    }

    @PostMapping("/refresh")
    public Mono<ConnectSessionData> refreshGrant(@RequestBody CreateSaltEdgeSessionRequest request) {
        return null;
    }

}
