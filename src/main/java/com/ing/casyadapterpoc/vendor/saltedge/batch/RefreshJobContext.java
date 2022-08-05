package com.ing.casyadapterpoc.vendor.saltedge.batch;

import lombok.Getter;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
@JobScope
@Getter
public class RefreshJobContext {

    private String connectionId;
    private List<String> accountIdList;

    public RefreshJobContext(String connectionId) {
        this.connectionId = connectionId;
        accountIdList = new ArrayList<>();
    }

    public RefreshJobContext() {
        accountIdList = new ArrayList<>();
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public void addAccountId(String accountId) {
        this.accountIdList.add(accountId);
    }

    public void addAccounts(List<String> accountIdList) {
        this.accountIdList = accountIdList;
    }

    @PreDestroy
    public void clearContext() {
        connectionId = null;
        accountIdList.clear();
    }

}
