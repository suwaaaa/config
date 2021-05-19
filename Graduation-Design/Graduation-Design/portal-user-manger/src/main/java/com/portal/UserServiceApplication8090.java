package com.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.portal.mapper")
public class UserServiceApplication8090 {
  public static void main(String[] args) {
      SpringApplication.run(UserServiceApplication8090.class,args);
  }
}
