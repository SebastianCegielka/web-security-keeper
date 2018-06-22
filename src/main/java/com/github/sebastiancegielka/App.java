package com.github.sebastiancegielka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.github.sebastiancegielka")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Docket passApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pass")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api.*"))
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Password manager Spring REST API")
                .description("Own project of web password manager")
                .contact("Sebastian Cegielka")
                .version("2.0")
                .build();
    }
}
