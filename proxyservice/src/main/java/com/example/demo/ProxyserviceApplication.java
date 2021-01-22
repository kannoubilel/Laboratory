package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.config.WebConfigurer;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
public class ProxyserviceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProxyserviceApplication.class, args);
	}
	

}
