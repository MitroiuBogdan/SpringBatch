package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaltedgeResponseMetaData {
    @JsonProperty("next_id")
    private int nextId;
    @JsonProperty("next_page")
    private String nextPage;
}
