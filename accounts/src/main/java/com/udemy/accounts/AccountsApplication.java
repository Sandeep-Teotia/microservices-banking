package com.udemy.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.udemy.accounts.dto.AccountsConfigInfoDto;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(AccountsConfigInfoDto.class)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
