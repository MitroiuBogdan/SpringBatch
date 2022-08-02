package com.ing.casyadapterpoc.vendor.saltedge.domain.response.ais;

import com.ing.casyadapterpoc.vendor.saltedge.domain.response.SaltEdgeListResponse;

import java.util.List;

public class SaltedgeTransactionResponse extends SaltEdgeListResponse<SaltedgeTransaction> {
    public SaltedgeTransactionResponse(List<SaltedgeTransaction> data) {
        super(data);
    }

    public SaltedgeTransactionResponse() {
        super();
    }
}
