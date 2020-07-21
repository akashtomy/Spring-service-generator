package com.corelogic.clp.<%=packageName%>.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "<%=fullServiceName%>";
    private static final String OAUTH2_EXPRESSION = "#oauth2.clientHasAnyRole('<%=fullServiceName%>.application')";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/health",
                        "/info",
                        "/v2/api-docs",
                        "/swagger-ui*.*",
                        "/styles.css",
                        "/*.png",
                        "/index.html").permitAll()
                .anyRequest().access(OAUTH2_EXPRESSION);
    }
}

