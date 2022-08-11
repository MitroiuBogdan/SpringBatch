package com.ing.casyadapterpoc.common.domain.casy_entity;

import lombok.*;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    //These are all the fields consumed from Yolt and sent by YoltAdapter to CasyAPI

    private String providerTransactionId;
    private String providerAccountId;
    private String transactionType;
    private String transactionDate;
    private Amount amount;
    private String remittanceInformationUnstructured;
    private String externalId;
    private String endToEndId;
    private ExchangeRate exchangeRate;
    private String creditorName;
    private String debtorName;
    private Merchant creditor;
    private Merchant debtor;
    private String bankTransactionCode;
    private String bookingDate;
    private String purposeCode;
    private String valueDate;

    //Extra used for enrichment
    private String merchantId;

}
