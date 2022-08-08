package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeRate {

    private String currencyFrom;
    private String currencyTo;
    private Double rate;
}
