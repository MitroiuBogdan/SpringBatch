package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccountResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransactionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionResponse;
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
    private static final String SALTEDGE_CONNECT_PATH = "v5/connect_sessions";

    private final WebClient webClient;

    @Override
    public Mono<SessionData> createSaltEdgeSession(SaltEdgeRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(SALTEDGE_CONNECT_PATH + "/create")
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
                        .path(SALTEDGE_CONNECT_PATH + "/refresh")
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
                        .path(SALTEDGE_CONNECT_PATH + "/reconnect")
                        .build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(SessionResponse.class)
                .map(SessionResponse::getData);
    }


    @Override
    public Flux<SaltedgeAccount> getAccounts(String connectionId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_ACCOUNT_PATH)
                        .queryParam("connection_id", connectionId).build())
                .retrieve()
                .bodyToMono(SaltedgeAccountResponse.class)
                .flatMapMany(ResponseHelper::getFluxFromData);
    }

    @Override
    public Flux<SaltedgeTransaction> getTransactions(String connectionId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_TRASACTION_PATH)
                        .queryParam("connection_id", connectionId).build())
                .retrieve().bodyToMono(SaltedgeTransactionResponse.class)
                .flatMapMany(ResponseHelper::getFluxFromData);
    }

    @Override
    public Flux<SaltedgeTransaction> getTransactionsByAccountId(String connectionId, String accountId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_TRASACTION_PATH)
                        .queryParam("connection_id", connectionId)
                        .queryParam("account_id", accountId)
                        .build())
                .retrieve().bodyToMono(SaltedgeTransactionResponse.class)
                .flatMapMany(ResponseHelper::getFluxFromData);
    }

}
