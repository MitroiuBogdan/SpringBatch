package com.ing.casyadapterpoc.vendor.saltedge.domain.request.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ing.casyadapterpoc.vendor.saltedge.domain.request.SaltEdgeConsent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConnectionSessionRequest {

    @NotNull
    @JsonProperty("customer_id")
    String customerId;
    @NotNull
    @JsonProperty("consent")
    SaltEdgeConsent consent;

    @JsonProperty("attempt")
    String attempt;
    @JsonProperty("provider_code")
    String providerCode;
    @JsonProperty("daily_refresh")
    boolean dailyRefresh;

}
