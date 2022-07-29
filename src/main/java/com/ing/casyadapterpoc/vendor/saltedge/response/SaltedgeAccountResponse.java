package com.ing.casyadapterpoc.vendor.saltedge.response;

import java.util.List;

public class SaltedgeAccountResponse extends SaltEdgeListResponse<SaltedgeAccount>{

    public SaltedgeAccountResponse(List<SaltedgeAccount> data) {
        super(data);
    }

    public SaltedgeAccountResponse() {
        super();
    }
}
