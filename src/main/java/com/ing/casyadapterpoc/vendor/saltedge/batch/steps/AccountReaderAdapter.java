package com.ing.casyadapterpoc.vendor.saltedge.batch.steps;

import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import com.ing.casyadapterpoc.vendor.saltedge.batch.service.AccountService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class AccountReaderAdapter implements InitializingBean {

    private final AccountService accountService;
    private LinkedList<Account> sltAccounts;

    public AccountReaderAdapter(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        sltAccounts = accountService.getAccounts();
    }

    public Account getNextAccount() {
        if (!sltAccounts.isEmpty()) {
            return sltAccounts.pollFirst();
        } else {
            return null;
        }
    }

}
