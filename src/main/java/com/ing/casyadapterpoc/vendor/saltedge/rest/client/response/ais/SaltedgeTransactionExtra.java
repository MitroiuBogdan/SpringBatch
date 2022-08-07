package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class SaltedgeTransactionExtra {
    private String account_balance_snapshot;
    private Double account_number;
    private String additional;
    private Double asset_amount;
    private String asset_code;
    private Double categorization_confidence;
    private String check_number;
    private Double closing_balance;
    private String constant_code;
    private Boolean convert;
    private String customer_category_code;
    private String customer_category_name;
    private Double exchange_rate;
    private String id;
    private String end_to_end_id;
    private String information;
    private String mcc;
    private String merchant_id;
    private Double opening_balance;
    private Double installment_debt_amount;
    private Double original_amount;
    private String original_category;
    private String original_currency_code;
    private String original_subcategory;
    private String payee;
    private String payee_information;
    private String payer;
    private String payer_information;
    private Boolean possible_duplicate;
    private String posting_date;
    private String posting_time;
    private String record_number;
    private String specific_code;
    private List<String> tags;
    private String time;
    private String transfer_account_name;
    private String type;
    private Double unit_price;
    private Double units;
    private String variable_code;

}
