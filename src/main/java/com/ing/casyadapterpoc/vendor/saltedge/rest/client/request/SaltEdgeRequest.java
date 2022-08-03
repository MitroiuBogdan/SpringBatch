package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request;

import lombok.Data;

@Data
public class SaltEdgeRequest<T> {

    private T data;

    public SaltEdgeRequest(T data) {
        this.data = data;
    }
}
