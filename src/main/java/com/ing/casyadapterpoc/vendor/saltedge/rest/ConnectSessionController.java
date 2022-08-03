package com.ing.casyadapterpoc.vendor.saltedge.rest;


import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeCreateSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
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

    SaltEdgeClientImpl saltEdgeClient;
    SaltEdgeCreateSessionRequestMapper toSaltegeCreateMapper;

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
    public Mono<ConnectSessionData> reconnectGrant(@RequestBody CreateSaltEdgeSessionRequest request) {
        return null;
    }

    @PostMapping("/refresh")
    public Mono<ConnectSessionData> refreshGrant(@RequestBody CreateSaltEdgeSessionRequest request) {
        return null;
    }

}
