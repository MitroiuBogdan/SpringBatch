package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
public class SaltedgeAccount {
    private String id;
    private String connection_id;
    private String name;
    private String nature;
    private BigDecimal balance;
    private String currency_code;
    private SaltedgeAccountExtra extra;

    private String created_at;
    private String updated_at;
}
