package com.ing.casyadapterpoc.vendor.saltedge.rest;


import com.ing.casyadapterpoc.vendor.saltedge.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ConnectionSessionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("connect_session")
public class ConnectionSessionResource {

    SaltEdgeClientImpl saltEdgeClient;

    @PostMapping("/create")
    public Mono<ResponseEntity<ConnectionSessionResponse>> createGrant(@RequestBody CreateConnectionSessionRequest request) {
        return null;
    }

    @PostMapping("/reconnect")
    public Mono<ResponseEntity<ConnectionSessionResponse>> reconnectGrant(@RequestBody CreateConnectionSessionRequest request) {
        return null;
    }

    @PostMapping("/refresh")
    public Mono<ResponseEntity<ConnectionSessionResponse>> refreshGrant(@RequestBody CreateConnectionSessionRequest request) {
        return null;
    }

}
