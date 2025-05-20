package com.celalgundogdu.couriertracking.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Celal Gündoğdu",
                        url = "https://github.com/celalgundogdu"
                ),
                description = "OpenApi documentation for Courier Tracking Service",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {

}
