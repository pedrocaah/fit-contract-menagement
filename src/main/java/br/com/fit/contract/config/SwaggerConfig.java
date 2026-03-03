package br.com.fit.contract.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI contractManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Contract Management API")
                        .description("API responsável pelo gerenciamento de contratos")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Pedro Oliveira")
                                .email("pdrhenriqe@outlook.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório do projeto")
                        .url("https://github.com/pedrocaah/fit-contract-menagement"));
    }
}