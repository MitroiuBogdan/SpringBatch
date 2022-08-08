package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @JsonProperty("identifier")
    String identifier;

}
