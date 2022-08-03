package com.ing.casyadapterpoc.saltedge.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeCreateSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeReconnectSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums.FetchDataScope;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappersTest {

    SaltEdgeCreateSessionRequestMapper toSaltEdgeCreateSessionRequest = new SaltEdgeCreateSessionRequestMapper();
    SaltEdgeReconnectSessionRequestMapper toSaltEdgeReconnectSessionRequest = new SaltEdgeReconnectSessionRequestMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void when_toMapUsingSaltEdgeCreateSessionRequestMapper_expectCorrectMapping() throws JsonProcessingException {

        CreateSessionRequest createSessionRequest = CreateSessionRequest.builder()
                .returnBackUrl("returnBackUrl")
                .providerUserId("providerUserId")
                .providerCode("providerCode")
                .dailyRefresh(true)
                .scopes(List.of(FetchDataScope.ACCOUNTS.getScopeValue(), FetchDataScope.TRANSACTIONS.getScopeValue()))
                .build();

        CreateSaltEdgeSessionRequest request = toSaltEdgeCreateSessionRequest
                .mapTo(createSessionRequest)
                .orElse(null);

        assertEquals("returnBackUrl", request.getAttempt().getReturnTo());
        assertEquals("providerUserId", request.getCustomerId());
        assertEquals("providerCode", request.getProviderCode());
        assertTrue(request.isDailyRefresh());

        log.info("Json format for SaltEdegeCreateSessionRequest {}", objectMapper.writeValueAsString(createSessionRequest));

        SaltEdgeRequest<CreateSaltEdgeSessionRequest> saltEdgeRequest = toSaltEdgeCreateSessionRequest
                .mapTo(createSessionRequest)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        assertNotNull(saltEdgeRequest);

        assertEquals("returnBackUrl", saltEdgeRequest.getData().getAttempt().getReturnTo());
        assertEquals("providerUserId", saltEdgeRequest.getData().getCustomerId());
        assertEquals("providerCode", saltEdgeRequest.getData().getProviderCode());
        assertTrue(saltEdgeRequest.getData().isDailyRefresh());

        log.info("Json format for SaltEdegeRequest {}", objectMapper.writeValueAsString(saltEdgeRequest));
    }

    @Test
    void when_toMapUsingSaltEdgeReconnectSessionRequestMapper_expectCorrectMapping() throws JsonProcessingException {

        ReconnectSessionRequest reconnectSessionRequest = ReconnectSessionRequest.builder()
                .providerGrantId("providerGrantId")
                .returnBackUrl("returnBackUrl")
                .providerUserId("providerUserId")
                .providerCode("providerCode")
                .dailyRefresh(true)
                .scopes(List.of(FetchDataScope.ACCOUNTS.getScopeValue(), FetchDataScope.TRANSACTIONS.getScopeValue()))
                .build();

        ReconnectSaltEdgeSessionRequest request = toSaltEdgeReconnectSessionRequest
                .mapTo(reconnectSessionRequest)
                .orElse(null);

        assertEquals("returnBackUrl", request.getAttempt().getReturnTo());
        assertEquals("providerUserId", request.getCustomerId());
        assertEquals("providerCode", request.getProviderCode());
        assertTrue(request.isDailyRefresh());

        log.info("Json format for SaltEdegeCreateSessionRequest {}", objectMapper.writeValueAsString(reconnectSessionRequest));

        SaltEdgeRequest<ReconnectSaltEdgeSessionRequest> saltEdgeRequest = toSaltEdgeReconnectSessionRequest
                .mapTo(reconnectSessionRequest)
                .map(SaltEdgeRequest::new)
                .orElse(null);

        assertNotNull(saltEdgeRequest);

        assertEquals("providerGrantId", saltEdgeRequest.getData().getConnectionId());
        assertEquals("returnBackUrl", saltEdgeRequest.getData().getAttempt().getReturnTo());
        assertEquals("providerUserId", saltEdgeRequest.getData().getCustomerId());
        assertEquals("providerCode", saltEdgeRequest.getData().getProviderCode());
        assertTrue(saltEdgeRequest.getData().isDailyRefresh());

        log.info("Json format for SaltEdegeRequest {}", objectMapper.writeValueAsString(saltEdgeRequest));
    }
}
