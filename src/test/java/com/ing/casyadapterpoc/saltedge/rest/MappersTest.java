package com.ing.casyadapterpoc.saltedge.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltEdgeCreateSessionRequestMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums.FetchDataScope;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSaltEdgeSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappersTest {

    SaltEdgeCreateSessionRequestMapper toSaltEdgeCreateSessionRequest = new SaltEdgeCreateSessionRequestMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test_createSessionRequest_toSaltEdgeRequestMapper() throws JsonProcessingException {

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
}
