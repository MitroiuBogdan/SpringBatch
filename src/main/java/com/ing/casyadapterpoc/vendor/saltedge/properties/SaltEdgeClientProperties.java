package com.ing.casyadapterpoc.vendor.saltedge.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "saltedge.client")
@Data
public class SaltEdgeClientProperties {

    String applicationId;
    String secret;
    String host;
}
