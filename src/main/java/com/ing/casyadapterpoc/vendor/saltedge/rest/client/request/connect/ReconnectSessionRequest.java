package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReconnectSessionRequest {

    @NotNull
    @JsonProperty("providerGrantId")
    String providerGrantId;

    @NotNull
    @JsonProperty("providerUserId")
    String providerUserId;

    @NotNull
    @JsonProperty("providerCode")
    String providerCode;

    @JsonProperty("activationDate")
    String activationDate;

    @JsonProperty("expirationDate")
    String expirationDate;

    @JsonProperty("consentValidityDays")
    Integer consentValidityDays;

    @JsonProperty("dailyRefresh")
    boolean dailyRefresh;

    @NotNull
    @JsonProperty("scopes")
    List<String> scopes;

    @NotNull
    @JsonProperty("returnBackUrl")
    String returnBackUrl;
}
