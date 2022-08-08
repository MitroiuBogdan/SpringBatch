package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@ToString
public class SaltedgeTransactionCount {

    private Long posted;
    private Long pending;
}
