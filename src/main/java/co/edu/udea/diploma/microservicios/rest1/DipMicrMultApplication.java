package co.edu.udea.diploma.microservicios.rest1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DipMicrMultApplication {

	public static void main(String[] args) {
		SpringApplication.run(DipMicrMultApplication.class, args);
	}

}
