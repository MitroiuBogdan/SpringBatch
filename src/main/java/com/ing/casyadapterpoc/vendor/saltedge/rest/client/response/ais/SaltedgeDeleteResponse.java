package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SaltedgeDeleteResponse {

    private boolean removed;
    @JsonProperty("id")
    private String connectionId;
}
