package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class Merchant {
    private String bban;
    private String iban;
    private String maskedPan;
    private String pan;
}
