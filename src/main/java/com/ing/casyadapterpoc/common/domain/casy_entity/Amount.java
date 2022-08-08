package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class Amount {

    private String currency;
    private Double content;
}
