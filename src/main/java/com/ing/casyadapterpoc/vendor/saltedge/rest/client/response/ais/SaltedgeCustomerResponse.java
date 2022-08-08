package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeListResponse;

import java.util.List;

public class SaltedgeCustomerResponse extends SaltEdgeListResponse<SaltEdgeCustomer> {
    public SaltedgeCustomerResponse(List<SaltEdgeCustomer> data) {
        super(data);
    }

    public SaltedgeCustomerResponse() {
        super();
    }
}
