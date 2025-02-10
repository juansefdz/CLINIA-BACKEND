package KiwiDevSoft.clinia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.ExternalDocumentation;

@OpenAPIDefinition(
    info = @Info(
        title = "API de Clinia",
        version = "1.0",
        description = "Modern and intuitive medical scheduling system",
        license = @License(name = "KiwiDevSoft"),
        contact = @Contact(name = "KiwiDevsoft", url = "https://kiwidevsoft.dev/")
    ),
    externalDocs = @ExternalDocumentation(
        description = "software developers: Juan Sebastián Fernández, Jhonatan Toro",
        url = "https://kiwidevosft.dev/"
    )
)
public class SwaggerConfig {
}
