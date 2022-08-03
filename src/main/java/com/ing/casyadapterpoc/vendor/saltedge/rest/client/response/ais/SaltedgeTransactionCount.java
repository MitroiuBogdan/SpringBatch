package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class SaltedgeTransactionCount {

    private Long posted;
    private Long pending;
}
