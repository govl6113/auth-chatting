package com.dankook.authchatting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI swaggerApi() {
        return new OpenAPI()
                // Bearer 추가
                .components(new Components()
                                    .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                            .name(SECURITY_SCHEME_NAME)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("Bearer")
                                            .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
    }
}