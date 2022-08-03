package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccountResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransactionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.utils.ResponseHelper;
import lombok.RequiredArgsConstructor;
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
    private static final String SALTEDGE_CONNECT_PATH = "v5/connect_sessions/create";

    private final WebClient webClient;

    @Override
    public Mono<ConnectSessionData> createConnectSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_CONNECT_PATH)
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(ConnectSessionResponse.class)
                .map(ConnectSessionResponse::getData);
    }

    @Override
    public Mono<ConnectSessionData> refreshConnectSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_CONNECT_PATH).build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(ConnectSessionResponse.class)
                .map(ConnectSessionResponse::getData);
    }

    @Override
    public Mono<ConnectSessionData> reconnectConnectSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_CONNECT_PATH).build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(ConnectSessionResponse.class)
                .map(ConnectSessionResponse::getData);
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
