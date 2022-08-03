package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaltEdgeRequest<T> {

    @JsonProperty("data")
    private T data;

    public SaltEdgeRequest(T data) {
        this.data = data;
    }
}
