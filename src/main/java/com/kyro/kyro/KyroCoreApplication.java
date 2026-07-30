package com.kyro.kyro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KyroCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyroCoreApplication.class, args);
	}

}
