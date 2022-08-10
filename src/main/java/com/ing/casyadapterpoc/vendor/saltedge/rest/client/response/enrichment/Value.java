package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Value {

    @JsonProperty("mode")
    String type;
    @JsonProperty("value")
    String value;
}
