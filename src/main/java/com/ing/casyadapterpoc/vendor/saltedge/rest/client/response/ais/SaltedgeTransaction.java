package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SaltedgeTransaction {
    private String id;
    private String account_id;
    private Boolean duplicated;
    private String mode;
    private String status;
    private String made_on;
    private BigDecimal amount;
    private String currency_code;
    private String description;
    private String category;
    private SaltedgeTransactionExtra extra;
    private String created_at;
    private String updated_at;
}
