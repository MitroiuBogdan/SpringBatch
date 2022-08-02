package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.common.service.VendorServiceSelector.selectVendorService;

@AllArgsConstructor
@Service
public class UserDelegatingService {

    private final List<UserVendorService> userVendorServices;

    public Mono<String> createUser(Vendor vendor){
        return selectVendorService(userVendorServices, vendor)
                .createUser();
    }

    public Mono<Void> deleteUser(Vendor vendor, String userId){
        return selectVendorService(userVendorServices,vendor)
                .deleteUser(userId);
    }

}
