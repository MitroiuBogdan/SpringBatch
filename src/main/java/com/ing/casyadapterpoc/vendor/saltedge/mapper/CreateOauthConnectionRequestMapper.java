package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttemptRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth.CreateOauthConnectionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth.CreateOauthConnectionRequestDataSaltEdge;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOauthConnectionRequestMapper implements Function<CreateOauthConnectionRequest, SaltEdgeRequest<CreateOauthConnectionRequestDataSaltEdge>> {

    public static final CreateOauthConnectionRequestMapper CREATE_OAUTH_CONNECTION_REQUEST_MAPPER = new CreateOauthConnectionRequestMapper();
    private static final String ROTARU_CUSTOMER_ID = "803757169278196241";

    @Override
    public SaltEdgeRequest<CreateOauthConnectionRequestDataSaltEdge> apply(CreateOauthConnectionRequest createGrantRequest) {
        CreateOauthConnectionRequestDataSaltEdge saltEdgeRequestData = CreateOauthConnectionRequestDataSaltEdge.builder()
                .customerId(createGrantRequest.getCustomerId() == null ? ROTARU_CUSTOMER_ID : createGrantRequest.getCustomerId())
                .countryCode(createGrantRequest.getCountryCode())
                .providerCode(createGrantRequest.getProviderCode())
                .consent(SaltEdgeConsent.builder()
                        .scopes(List.of("account_details", "transactions_details"))
                        .build())
                .attempt(SaltEdgeAttemptRequest.builder()
                        .fetchScopes(List.of("accounts", "transactions"))
                        .build())
                .build();
        return new SaltEdgeRequest<>(saltEdgeRequestData);
    }
}
