package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO: Refactor after mappers implementation
@Data
@NoArgsConstructor
public class ConnectSessionDataRequest<T> {

    @JsonProperty("data")
    private T data;

    public void setData(T data) {
        this.data = data;
    }
}
