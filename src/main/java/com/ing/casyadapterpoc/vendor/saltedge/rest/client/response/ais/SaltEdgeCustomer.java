package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class SaltEdgeCustomer {

    private String id;
    private String identifier;
    private String secret;
    private String blocked_at;
    private String created_at;
    private String updated_at;
}
