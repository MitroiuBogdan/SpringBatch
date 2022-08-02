package com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SaltedgeTransactionExtra {
    private String payee;
    private String posting_date;
    private String end_to_end_id;
    private BigDecimal account_balance_snapshot;
    private Long categorization_confidence;

}
