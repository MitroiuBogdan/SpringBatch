package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.common.domain.casy_entity.Aspsp;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClient;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeListResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeAspspMapper.SALTEDGE_ASPSP_MAPPER;

@Service
@AllArgsConstructor
@Slf4j
public class SaltedgeAspspService {

    private SaltEdgeClient saltEdgeClient;

    public Mono<Aspsp> getByProviderCode(String providerCode) {
        return saltEdgeClient.getAspspByProviderCode(providerCode)
                .map(SaltEdgeResponse::getData)
                .map(SALTEDGE_ASPSP_MAPPER);
    }

    public Flux<Aspsp> getAspsps() {
        return saltEdgeClient.getAspsps()
                .flatMapIterable(SaltEdgeListResponse::getData)
                .map(SALTEDGE_ASPSP_MAPPER);
    }
}
