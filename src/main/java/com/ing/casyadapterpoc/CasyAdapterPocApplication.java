package com.ing.casyadapterpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CasyAdapterPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasyAdapterPocApplication.class, args);
	}

}
