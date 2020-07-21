package com.corelogic.clp.<%=packageName%>.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class OAuth2Helper {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    public OAuth2AccessToken createAccessToken(final String clientId) {
        ClientDetails client = clientDetailsService.loadClientByClientId(clientId);
        Collection<GrantedAuthority> authorities = client.getAuthorities();
        Set<String> resourceIds = client.getResourceIds();
        Set<String> scopes = client.getScope();

        OAuth2Request request = new OAuth2Request(null, clientId, authorities, true, scopes,
                resourceIds, null, null, null);
        OAuth2Authentication authentication = new OAuth2Authentication(request, null);

        return tokenServices.createAccessToken(authentication);
    }
}
