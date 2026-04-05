package com.jobApplication.Jobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobserviceApplication.class, args);
	}

}
