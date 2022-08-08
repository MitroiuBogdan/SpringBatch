package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltEdgeCustomer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeUserMapper implements Function<SaltEdgeCustomer, User> {

    public static final SaltedgeUserMapper SALTEDGE_USER_MAPPER = new SaltedgeUserMapper();

    @Override
    public User apply(SaltEdgeCustomer saltEdgeCustomerData) {
        return User.builder()
                .id(saltEdgeCustomerData.getId())
                .build();
    }
}
