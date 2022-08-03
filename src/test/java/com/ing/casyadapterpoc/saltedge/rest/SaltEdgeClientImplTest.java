package com.ing.casyadapterpoc.saltedge.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ing.casyadapterpoc.saltedge.mock.MockData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.ConnectSessionDataRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums.FetchDataScope;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect.CreateConnectSessionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionData;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect.ConnectSessionResponse;
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
import static com.ing.casyadapterpoc.saltedge.mock.MockData.connectSessionResponseMock;
import static com.ing.casyadapterpoc.saltedge.mock.MockData.createConnectSessionRequest;

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
        ConnectSessionResponse connectSessionResponse = connectSessionResponseMock("example.com", expectedDate.toString());

        stubFor(post("/v5/connect_sessions/create")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objMapper.writeValueAsString(connectSessionResponse))));

        SaltEdgeConsent consent = MockData.createConsent(
                List.of(FetchDataScope.ACCOUNTS.getScopeValue(),
                        FetchDataScope.TRANSACTIONS.getScopeValue()),
                90);
        CreateConnectSessionRequest createRequest = createConnectSessionRequest("111111111111111111", consent);
        ConnectSessionDataRequest connectSessionDataRequest = new ConnectSessionDataRequest();
        connectSessionDataRequest.setData(createRequest);

        Mono<ConnectSessionData> createResponse = saltEdgeClient.createConnectSession(connectSessionDataRequest);
        StepVerifier
                .create(createResponse)
                .expectNext(connectSessionResponse.getData())
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
