package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttemptRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.SessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.SessionRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Component
public class SessionToSaltEdgeSessionMapper {

    public Optional<SessionRequestSaltEdge> mapTo(SessionRequest source) {
        if (source == null) {
            return Optional.empty();
        }
        SessionRequestSaltEdge target = SessionRequestSaltEdge.builder()
                .connectionId(source.getProviderGrantId())
                .customerId(source.getProviderUserId())
                .providerCode(source.getAspspCode())
                .categorization(source.getCategorization())
                .dailyRefresh(source.getDailyRefresh())
                .consent(SaltEdgeConsent.builder()
                        .scopes(List.of("account_details", "transactions_details"))
                        .activationDate(source.getActivationDate())
                        .consentValidityDays(90D)
                        .expirationDate(source.getExpirationDate())
                        .build())
                .attempt(SaltEdgeAttemptRequest.builder()
                        .returnTo(source.getReturnBackUrl())
                        .build())
                .build();

        return Optional.of(target);
    }
}
