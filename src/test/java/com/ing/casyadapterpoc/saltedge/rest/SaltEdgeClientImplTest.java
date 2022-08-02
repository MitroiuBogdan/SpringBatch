package com.ing.casyadapterpoc.saltedge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ing.casyadapterpoc.saltedge.mock.MockData;
import com.ing.casyadapterpoc.vendor.saltedge.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.domain.enums.FetchDataScope;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect.CreateConnectionSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeResponse;
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

@WireMockTest(httpsEnabled = true, httpsPort = 8088)
@SpringBootTest
class SaltEdgeClientImplTest {

    @Autowired
    SaltEdgeClientImpl saltEdgeClient;

    ObjectMapper objMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void test_createConnectSession() {
        LocalDateTime expectedDate = LocalDateTime.now();
        SaltEdgeResponse saltEdgeResponse = MockData.createSaltEdgeResponse("example.com", expectedDate.toString());

        SaltEdgeConsent consent = MockData.createConsent(
                List.of(FetchDataScope.ACCOUNTS.getScopeValue(),
                        FetchDataScope.TRANSACTIONS.getScopeValue()),
                90);

        CreateConnectionSessionRequest createRequest = MockData.createConnectionSessionRequest("111111111111111111", consent);

        stubFor(post("/v5/connect_sessions/create")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objMapper.writeValueAsString(saltEdgeResponse))));

        Mono<SaltEdgeResponse> createResponse = saltEdgeClient.createConnectSession(createRequest);
        StepVerifier
                .create(createResponse)
                .expectNext(saltEdgeResponse)
                .expectComplete()
                .verify();
    }

    @Test
    void refreshConnectSession() {
    }

    @Test
    void reconnectConnectSession() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void getTransactions() {
    }
}
