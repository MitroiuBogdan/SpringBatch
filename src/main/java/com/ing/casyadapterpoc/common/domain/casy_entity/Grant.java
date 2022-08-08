package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Grant {

    private String providerGrantId;

    private String lastRefreshTimestampProvider;

    private String providerStatus;

    private String providerReason;
}
