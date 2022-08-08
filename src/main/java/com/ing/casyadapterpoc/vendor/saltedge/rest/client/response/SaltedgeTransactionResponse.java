package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeTransaction;

import java.util.List;

public class SaltedgeTransactionResponse extends SaltEdgeListResponse<SaltedgeTransaction> {
    public SaltedgeTransactionResponse(List<SaltedgeTransaction> data) {
        super(data);
    }

    public SaltedgeTransactionResponse() {
        super();
    }
}
