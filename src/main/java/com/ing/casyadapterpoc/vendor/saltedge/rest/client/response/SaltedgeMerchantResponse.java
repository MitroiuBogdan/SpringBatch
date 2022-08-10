package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment.MerchantInfo;

import java.util.List;

public class SaltedgeMerchantResponse extends SaltEdgeListResponse<MerchantInfo> {
    public SaltedgeMerchantResponse(List<MerchantInfo> data) {
        super(data);
    }

    public SaltedgeMerchantResponse() {
        super();
    }
}
