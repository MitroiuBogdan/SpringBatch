package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeListResponse;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltEdgeCustomer;

import java.util.List;

public class SaltedgeCustomerResponse extends SaltEdgeListResponse<SaltEdgeCustomer> {
    public SaltedgeCustomerResponse(List<SaltEdgeCustomer> data) {
        super(data);
    }

    public SaltedgeCustomerResponse() {
        super();
    }
}
