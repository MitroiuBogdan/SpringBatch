package com.ing.casyadapterpoc.domain;

import lombok.*;

import java.util.List;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    private String providerTransactionId;
    private String transactionType;
    private String bookingDate;
    private String valueDate;
    private String transactionDate;
    private String creditorName;
    private String providerAccountId;
    private String debtorName;
    private String remittanceInformationUnstructured;
    private String purposeCode;
    private String bankTransactionCode;
    private String externalId;
    private String endToEndId;
    private String status;
    private String amount;
    private List<String> exchangeRates;

}
