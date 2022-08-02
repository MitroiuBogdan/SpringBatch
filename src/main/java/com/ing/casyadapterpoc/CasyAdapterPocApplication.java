package com.ing.casyadapterpoc;

import com.ing.casyadapterpoc.vendor.saltedge.properties.SaltEdgeClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties({SaltEdgeClientProperties.class})
public class CasyAdapterPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasyAdapterPocApplication.class, args);
    }

}
