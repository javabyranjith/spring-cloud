package jbr.sb.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SbMsUserWishlistApplication {

  public static void main(String[] args) {
    SpringApplication.run(SbMsUserWishlistApplication.class, args);
  }

}
