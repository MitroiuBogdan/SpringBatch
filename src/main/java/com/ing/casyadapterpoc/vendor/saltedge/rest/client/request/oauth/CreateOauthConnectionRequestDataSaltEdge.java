package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeAttemptRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeConsent;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateOauthConnectionRequestDataSaltEdge {

    @NotNull
    @JsonProperty("customer_id")
    String customerId;

    @NotNull
    @JsonProperty("country_code")
    String countryCode;

    @NotNull
    @JsonProperty("provider_code")
    String providerCode;

    @NotNull
    @JsonProperty("consent")
    SaltEdgeConsent consent;

    @JsonProperty("attempt")
    SaltEdgeAttemptRequest attempt;

}
