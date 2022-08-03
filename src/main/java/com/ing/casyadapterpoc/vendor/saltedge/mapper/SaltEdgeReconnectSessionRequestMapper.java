package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttempt;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor
@Component
public class SaltEdgeReconnectSessionRequestMapper {

    public Optional<ReconnectSaltEdgeSessionRequest> mapTo(ReconnectSessionRequest source) {
        if (source == null) {
            return Optional.empty();
        }
        ReconnectSaltEdgeSessionRequest target = ReconnectSaltEdgeSessionRequest.builder()
                .connectionId(source.getProviderGrantId())
                .customerId(source.getProviderUserId())
                .providerCode(source.getProviderCode())
                .dailyRefresh(source.isDailyRefresh())
                .consent(SaltEdgeConsent.builder()
                        .scopes(source.getScopes())
                        .activationDate(source.getActivationDate())
                        .consentValidityDays(source.getConsentValidityDays())
                        .expirationDate(source.getExpirationDate())
                        .build())
                .attempt(SaltEdgeAttempt.builder()
                        .returnTo(source.getReturnBackUrl())
                        .build())
                .build();

        return Optional.of(target);
    }
}
