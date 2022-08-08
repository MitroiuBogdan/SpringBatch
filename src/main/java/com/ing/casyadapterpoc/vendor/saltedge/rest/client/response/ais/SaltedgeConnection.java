package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
public class SaltedgeConnection {

    private String id;
    private String secret;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("provider_code")
    private String providerCode;
    @JsonProperty("provider_name")
    private String providerName;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private String status;
    private String categorization;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("last_success_at")
    private String lastSuccessAt;
    @JsonProperty("last_consent_id")
    private String lastConsentId;
    @JsonProperty("last_attempt")
    private SaltedgeAttempt lastAttempt;
}
