package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.service.VendorServiceSelector.selectVendorService;

@AllArgsConstructor
@Service
public class GrantActivationDelegatingService {

    private final List<GrantActivationVendorService> grantActivationVendorServices;

    public Mono<String> getLoginUrl(Vendor vendor){
        return selectVendorService(grantActivationVendorServices,vendor)
                .getLoginUrl();
    }
}
