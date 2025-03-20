package com.proa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Activar un servidor de eureka
@EnableEurekaServer
@SpringBootApplication
public class MicroserviseEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviseEurekaApplication.class, args);
	}

}
