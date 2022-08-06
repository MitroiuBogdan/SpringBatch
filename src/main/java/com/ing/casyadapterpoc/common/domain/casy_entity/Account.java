package com.ing.casyadapterpoc.common.domain.casy_entity;

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
public class Account {

    public String providerAccountId;
    public String lastRefreshTimestamp;
    public String lastRefreshTimestampProvider;
    public String iban;
    public String bban;
    public String bic;
    public Transaction transaction;
    public String clientId;
    public String name;
    public String product;
    public String cashAccountType;
    public String currency;
    public String pan; //to be removed ???
    public String maskedPan;
    public String usage;
    public String accountStatus;
    public String linkedAccount;
    public String accountHolderName;
    public BigDecimal availableCredit;
    public BigDecimal creditLimit;
    public String aspspAccountId;
    public String userId;
    public List<String> balances = new ArrayList<>();
    public transient String providerGrantId;
}
