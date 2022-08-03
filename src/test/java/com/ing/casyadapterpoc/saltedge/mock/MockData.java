package com.ing.casyadapterpoc.saltedge.mock;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.RefreshSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionResponse;

import java.time.ZonedDateTime;
import java.util.List;

public interface MockData {

    static SessionResponse sessionResponseMock(String connectionUrl, String date) {
        return SessionResponse.builder()
                .data(
                        SessionData.builder()
                                .connectUrl(connectionUrl)
                                .expiresAt(date)
                                .build())
                .build();
    }

    static CreateSessionRequestSaltEdge createSessionRequestSaltEdgeMock(String customerId, SaltEdgeConsent consent) {
        return CreateSessionRequestSaltEdge.builder()
                .customerId(customerId)
                .consent(consent)
                .dailyRefresh(true)
                .build();
    }

    static ReconnectSessionRequestSaltEdge reconnectSessionRequestSaltEdgeMock(String connectionId, String customerId, SaltEdgeConsent consent) {
        return ReconnectSessionRequestSaltEdge.builder()
                .connectionId(connectionId)
                .customerId(customerId)
                .consent(consent)
                .dailyRefresh(true)
                .build();
    }

    static RefreshSessionRequestSaltEdge refreshSessionRequestSaltEdgeMock(String connectionId, String customerId) {
        return RefreshSessionRequestSaltEdge.builder()
                .connectionId(connectionId)
                .customerId(customerId)
                .dailyRefresh(true)
                .build();
    }

    static SaltEdgeConsent consentMock(List<String> scopes, int validityDays) {
        return SaltEdgeConsent.builder()
                .scopes(scopes)
                .activationDate(ZonedDateTime.now().toString())
                .expirationDate(ZonedDateTime.now().plusDays(validityDays).toString())
                .consentValidityDays(validityDays)
                .build();
    }
}
