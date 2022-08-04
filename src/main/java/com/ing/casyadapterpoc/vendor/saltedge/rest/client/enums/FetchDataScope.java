package com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums;

public enum FetchDataScope {
    ACCOUNTS("account_details"),
    TRANSACTIONS("holder_details"),
    HOLDER("transactions_details");
    private String value;

    FetchDataScope(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
