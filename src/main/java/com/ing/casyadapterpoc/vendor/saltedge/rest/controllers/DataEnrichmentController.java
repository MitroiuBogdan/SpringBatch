package com.ing.casyadapterpoc.vendor.saltedge.rest.controllers;

import com.ing.casyadapterpoc.vendor.saltedge.rest.client.SaltEdgeClientImpl;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.request.enrichment.MerchantsInfoRequest;
import com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.enrichment.MerchantInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.ing.casyadapterpoc.common.logging.LoggingHelper.buildLogMessage;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/SALTEDGE/data-enrichment")
public class DataEnrichmentController {

    private final SaltEdgeClientImpl saltEdgeClient;

    @PostMapping("/merchants")
    public Flux<MerchantInfo> getMerchantsInfo(@RequestBody MerchantsInfoRequest merchantsInfoRequest) {
        log.info("getMerchantsInfo - getting merchants information's {}", merchantsInfoRequest.getData());
        return saltEdgeClient.getMerchantsInfo(merchantsInfoRequest)
                .doOnNext(merchantInfo -> log.info(buildLogMessage(merchantInfo)));
    }

}
