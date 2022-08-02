package com.ing.casyadapterpoc.vendor.saltedge.client;

import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.ReconnectConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private static final String SALTEDGE_CONNECTION_SESSION_PATH = "v5/connect_sessions/create";

    private final WebClient webClient;

    @Override
    public Mono<SaltEdgeResponse> createConnectSession(CreateConnectionSessionRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_CONNECTION_SESSION_PATH).build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(SaltEdgeResponse.class);

    }

    @Override
    public Mono<ResponseEntity> refreshConnectSession(ReconnectConnectionSessionRequest request) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> reconnectConnectSession(CreateConnectionSessionRequest request) {
        return null;
    }

    @Override
    public Flux<ResponseEntity> getGrants(CreateConnectionSessionRequest request) {
        return null;
    }

    @Override
    public Flux<SaltedgeAccount> getAccounts() {
//++apiCall
        return webClient.get().uri(uriBuilder -> uriBuilder.path(SALTEDGE_ACCOUNT_PATH)
                        .queryParam("connection_id", "785075132128303256").build())
                .retrieve()
                .bodyToMono(SaltedgeAccountResponse.class)
                .flatMapMany(this::getFluxFromListResponse);
    }

    @Override
    public Flux<SaltedgeTransaction> getTransactions() {
        return webClient.get().uri(uriBuilder -> uriBuilder.path(SALTEDGE_TRASACTION_PATH).queryParam("connection_id", "785075132128303256").build()).retrieve().bodyToMono(SaltedgeTransactionResponse.class).flatMapMany(this::getFluxFromListResponse);
    }

    private <T> Flux<T> getFluxFromListResponse(SaltEdgeListResponse<T> response) {
        return Flux.fromIterable(response.getData());
    }
}
