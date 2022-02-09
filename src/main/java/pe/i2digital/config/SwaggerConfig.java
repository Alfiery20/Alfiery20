/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.config;

import java.util.ArrayList;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author alfie
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("appat-api-1.0")
                .apiInfo(getApiInfo())
                .produces(Set.of(MediaType.APPLICATION_JSON_VALUE))
                //.consumes(Set.of(MediaType.APPLICATION_JSON_VALUE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("p2.i2digital.app"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    public ApiInfo getApiInfo(){
        return new ApiInfo("Documentacion del REST API - APP-AT",
                "Documentacion del API REST de los recursos del backend de la plataforma APP-AT",
                "1.0",
                "urn:tos",
                new Contact("Omar Saavedra Salazar", "i2digital.pe", "osaavedra@unprg.edu.pe"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
