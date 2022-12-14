package com.ing.casyadapterpoc;

import com.ing.casyadapterpoc.vendor.saltedge.batch.SaltEdgeRefreshService;
import com.ing.casyadapterpoc.vendor.saltedge.batch.model.Account;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.function.Consumer;

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
        saltEdgeRefreshService.startDataFetching("000000000000001");


    }

    private void accountConsumer(Account account2) {
        System.out.println("TRUE");
        if (account2.getId().equals("ema")) {
            System.out.println("TRUE");
            account2.setName("1");
        }
    }


}

