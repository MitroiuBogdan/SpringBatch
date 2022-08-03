package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectSessionResponse {

    @JsonProperty("data")
    ConnectSessionData data;
}

