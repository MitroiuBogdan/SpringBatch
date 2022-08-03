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
public class RefreshSessionRequest {

    @NotNull
    @JsonProperty("providerGrantId")
    String providerGrantId;

    @NotNull
    @JsonProperty("providerUserId")
    String providerUserId;

    @NotNull
    @JsonProperty("providerCode")
    String providerCode;

    @JsonProperty("dailyRefresh")
    boolean dailyRefresh;

    @NotNull
    @JsonProperty("returnBackUrl")
    String returnBackUrl;
}
