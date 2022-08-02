package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Service
public class GrantActivationDelegatingService {

    private final List<GrantActivationVendorService> grantActivationVendorServices;

    public Mono<String> getLoginUrl(Vendor vendor){
        return VendorServiceSelector.selectVendorService(grantActivationVendorServices,vendor)
                .getLoginUrl();
    }
}
