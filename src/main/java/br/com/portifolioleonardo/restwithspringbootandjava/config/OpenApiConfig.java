package br.com.portifolioleonardo.restwithspringbootandjava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTFul API with Java and Spring Boot 3")
                        .version("v1")
                        .description("API developed from the Udemy course to improve my knowledge in Java and Spring Boot")
                        .termsOfService("".toString())
                        .license(new License()
                                    .name("Apache 2.0")
                                    .url("https://www.udemy.com/course/restful-apis-do-0-a-nuvem-com-springboot-e-docker/")));
    }
}
