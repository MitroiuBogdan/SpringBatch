package com.ing.casyadapterpoc.vendor.saltedge.rest.client.enums;

import java.util.HashMap;
import java.util.Map;

public enum FetchDataScope {
    ACCOUNTS("account_details"),
    TRANSACTIONS("holder_details"),
    HOLDER("transactions_details");

    public static final Map<String, String> valueByScope;
    private final String scopeValue;

    static {
        valueByScope = new HashMap<>();
        for (FetchDataScope value : FetchDataScope.values()) {
            valueByScope.put(value.getScopeValue(), value.toString());
        }
    }

    FetchDataScope(String value) {
        this.scopeValue = value;
    }

    public String getScopeValue() {
        return scopeValue;
    }

}
