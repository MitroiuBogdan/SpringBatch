package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaltEdgeRequest<T> {

    private T data;

    public SaltEdgeRequest(T data) {
        this.data = data;
    }
}
