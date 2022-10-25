package com.ing.casyadapterpoc.vendor.saltedge.batch.model;

public class AccountData {

    String id;

    public void setId(String id) {
        this.id = id;
    }

    public AccountData(String id) {
//        throw new RuntimeException("das");
        this.id = id;
    }
    public String getId() {
        return id;
    }

}
