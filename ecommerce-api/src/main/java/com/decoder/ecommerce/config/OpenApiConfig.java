package com.decoder.ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Ecommerce API",
                description = "",
                summary = "",
                termsOfService = "",
                contact = @Contact(
                        name = "ketan",
                        email = "ketansodnarva2@gmail.com"
                ),
                license = @License (
                        name = "Licence number: LN220283107038"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "dev",
                        url = "http://localhost:8080/"
                ),
                @Server(
                        description = "react",
                        url = "http://localhost:5173"
                )
        },
        security = @SecurityRequirement(
                name = "authBearer"
        ) // this will provide global level, for controller level there is another annotation, i.e. check @AdminOrderController
)
@SecurityScheme(
        name = "authBearer",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Need JWT for end points like: api/**"
)
public class OpenApiConfig {

}
