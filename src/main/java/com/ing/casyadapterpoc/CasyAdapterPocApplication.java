package com.ing.casyadapterpoc;

import com.ing.casyadapterpoc.vendor.saltedge.batch.SaltEdgeRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties
public class CasyAdapterPocApplication implements CommandLineRunner {

    @Autowired
    SaltEdgeRefreshService saltEdgeRefreshService;


    public static void main(String[] args) {
        SpringApplication.run(CasyAdapterPocApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        saltEdgeRefreshService.startDataFetching("0000000001");
//        saltEdgeRefreshService.startDataFetching("0000000002");
//        saltEdgeRefreshService.startDataFetching("0000000003");
//        saltEdgeRefreshService.startDataFetching("0000000004");

    }
}
