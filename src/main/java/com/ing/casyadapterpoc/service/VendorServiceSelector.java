package com.ing.casyadapterpoc.service;

import com.ing.casyadapterpoc.domain.Vendor;

import java.util.List;

public class VendorServiceSelector {

    public static <T extends VendorService> T selectVendorService(List<T> vendorServices, Vendor vendor){
        return vendorServices.stream()
                .filter(service -> service.isForVendor(vendor))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vendor service for not implemented for " + vendor));
    }
}
