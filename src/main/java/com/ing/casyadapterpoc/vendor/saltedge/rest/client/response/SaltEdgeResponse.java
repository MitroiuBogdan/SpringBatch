package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaltEdgeResponse<T> {
    private T data;
}
