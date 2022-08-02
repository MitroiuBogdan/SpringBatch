package com.ing.casyadapterpoc.common.controller.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AccountResponse {

    private String providerAccountId;
    private String lastRefreshTimestamp;
    private String lastRefreshTimestampProvider;
    private String iban;
    private String bban;
    private String bic;
    private String clientId;
    private String name;
    private String product;
    private String cashAccountType;
    private String currency;
    private String pan;
    private String maskedPan;
    private String usage;
    private String accountStatus;
    private String linkedAccount;
    private String accountHolderName;
    private BigDecimal availableCredit;
    private BigDecimal creditLimit;
    private String aspspAccountId;
    private String userId;
    private List<String> balances = new ArrayList<>();
    private transient String providerGrantId;
}
