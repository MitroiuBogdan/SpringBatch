package com.ing.casyadapterpoc.vendor.saltedge.response;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
@ToString
public class SaltedgeAccountExtra {
    private String bban;
    private String iban;
    private String sort_code;
    private String raw_balance;
    private String account_name;
    private String balance_type;
    private String current_date;
    private String current_time;
    private SaltedgeTransactionCount transactions_count;
    private String last_posted_transaction_id;
}
