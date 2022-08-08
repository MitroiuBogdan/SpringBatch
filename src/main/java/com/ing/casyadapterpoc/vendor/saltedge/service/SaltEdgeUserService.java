package com.ing.casyadapterpoc.vendor.saltedge.service;

import com.ing.casyadapterpoc.common.domain.Vendor;
import com.ing.casyadapterpoc.common.domain.casy_entity.User;
import com.ing.casyadapterpoc.common.service.UserVendorService;
import com.ing.casyadapterpoc.vendor.saltedge.mapper.SaltedgeUserMapper;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.CreateCustomerRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.SaltEdgeRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.SaltEdgeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SaltEdgeUserService implements UserVendorService {

    private final SaltEdgeClientImpl saltEdgeClient;

    private static final Vendor VENDOR = Vendor.SALTEDGE;

    @Override
    public boolean isForVendor(Vendor vendor) {
        return VENDOR.equals(vendor);
    }

    public Mono<User> createUser(String identifier) {
        SaltEdgeRequest request = SaltEdgeRequest.builder()
                .data(CreateCustomerRequest.builder()
                        .identifier(identifier)
                        .build())
                .build();

        return saltEdgeClient.createCustomer(request)
                .map(SaltEdgeResponse::getData)
                .map(SaltedgeUserMapper.SALTEDGE_USER_MAPPER);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUser(String userId) {
        return saltEdgeClient.deleteCustomer(userId);
    }

    @Override
    public Flux<User> getUsers() {
        return saltEdgeClient.getAllCustomers()
                .map(SaltedgeUserMapper.SALTEDGE_USER_MAPPER);
    }

    @Override
    public Mono<User> getUserById(String userId) {
        return saltEdgeClient.getCustomerById(userId)
                .map(SaltEdgeResponse::getData)
                .map(SaltedgeUserMapper.SALTEDGE_USER_MAPPER);
    }
}
