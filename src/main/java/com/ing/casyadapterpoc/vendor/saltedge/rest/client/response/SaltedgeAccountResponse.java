package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeAccount;

import java.util.List;

public class SaltedgeAccountResponse extends SaltEdgeListResponse<SaltedgeAccount> {

    public SaltedgeAccountResponse(List<SaltedgeAccount> data) {
        super(data);
    }

    public SaltedgeAccountResponse() {
        super();
    }
}
