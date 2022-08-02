package com.ing.casyadapterpoc.vendor.saltedge.rest;


import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.ConnectSessionDataRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.connect.ConnectSessionData;
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
public class ConnectSessionResource {

    SaltEdgeClientImpl saltEdgeClient;

    @PostMapping("/create")
    public Mono<ConnectSessionData> createGrant(@RequestBody CreateConnectSessionRequest request) {
        log.info("createGrant - create grant for request...");
        ConnectSessionDataRequest data = new ConnectSessionDataRequest();
        data.setData(request);

        return saltEdgeClient.createConnectSession(data);
    }

    @PostMapping("/reconnect")
    public Mono<ConnectSessionData> reconnectGrant(@RequestBody CreateConnectSessionRequest request) {
        return null;
    }

    @PostMapping("/refresh")
    public Mono<ConnectSessionData> refreshGrant(@RequestBody CreateConnectSessionRequest request) {
        return null;
    }

}
