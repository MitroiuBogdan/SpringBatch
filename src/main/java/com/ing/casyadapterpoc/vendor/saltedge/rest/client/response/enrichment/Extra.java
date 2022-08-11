package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Extra {

    @JsonProperty("building_number")
    String buildingNumber;
    @JsonProperty("shop_number")
    String shopNumber;
    @JsonProperty("type_of_shop")
    String typeOfShop;
}
