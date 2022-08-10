package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @JsonProperty("country_code")
    String countryCode;
    @JsonProperty("city")
    String city;
    @JsonProperty("street")
    String street;
    @JsonProperty("post_code")
    String postCode;
    @JsonProperty("transliterated_dity")
    String transliteratedCity;
    @JsonProperty("transliterated_street")
    String transliteratedStreet;
    @JsonProperty("coordinates")
    Coordinates coordinates;

}
