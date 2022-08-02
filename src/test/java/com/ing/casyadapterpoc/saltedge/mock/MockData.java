package com.ing.casyadapterpoc.saltedge.mock;

import com.ing.casyadapterpoc.vendor.saltedge.domain.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ConnectionSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.ConnectionSessionResponse;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeResponse;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface MockData {

    static ConnectionSessionResponse connectionSessionResponseMock(String connectionUrl, String date) {
        return ConnectionSessionResponse.builder()
                .data(
                        ConnectionSessionData.builder()
                                .connectUrl(connectionUrl)
                                .expiresAt(date)
                                .build())
                .build();
    }

    static SaltEdgeResponse createSaltEdgeResponse(String connectionUrl, String date) {
        return SaltEdgeResponse.builder()
                .data(
                        ConnectionSessionData.builder()
                                .connectUrl(connectionUrl)
                                .expiresAt(date)
                                .build())
                .build();
    }


    static CreateConnectionSessionRequest createConnectionSessionRequest(String customerId, SaltEdgeConsent consent) {
        return CreateConnectionSessionRequest.builder()
                .customerId(customerId)
                .consent(consent)
                .dailyRefresh(true)
                .build();
    }

    static SaltEdgeConsent createConsent(List<String> scopes, int validityDays) {
        return SaltEdgeConsent.builder()
                .scopes(scopes)
                .activationDate(ZonedDateTime.now().toString())
                .expirationDate(ZonedDateTime.now().plusDays(validityDays).toString())
                .consentValidityDays(validityDays)
                .build();
    }
}
