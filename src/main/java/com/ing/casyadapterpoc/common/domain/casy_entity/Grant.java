package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Grant {

    private String id;

    private String providerGrantId;

    private String clientId;

    private String countryCode;

    private String status;

    private String state;

    private String refreshStatus;

    private String lastRefreshTimestamp;

    private String lastSuccessfulRefreshTimestamp;

    private String lastRefreshTimestampProvider;

    private String statusTimestamp;

    private String startTimestamp;

    private String expectedExpirationDate;

    private String endTimestamp;

    private String userId;

    private String providerStatus;

    private String providerReason;
}
