package com.corelogic.clp.<%=packageName%>.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Profile("!cloud")
@Configuration
@EnableAuthorizationServer
public class LocalAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("fake-client")
            .secret("{noop}secret")
            .authorizedGrantTypes("client_credentials")
            .authorities("<%=fullServiceName%>.application")
            .scopes("<%=fullServiceName%>.application")
            .resourceIds("<%=fullServiceName%>");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("someSymmetricKey");
        converter.setVerifierKey("someSymmetricKey");
        return converter;
    }
}
