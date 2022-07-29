package com.ing.casyadapterpoc.service;

import reactor.core.publisher.Mono;

public interface GrantActivationVendorService extends VendorService{

    Mono<String> getLoginUrl();
}
