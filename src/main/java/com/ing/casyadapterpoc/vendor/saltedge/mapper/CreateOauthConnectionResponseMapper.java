package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth.CreateOauthConnectionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth.CreateOauthConnectionSaltEdgeResponseData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOauthConnectionResponseMapper implements Function<SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData>, CreateOauthConnectionResponse> {

    public static final CreateOauthConnectionResponseMapper CREATE_OAUTH_CONNECTION_RESPONSE_MAPPER = new CreateOauthConnectionResponseMapper();

    @Override
    public CreateOauthConnectionResponse apply(SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData> saltEdgeResponse) {
        CreateOauthConnectionSaltEdgeResponseData saltEdgeResponseData = saltEdgeResponse.getData();
        return CreateOauthConnectionResponse.builder()
                .connectionId(saltEdgeResponseData.getConnectionId())
                .connectionSecret(saltEdgeResponseData.getConnectionSecret())
                .attemptId(saltEdgeResponseData.getAttemptId())
                .token(saltEdgeResponseData.getToken())
                .expiresAt(saltEdgeResponseData.getExpiresAt())
                .redirectUrl(saltEdgeResponseData.getRedirectUrl())
                .build();
    }
}
