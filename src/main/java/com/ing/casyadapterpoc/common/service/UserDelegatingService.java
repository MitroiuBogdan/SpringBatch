package com.ing.casyadapterpoc.common.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ing.casyadapterpoc.common.service.VendorServiceSelector.selectVendorService;

@AllArgsConstructor
@Service
public class UserDelegatingService {

    private final List<UserVendorService> userVendorServices;

    public Mono<User> createUser(Vendor vendor, String identifier) {
        return selectVendorService(userVendorServices, vendor)
                .createUser(identifier);
    }

    public Mono<Void> deleteUser(Vendor vendor, String userId) {
        return selectVendorService(userVendorServices, vendor)
                .deleteUser(userId);
    }

}
