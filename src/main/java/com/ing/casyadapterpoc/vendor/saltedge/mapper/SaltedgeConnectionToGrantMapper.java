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

    @Override
    public Grant apply(SaltedgeConnection saltedgeConnection) {
        return Grant.builder()
                .providerGrantId(saltedgeConnection.getId())
                .lastRefreshTimestampProvider(saltedgeConnection.getUpdatedAt())
                .providerStatus(saltedgeConnection.getStatus())
                .providerReason(saltedgeConnection.getLastAttempt().getFailMessage())
                .build();
    }
}
