package com.norseamerican.urlshortener.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;
import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.norseamerican.urlshortener"))
            .paths(Predicates.not(PathSelectors.regex("/")))
            .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Url Shortener REST API")
            .description("Url Shortener REST API")
            .contact(new Contact("PermaLost", "", "permalost@gmail.com"))
            .version("1.0.0")
            .build();
    }
}