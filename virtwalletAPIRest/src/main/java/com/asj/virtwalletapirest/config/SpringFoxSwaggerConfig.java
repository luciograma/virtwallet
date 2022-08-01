package com.asj.virtwalletapirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {

    //  Springfox con la notacion EnableSwagger2 genera lo que está aqui abajo
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.asj.bootcampgp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "VirtWallet",
                "api de conexion a base de datos para proyecto VirtWallet",
                "v1",
                "url de termino y servicios",
                new Contact("","","lgramaccioni@asjservicios.com"),
                "licencia",
                "Url licencia",
                Collections.emptyList()
        );
    }

}
