package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaltEdgeClient {

    //    SaltEdge Connect
    Mono<ConnectSessionData> createConnectSession(SaltEdgeRequest requestBody);

    Mono<ConnectSessionData> refreshConnectSession(SaltEdgeRequest requestBody);

    Mono<ConnectSessionData> reconnectConnectSession(SaltEdgeRequest requestBody);

//    Aggregation

    Flux<SaltedgeAccount> getAccounts();

    Flux<SaltedgeTransaction> getTransactions();


}
