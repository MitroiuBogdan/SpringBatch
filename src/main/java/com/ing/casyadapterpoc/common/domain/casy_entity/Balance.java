package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    private Double content;
    private String currency;
}
