package com.omega.currencyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.omega")
public class CurrencyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApiApplication.class, args);
	}
}
