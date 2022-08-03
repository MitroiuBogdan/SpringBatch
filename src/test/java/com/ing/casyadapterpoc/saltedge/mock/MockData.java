package com.ing.casyadapterpoc.saltedge.mock;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateConnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionResponse;

import java.time.ZonedDateTime;
import java.util.List;

public interface MockData {

    static ConnectSessionResponse connectSessionResponseMock(String connectionUrl, String date) {
        return ConnectSessionResponse.builder()
                .data(
                        ConnectSessionData.builder()
                                .connectUrl(connectionUrl)
                                .expiresAt(date)
                                .build())
                .build();
    }

    static ConnectSessionData connectSessionDataResponse(String connectionUrl, String date) {
        return ConnectSessionData.builder()
                .connectUrl(connectionUrl)
                .expiresAt(date)
                .build();
    }


    static CreateConnectSessionRequest createConnectSessionRequest(String customerId, SaltEdgeConsent consent) {
        return CreateConnectSessionRequest.builder()
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
