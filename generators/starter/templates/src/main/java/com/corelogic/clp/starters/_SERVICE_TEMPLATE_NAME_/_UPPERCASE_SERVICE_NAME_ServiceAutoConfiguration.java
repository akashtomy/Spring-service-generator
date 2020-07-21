package com.corelogic.clp.starters.<%=packageName%>;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.Collections;

@Configuration
@EnableConfigurationProperties
@ConditionalOnClass({OAuth2RestTemplate.class})
@ConditionalOnProperty(prefix = "<%=packageName%>service", name = "url")
@PropertySource("classpath:/config/clp<%=packageName%>servicestarter.properties")
public class <%=properCaseName%>ServiceAutoConfiguration {

    @Bean(name = "<%=camelCaseName%>ServiceClientCredentialsResourceDetails")
    @ConfigurationProperties("clpservices.oauth2.client")
    ClientCredentialsResourceDetails details() {
        return new <%=properCaseName%>ServiceClientCredentialsResourceDetails();
    }

    @Bean(name = "<%=camelCaseName%>ServiceRestTemplate")
    @ConditionalOnMissingBean(name = "<%=camelCaseName%>ServiceRestTemplate")
    OAuth2RestTemplate restTemplate(@Qualifier("<%=camelCaseName%>ServiceClientCredentialsResourceDetails") ClientCredentialsResourceDetails details,
                                    @Qualifier("<%=camelCaseName%>ServiceOAuth2ErrorHandler") OAuth2ErrorHandler oAuth2ErrorHandler,
                                    @Value("${clp-<%=lowerCaseName%>-service-starter.build.version}") String version,
                                    @Value("${spring.application.name}") String applicationName) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details, new DefaultOAuth2ClientContext(atr));
        oAuth2RestTemplate.setErrorHandler(oAuth2ErrorHandler);
        oAuth2RestTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        oAuth2RestTemplate.setInterceptors(Collections.singletonList(new <%=properCaseName%>ServiceClientHttpRequestInterceptor(version, applicationName)));

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        oAuth2RestTemplate.setRequestFactory(requestFactory);

        return oAuth2RestTemplate;
    }

    @Bean(name = "<%=camelCaseName%>ServiceOAuth2ErrorHandler")
    @ConditionalOnMissingBean(name = "<%=camelCaseName%>ServiceOAuth2ErrorHandler")
    OAuth2ErrorHandler oAuth2ErrorHandler(@Qualifier("<%=camelCaseName%>ServiceClientCredentialsResourceDetails") ClientCredentialsResourceDetails details) {
        return new OAuth2ErrorHandler(new ErrorHandler(), details);
    }

    @Bean
    @ConditionalOnMissingBean(name = "<%=camelCaseName%>Client")
    public <%=properCaseName%>ServiceClient <%=camelCaseName%>Client(@Qualifier("<%=camelCaseName%>ServiceRestTemplate") OAuth2RestTemplate <%=camelCaseName%>ServiceRestTemplate,
                                           @Value("${<%=packageName%>service.url}") String serviceUrl) {
        return new <%=properCaseName%>ServiceClient(<%=camelCaseName%>ServiceRestTemplate, serviceUrl);
    }
}

