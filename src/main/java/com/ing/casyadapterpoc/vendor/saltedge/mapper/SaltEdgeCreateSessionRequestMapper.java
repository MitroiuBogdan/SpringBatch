package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttempt;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor
@Component
public class SaltEdgeCreateSessionRequestMapper {

    public Optional<CreateSaltEdgeSessionRequest> mapTo(CreateSessionRequest createSessionRequest) {
        if (createSessionRequest == null) {
            return Optional.empty();
        }
        CreateSaltEdgeSessionRequest createSaltEdgeSessionRequest = CreateSaltEdgeSessionRequest.builder()
                .customerId(createSessionRequest.getProviderUserId())
                .providerCode(createSessionRequest.getProviderCode())
                .dailyRefresh(createSessionRequest.isDailyRefresh())
                .consent(SaltEdgeConsent.builder()
                        .scopes(createSessionRequest.getScopes())
                        .activationDate(createSessionRequest.getActivationDate())
                        .consentValidityDays(createSessionRequest.getConsentValidityDays())
                        .expirationDate(createSessionRequest.getExpirationDate())
                        .build())
                .attempt(SaltEdgeAttempt.builder()
                        .returnTo(createSessionRequest.getReturnBackUrl())
                        .build())
                .build();

        return Optional.of(createSaltEdgeSessionRequest);
    }
}
