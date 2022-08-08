package com.ing.casyadapterpoc.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "common")
@Data
public class CommonProperties {

    String saltedgeAccountsPath;
    String saltedgeTrxPath;

    String tinkAccountPath;
    String tinkTransactionPath;
    String tinkGrantPath;

}
