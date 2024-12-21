package com.backend.studycenter.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "REST API",
                version = "1.0",
                description = "REST API description...",
                contact = @Contact(name = "Name Surname")),
                security = {@SecurityRequirement(name = "UnlimIT-API")}
)
@SecuritySchemes({
        @SecurityScheme(
                name = "UnlimIT-API",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT",
                in= SecuritySchemeIn.HEADER
        )
})
public class SwaggerConfig {
}
