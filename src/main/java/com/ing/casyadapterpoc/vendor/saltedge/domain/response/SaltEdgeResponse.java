package com.ing.casyadapterpoc.vendor.saltedge.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaltEdgeResponse {

    @JsonProperty("data")
    private ConnectionSessionData data;

}
