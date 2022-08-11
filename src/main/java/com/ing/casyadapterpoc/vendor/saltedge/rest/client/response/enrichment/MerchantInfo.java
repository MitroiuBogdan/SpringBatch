package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MerchantInfo {

    @JsonProperty("id")
    String merchantId;
    @JsonProperty("names")
    List<Value> names;
    @JsonProperty("contact")
    List<Value> contacts;
    @JsonProperty("address")
    Address address;
    @JsonProperty("extra")
    Extra extra;
}
