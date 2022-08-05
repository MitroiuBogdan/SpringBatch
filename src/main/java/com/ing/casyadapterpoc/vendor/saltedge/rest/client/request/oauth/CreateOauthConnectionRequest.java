package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateOauthConnectionRequest {


    private String customerId;

    @NotNull
    private String countryCode;

    @NotNull
    private String providerCode;


}
