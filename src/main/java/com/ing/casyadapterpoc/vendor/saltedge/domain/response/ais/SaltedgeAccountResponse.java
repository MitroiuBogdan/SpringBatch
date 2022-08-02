package com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais;

import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeListResponse;

import java.util.List;

public class SaltedgeAccountResponse extends SaltEdgeListResponse<SaltedgeAccount> {

    public SaltedgeAccountResponse(List<SaltedgeAccount> data) {
        super(data);
    }

    public SaltedgeAccountResponse() {
        super();
    }
}
