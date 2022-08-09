package com.ing.casyadapterpoc.vendor.saltedge.mapper;

import com.ing.casyadapterpoc.common.domain.casy_entity.Aspsp;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais.SaltedgeProvider;
import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaltedgeAspspMapper implements Function<SaltedgeProvider, Aspsp> {

    public static final SaltedgeAspspMapper SALTEDGE_ASPSP_MAPPER = new SaltedgeAspspMapper();

    @Override
    public Aspsp apply(SaltedgeProvider saltedgeProvider) {
        return Aspsp.builder()
                .providerAspspId(saltedgeProvider.getId())
                .name(saltedgeProvider.getName())
                .icon(saltedgeProvider.getLogoUrl())
                .additionalInfoNeeded(List.of(saltedgeProvider.getRequiredFields(), saltedgeProvider.getInteractiveFields()).toString())
                .build();
    }
}
