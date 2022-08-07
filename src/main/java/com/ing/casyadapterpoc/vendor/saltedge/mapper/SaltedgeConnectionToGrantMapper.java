package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.Grant;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeConnection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeConnectionToGrantMapper implements Function<SaltedgeConnection, Grant> {

    public static SaltedgeConnectionToGrantMapper SALTEDGE_CONNECTION_TO_GRANT_MAPPER = new SaltedgeConnectionToGrantMapper();

    private static final String NOT_MAPPED = "#_not_mapped_#";

    @Override
    public Grant apply(SaltedgeConnection saltedgeConnection) {
        return Grant.builder()
                .id(UUID.randomUUID().toString())
                .providerGrantId(saltedgeConnection.getId())
                .clientId("Casy-PoC-technical-client-id")
                .countryCode(saltedgeConnection.getCountryCode())
                .status(NOT_MAPPED)
                .state(NOT_MAPPED)
                .refreshStatus(NOT_MAPPED)
                .lastRefreshTimestampProvider(saltedgeConnection.getUpdatedAt())
                .statusTimestamp(NOT_MAPPED)
                .startTimestamp(saltedgeConnection.getCreatedAt())
                .expectedExpirationDate(NOT_MAPPED)
                .userId(UUID.randomUUID().toString())
                .providerStatus(saltedgeConnection.getStatus())
                .providerReason(NOT_MAPPED)
                .build();
    }
}
