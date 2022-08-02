package com.ing.casyadapterpoc.vendor.saltedge.utils;

import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeListResponse;
import reactor.core.publisher.Flux;

public interface ResponseHelper {

    static <T> Flux<T> getFluxFromData(SaltEdgeListResponse<T> response) {
        return Flux.fromIterable(response.getData());
    }
}
