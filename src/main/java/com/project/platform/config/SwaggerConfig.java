package com.project.platform.config;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme()))
                .security(Collections.singletonList(securityRequirement()))
                .info(apiInfo());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(In.HEADER).name(AUTHORIZATION);
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement().addList("bearerAuth");
    }

    private Info apiInfo() {
        return new Info()
                .title("Platform API Title")
                .description("Platform API Description")
                .version("1.0.0");
    }
}