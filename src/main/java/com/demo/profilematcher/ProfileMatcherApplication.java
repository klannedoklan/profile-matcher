package com.demo.profilematcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProfileMatcherApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProfileMatcherApplication.class, args);
  }

}
