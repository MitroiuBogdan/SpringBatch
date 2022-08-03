package com.ing.casyadapterpoc.saltedge.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ing.casyadapterpoc.saltedge.mock.MockData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums.FetchDataScope;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ReconnectSessionRequestSaltEdge;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.SessionResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.ing.casyadapterpoc.saltedge.mock.MockData.*;

@WireMockTest(httpsEnabled = true, httpsPort = 8088)
@SpringBootTest
class SaltEdgeClientImplTest {

    @Autowired
    SaltEdgeClientImpl saltEdgeClient;

    ObjectMapper objMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void when_createConnectSession_expect_200_connectSessionDataIsReturned() {
        LocalDateTime expectedDate = LocalDateTime.now();
        SessionResponse sessionResponse = sessionResponseMock("example.com", expectedDate.toString());

        stubFor(post("/v5/connect_sessions/create")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objMapper.writeValueAsString(sessionResponse))));

        SaltEdgeConsent consent = MockData.consentMock(
                List.of(FetchDataScope.ACCOUNTS.getScopeValue(),
                        FetchDataScope.TRANSACTIONS.getScopeValue()),
                90);

        SaltEdgeRequest saltEdgeRequest = new SaltEdgeRequest(createSessionRequestSaltEdgeMock("111111111111111111", consent));
        Mono<SessionData> response = saltEdgeClient.createSaltEdgeSession(saltEdgeRequest);

        StepVerifier
                .create(response)
                .expectNext(sessionResponse.getData())
                .expectComplete()
                .verify();
    }
    
    @Test
    void when_reconnectConnectSession_expect_200_connectSessionDataIsReturned() throws JsonProcessingException {
        LocalDateTime expectedDate = LocalDateTime.now();
        SessionResponse sessionResponse = sessionResponseMock("example.com", expectedDate.toString());

        stubFor(post("/v5/connect_sessions/reconnect")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objMapper.writeValueAsString(sessionResponse))));

        SaltEdgeConsent consent = MockData.consentMock(
                List.of(FetchDataScope.ACCOUNTS.getScopeValue(),
                        FetchDataScope.TRANSACTIONS.getScopeValue()),
                90);

        SaltEdgeRequest saltEdgeRequest = new SaltEdgeRequest(reconnectSessionRequestSaltEdgeMock("111111111111111222", "111111111111111111", consent));
        Mono<SessionData> response = saltEdgeClient.reconnectSaltEdgeSession(saltEdgeRequest);

        StepVerifier
                .create(response)
                .expectNext(sessionResponse.getData())
                .expectComplete()
                .verify();
    }

    @Test
    void getAccounts() {
    }

    @Test
    void getTransactions() {
    }
}
