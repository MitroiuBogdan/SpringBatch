package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOauthConnectionSaltEdgeResponseData {

    @JsonProperty("connection_id")
    private String connectionId;

    @JsonProperty("connection_secret")
    private String connectionSecret;

    @JsonProperty("attempt_id")
    private String attemptId;

    private String token;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("redirect_url")
    private String redirectUrl;


}
