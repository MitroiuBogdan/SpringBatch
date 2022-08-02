package com.ing.casyadapterpoc.vendor.saltedge.rest.client;

import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.ConnectSessionDataRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.ReconnectConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais.SaltedgeAccount;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais.SaltedgeAccountResponse;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais.SaltedgeTransaction;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais.SaltedgeTransactionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.connect.ConnectSessionResponse;
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
    private static final String SALTEDGE_CONNECTION_SESSION_PATH = "v5/connect_sessions/create";

    private final WebClient webClient;

//    @Override
    public Mono<ConnectSessionData> createConnectSession(ConnectSessionDataRequest requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(SALTEDGE_CONNECTION_SESSION_PATH).build())
                .body(fromValue(requestBody))
                .retrieve()
                .bodyToMono(ConnectSessionResponse.class)
                .map(ConnectSessionResponse::getData);
    }

    @Override
    public Mono<ConnectSessionData> createConnectSession(CreateConnectSessionRequest request) {
        return null;
    }

    @Override
    public Mono<ConnectSessionResponse> refreshConnectSession(ReconnectConnectionSessionRequest request) {
        return null;
    }

    @Override
    public Mono<ConnectSessionResponse> reconnectConnectSession(CreateConnectSessionRequest request) {
        return null;
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
