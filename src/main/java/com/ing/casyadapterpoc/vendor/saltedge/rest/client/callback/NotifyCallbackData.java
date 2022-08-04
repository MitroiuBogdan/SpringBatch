package com.ing.casyadapterpoc.vendor.saltedge.rest.client.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotifyCallbackData {

    @JsonProperty("connection_id")
    private String connectionId;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("stage")
    private String stage;
}
