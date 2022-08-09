package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
public class SaltedgeField {

    private String name;
    @JsonProperty("english_name")
    private String englishName;
    @JsonProperty("localized_name")
    private String localizedName;
    private String nature;
    private int position;
    private boolean optional;
    private Object extra;
}
