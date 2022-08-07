package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttempt;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth.CreateOauthConnectionRequestDataSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeConnection;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth.CreateOauthConnectionSaltEdgeResponseData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaltEdgeClient {

    //    SaltEdge Connect
    Mono<SessionData> createSaltEdgeSession(SaltEdgeRequest requestBody);

    Mono<SessionData> refreshSaltEdgeSession(SaltEdgeRequest requestBody);

    Mono<SessionData> reconnectSaltEdgeSession(SaltEdgeRequest requestBody);


    // Oauth connection
    Mono<SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData>> createOauthConnection(SaltEdgeRequest<CreateOauthConnectionRequestDataSaltEdge> requestBody);

//    Aggregation

    Flux<SaltedgeAccount> getAccounts(String connectionId);

    Flux<SaltedgeTransaction> getTransactions(String connectionId, String accountId);

    Mono<SaltEdgeResponse<SaltedgeConnection>> refreshConnectionById(String connectionId, SaltEdgeRequest<SaltEdgeAttempt> requestBody);

}
