package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Aspsp {

    private String providerAspspId;
    private String name;
    private String icon;
    private String additionalInfoNeeded;
    private String loginType;
    private String accountGrantStrategy;

}
