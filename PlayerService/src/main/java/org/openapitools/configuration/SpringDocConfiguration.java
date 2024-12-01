package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Game Service API")
                                .description("This API manages player profiles, tracks in-game progress, awards achievements based on player milestones, and sends notifications.")
                                .contact(
                                        new Contact()
                                                .name("Your Name")
                                                .url("https://www.example.com")
                                                .email("your-email@example.com")
                                )
                                .version("1.0.0")
                )
        ;
    }
}