package com.corelogic.clp.starters.<%=packageName%>;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@ConditionalOnMissingBean(name = "<%=camelCaseName%>ServiceClientCredentialsResourceDetails")
public class <%=properCaseName%>ServiceClientCredentialsResourceDetails extends ClientCredentialsResourceDetails {

    @NotNull
    @Size(min = 1, message = "clpservices.oauth2.client.client-id must not be empty")
    private String clientId;
    @NotNull
    @Size(min = 1, message = "clpservices.oauth2.client.client-secret must not be empty")
    private String clientSecret;
    @NotNull
    @Size(min = 1, message = "clpservices.oauth2.client.access-token-uri must not be empty")
    private String accessTokenUri;

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
        super.setClientId(this.clientId);
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        super.setClientSecret(this.clientSecret);
    }

    @Override
    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
        super.setAccessTokenUri(this.accessTokenUri);
    }
}