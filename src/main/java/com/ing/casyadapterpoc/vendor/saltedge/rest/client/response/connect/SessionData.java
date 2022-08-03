package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.connect;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionData {
    @NotNull
    @JsonProperty("connect_url")
    String connectUrl;
//    TODO: to be changed to ZoneDateTime -> fix jackson dependency
    @NotNull
    @JsonProperty("expires_at")
    String expiresAt;

}
