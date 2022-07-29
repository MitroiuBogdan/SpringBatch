package com.ing.casyadapterpoc.vendor.saltedge.client;

import com.ing.casyadapterpoc.vendor.saltedge.response.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class SaltEdgeClient {

    private static final String SALTEDGE_ACCOUNT_PATH = "v5/accounts";
    private static final String SALTEDGE_TRASACTION_PATH = "v5/transactions";

    private final WebClient webClient;

    public SaltEdgeClient(@Qualifier("saltedgeWebClient")WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<SaltedgeAccount> getAccounts() {
//++apiCall
        return webClient.get().uri(uriBuilder ->
                uriBuilder.path(SALTEDGE_ACCOUNT_PATH)
                        .queryParam("connection_id","785075132128303256")
                        .build()
        ).retrieve()
                .bodyToMono(SaltedgeAccountResponse.class)
                .flatMapMany(this::getFluxFromListResponse);
    }

    public Flux<SaltedgeTransaction> getTransactions(){
        return webClient.get().uri(uriBuilder ->
                uriBuilder.path(SALTEDGE_TRASACTION_PATH)
                        .queryParam("connection_id","785075132128303256")
                        .build()
        ).retrieve()
                .bodyToMono(SaltedgeTransactionResponse.class)
                .flatMapMany(this::getFluxFromListResponse);
    }

    private <T> Flux<T> getFluxFromListResponse(SaltEdgeListResponse<T> response){
        return Flux.fromIterable(response.getData());
    }
}
