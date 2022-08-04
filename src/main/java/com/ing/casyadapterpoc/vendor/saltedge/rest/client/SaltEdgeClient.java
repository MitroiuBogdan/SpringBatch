package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaltEdgeClient {

    //    SaltEdge Connect
    Mono<SessionData> createSaltEdgeSession(SaltEdgeRequest requestBody);

    Mono<SessionData> refreshSaltEdgeSession(SaltEdgeRequest requestBody);

    Mono<SessionData> reconnectSaltEdgeSession(SaltEdgeRequest requestBody);

//    Aggregation

    Flux<SaltedgeAccount> getAccounts(String connectionId);

    Flux<SaltedgeTransaction> getTransactions(String connectionId);

    Flux<SaltedgeTransaction> getTransactionsByAccountId(String connectionId, String accountId);


}
