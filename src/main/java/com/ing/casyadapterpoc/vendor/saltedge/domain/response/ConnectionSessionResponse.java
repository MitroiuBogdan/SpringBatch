package com.ing.casyadapterpoc.vendor.saltedge.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConnectionSessionResponse {

    @JsonProperty("data")
    ConnectionSessionData data;
}

