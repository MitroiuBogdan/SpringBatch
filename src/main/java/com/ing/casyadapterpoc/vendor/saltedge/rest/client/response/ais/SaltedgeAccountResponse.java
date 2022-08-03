package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeListResponse;

import java.util.List;

public class SaltedgeAccountResponse extends SaltEdgeListResponse<SaltedgeAccount> {

    public SaltedgeAccountResponse(List<SaltedgeAccount> data) {
        super(data);
    }

    public SaltedgeAccountResponse() {
        super();
    }
}
