package com.ing.casyadapterpoc.vendor.saltedge.client;

import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.ReconnectConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltedgeTransaction;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaltEdgeClient {

    //    SaltEdge Connect
    Mono<SaltEdgeResponse> createConnectSession(CreateConnectionSessionRequest request);

    Mono<ResponseEntity> refreshConnectSession(ReconnectConnectionSessionRequest request);

    Mono<ResponseEntity> reconnectConnectSession(CreateConnectionSessionRequest request);

//    Aggregation

    Flux<ResponseEntity> getGrants(CreateConnectionSessionRequest request);

    Flux<SaltedgeAccount> getAccounts();

    Flux<SaltedgeTransaction> getTransactions();


}
