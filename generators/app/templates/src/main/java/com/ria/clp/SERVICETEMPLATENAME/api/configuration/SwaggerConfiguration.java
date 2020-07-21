package com.corelogic.clp.<%=packageName%>.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static java.util.Collections.emptyList;
import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${clpservices.oauth2.client.access-token-uri}")
    private String authServer;

    @Value("${swagger.protocol}")
    private String protocol;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Collections.singletonList(protocol)))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.corelogic.clp.<%=packageName%>.api"))
                .paths(any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Core API - <%=simpleServiceName%>",
                "<%=simpleServiceName%> provides REST API for hello world.\n",
                "v1", "",new Contact(null, null, null),
                null, null, emptyList());
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("")
                .clientSecret("")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }


    private SecurityScheme securityScheme() {
        GrantType clientCredentials = new ClientCredentialsGrant(authServer);
        return new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(clientCredentials))
                .build();
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[] {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")
        };
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }
}
