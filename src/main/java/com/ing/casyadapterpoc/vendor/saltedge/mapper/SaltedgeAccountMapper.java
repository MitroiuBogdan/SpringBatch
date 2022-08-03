package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeAccountMapper implements Function<SaltedgeAccount, Account> {

    public static final SaltedgeAccountMapper SALTEDGE_ACCOUNT_MAPPER = new SaltedgeAccountMapper();

    @Override
    public Account apply(SaltedgeAccount saltedgeAccount) {
        return Account.builder()
                .providerAccountId(saltedgeAccount.getId())
                .bban(saltedgeAccount.getExtra().getBban())
                .iban(saltedgeAccount.getExtra().getIban())
                .currency(saltedgeAccount.getCurrency_code())
                .name(saltedgeAccount.getExtra().getAccount_name())
                .aspspAccountId(saltedgeAccount.getName())
                .balances(Collections.singletonList(saltedgeAccount.getBalance().toString()))
                .lastRefreshTimestampProvider(buildLastRefreshProvider(saltedgeAccount))
                .build();
    }

    private String buildLastRefreshProvider (SaltedgeAccount saltedgeAccount){
        return saltedgeAccount.getExtra().getCurrent_date()
                + ":"
                + saltedgeAccount.getExtra().getCurrent_time();
    }
}
