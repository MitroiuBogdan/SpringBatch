package com.ing.casyadapterpoc.vendor.saltedge.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectionSessionData {

    @JsonProperty("connect_url")
    String connectUrl;
    @JsonProperty("expires_at")
    String expiresAt;

}
