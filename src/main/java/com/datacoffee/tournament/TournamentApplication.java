package com.datacoffee.tournament;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
/*@ComponentScans({ @ComponentScan("com.datacoffee.tournaments.controller") })
@EnableJpaRepositories("com.datacoffee.tournaments.repository")
@EntityScan("com.datacoffee.tournaments.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Tournaments microservice REST API Documentation",
				description = "DataCoffee Tournaments microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "White Mhat",
						email = "tutor@datacoffee.com",
						url = "https://www.datacoffee.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.datacoffee.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "DataCoffee Tournaments microservice REST API Documentation",
				url = "https://www.datacoffee.com/swagger-ui.html"
		)
)
public class TournamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TournamentApplication.class, args);
	}

}
