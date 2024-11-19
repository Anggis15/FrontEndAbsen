package com.example.frontAbsen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FrontAbsenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontAbsenApplication.class, args);
	}

}
