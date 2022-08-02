package com.ing.casyadapterpoc.vendor.saltedge.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaltEdgeAttempt {

    @JsonProperty("return_to")
    String returnTo;
}
