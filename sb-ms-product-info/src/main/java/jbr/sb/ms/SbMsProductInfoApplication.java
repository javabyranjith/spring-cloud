package jbr.sb.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SbMsProductInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbMsProductInfoApplication.class, args);
	}

}
