package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateConnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaltEdgeClient {

    //    SaltEdge Connect
    Mono<ConnectSessionData> createConnectSession(CreateConnectSessionRequest request);

    Mono<ConnectSessionResponse> refreshConnectSession(ReconnectConnectionSessionRequest request);

    Mono<ConnectSessionResponse> reconnectConnectSession(CreateConnectSessionRequest request);

//    Aggregation

    Flux<SaltedgeAccount> getAccounts();

    Flux<SaltedgeTransaction> getTransactions();


}
