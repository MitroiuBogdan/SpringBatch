package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClient;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth.CreateOauthConnectionRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.oauth.CreateOauthConnectionSaltEdgeResponseData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.ing.casyadapterpoc.vendor.saltedge.mapper.CreateOauthConnectionRequestMapper.CREATE_OAUTH_CONNECTION_REQUEST_MAPPER;

@AllArgsConstructor
@Service
public class SaltedgeOauthConnectionService {

    private final SaltEdgeClient saltEdgeClient;

    public Mono<SaltEdgeResponse<CreateOauthConnectionSaltEdgeResponseData>> createConnection(CreateOauthConnectionRequest request) {
        return saltEdgeClient.createOauthConnection(CREATE_OAUTH_CONNECTION_REQUEST_MAPPER.apply(request));
    }
}
