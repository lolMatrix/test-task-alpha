package com.matrix.currencytogif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencytogifApplication {

	public static void main(String[] args) {
		for (String arg :
				args) {
			System.out.println(arg);
		}
		SpringApplication.run(CurrencytogifApplication.class, args);
	}

}
