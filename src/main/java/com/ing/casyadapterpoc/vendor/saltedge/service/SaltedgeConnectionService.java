package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Grant;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClient;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttempt;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeConnectionToGrantMapper.SALTEDGE_CONNECTION_TO_GRANT_MAPPER;

@Slf4j
@AllArgsConstructor
@Service
public class SaltedgeConnectionService {

    private final SaltEdgeClient saltEdgeClient;

    public Mono<Grant> refreshById(String connectionId) {
        SaltEdgeAttempt attempt = SaltEdgeAttempt.builder()
                .fetchScopes(List.of("accounts", "transactions"))
                .build();
        return saltEdgeClient.refreshConnectionById(connectionId, new SaltEdgeRequest<>(attempt))
                .doOnNext(refreshResponse -> log.info("refreshById - refresh succeeded for connection {}", refreshResponse.getData()))
                .map(refreshResponse -> SALTEDGE_CONNECTION_TO_GRANT_MAPPER.apply(refreshResponse.getData()));
    }

    public Mono<SaltedgeDeleteResponse> deleteById(String connectionId) {
        return saltEdgeClient.deleteConnectionById(connectionId)
                .doOnNext(deleteResponse -> log.info("deleteById - deletion succeeded for connection {}", connectionId))
                .map(SaltEdgeResponse::getData);
    }
}
