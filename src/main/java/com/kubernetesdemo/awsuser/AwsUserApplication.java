package com.kubernetesdemo.awsuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AwsUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(AwsUserApplication.class, args);
	}

}
