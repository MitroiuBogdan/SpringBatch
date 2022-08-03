package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttempt;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.RefreshSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.RefreshSessionRequestSaltEdge;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor
@Component
public class RefreshSessionToSaltEdgeSessionMapper {

    public Optional<RefreshSessionRequestSaltEdge> mapTo(RefreshSessionRequest source) {
        if (source == null) {
            return Optional.empty();
        }
        RefreshSessionRequestSaltEdge target = RefreshSessionRequestSaltEdge.builder()
                .connectionId(source.getProviderGrantId())
                .customerId(source.getProviderUserId())
                .providerCode(source.getProviderCode())
                .dailyRefresh(source.isDailyRefresh())
                .attempt(SaltEdgeAttempt.builder()
                        .returnTo(source.getReturnBackUrl())
                        .build())
                .build();

        return Optional.of(target);
    }
}
