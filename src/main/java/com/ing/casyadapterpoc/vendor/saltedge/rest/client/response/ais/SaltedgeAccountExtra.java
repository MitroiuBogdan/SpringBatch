package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
@ToString
public class SaltedgeAccountExtra {
    private String account_name;
    private String account_number;
    private List<String> assets;
    private Double available_amount;
    private String balance_type;
    private Double blocked_amount;
    private String card_type;
    private List<String> cards;
    private String client_name;
    private Double closing_balance;
    private Double credit_limit;
    private String current_date;
    private String current_time;
    private String iban;
    private String bban;
    private String interest_rate;
    private String interest_type;
    private String floating_interest_rate;
    private String remaining_payments;
    private Double penalty_amount;
    private Double next_payment_amount;
    private String next_payment_date;
    private String open_date;
    private Double opening_balance;
    private Boolean partial;
    private String raw_balance;
    private String sort_code;
    private String statement_cut_date;
    private String status;
    private String swift;
    private Double total_payment_amount;
    private SaltedgeTransactionCount transactions_count;
    private String payment_type;
    private Double cashback_amount;
    private Double monthly_total_payment;
    private Double minimum_payment;
    private String expiry_date;
}
