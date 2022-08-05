package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOauthConnectionResponse {

    private String connectionId;

    private String connectionSecret;

    private String attemptId;

    private String token;

    private String expiresAt;

    private String redirectUrl;
}
