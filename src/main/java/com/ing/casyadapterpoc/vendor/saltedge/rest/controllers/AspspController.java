package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;

import com.ing.casyadapterpoc.common.domain.casy_entity.Aspsp;
import com.ing.casyadapterpoc.vendor.saltedge.service.SaltedgeAspspService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/saltedge/aspsps")
@AllArgsConstructor
public class AspspController {

    private final SaltedgeAspspService saltedgeAspspService;

    @GetMapping("/{providerCode}")
    public Mono<Aspsp> getByProviderCode(@PathVariable String providerCode){
        log.info("getById - getting aspsp by provider code {}", providerCode);
        return saltedgeAspspService.getByProviderCode(providerCode);
    }

    @GetMapping
    public Flux<Aspsp> getAspsps(){
        log.info("getAspsps - getting aspsps");
        return saltedgeAspspService.getAspsps();
    }
}
