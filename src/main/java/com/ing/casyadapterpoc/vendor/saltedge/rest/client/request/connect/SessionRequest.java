package com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.connect;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionRequest {

    String providerGrantId;
    String providerUserId;
    String aspspCode;
    String returnBackUrl;
    String activationDate;
    String expirationDate;
    Boolean dailyRefresh;
}
