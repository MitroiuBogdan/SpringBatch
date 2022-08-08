package com.ing.casyadapterpoc.common.service.delegate;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import com.ing.casyadapterpoc.common.service.UserVendorService;
import com.ing.casyadapterpoc.common.service.VendorServiceSelector;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

    public Mono<ResponseEntity<Void>> deleteUser(Vendor vendor, String userId) {
        return selectVendorService(userVendorServices, vendor)
                .deleteUser(userId);
    }

    public Flux<User> getUsers(Vendor vendor) {
        return VendorServiceSelector.selectVendorService(userVendorServices, vendor)
                .getUsers();
    }

    public Mono<User> getUserById(Vendor vendor, String userId) {
        return selectVendorService(userVendorServices, vendor)
                .getUserById(userId);
    }

}
