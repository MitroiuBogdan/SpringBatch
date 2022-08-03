package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {

    @JsonProperty("data")
    SessionData data;
}

