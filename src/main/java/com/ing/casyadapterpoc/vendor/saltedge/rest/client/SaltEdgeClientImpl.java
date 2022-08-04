package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth.CreateOauthConnectionRequestDataSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccountResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransactionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth.CreateOauthConnectionSaltEdgeResponseData;
import com.ing.casyadapterpoc.vendor.saltedge.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Service
@RequiredArgsConstructor
public class SaltEdgeClientImpl implements SaltEdgeClient {

    private static final String SALTEDGE_ACCOUNT_PATH = "v5/accounts";
    private static final String SALTEDGE_TRASACTION_PATH = "v5/transactions";
    private static final String SALTEDGE_CONNECT_PATH = "v5/connect_sessions";
    private static final String SALTEDGE_OAUTH_PATH = "/v5/oauth_providers";

    private final WebClient webClient;

    @Override
    public Mono<SessionData> createSaltEdgeSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_CONNECT_PATH+"/create")
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(SessionResponse.class)
                .map(SessionResponse::getData);
    }

    @Override
    public Mono<SessionData> refreshSaltEdgeSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_CONNECT_PATH+"/refresh")
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(SessionResponse.class)
                .map(SessionResponse::getData);
    }

    @Override
    public Mono<SessionData> reconnectSaltEdgeSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_CONNECT_PATH+"/reconnect")
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(SessionResponse.class)
                .map(SessionResponse::getData);
    }

    @Override
    public Mono<SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData>> createOauthConnection(SaltEdgeRequest<CreateOauthConnectionRequestDataSaltEdge> requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_OAUTH_PATH+"/create")
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData>>() {});
    }


    @Override
    public Flux<SaltedgeAccount> getAccounts() {
//++apiCall
        return webClient.get().uri(uriBuilder -> uriBuilder.path(SALTEDGE_ACCOUNT_PATH)
                        .queryParam("connection_id", "785075132128303256").build())
                .retrieve()
                .bodyToMono(SaltedgeAccountResponse.class)
                .flatMapMany(ResponseHelper::getFluxFromData);
    }

    @Override
    public Flux<SaltedgeTransaction> getTransactions() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_TRASACTION_PATH)
                        .queryParam("connection_id", "785075132128303256").build())
                .retrieve().bodyToMono(SaltedgeTransactionResponse.class)
                .flatMapMany(ResponseHelper::getFluxFromData);
    }

}
