package com.ing.casyadapterpoc.common.service;

import reactor.core.publisher.Mono;

public interface GrantActivationVendorService extends VendorService{

    Mono<String> getLoginUrl();
}
