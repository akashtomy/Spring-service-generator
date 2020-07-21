package com.corelogic.clp.starters.<%=packageName%>;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.corelogic.clp.starters.<%=packageName%>.dtos.ExampleResponse;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

public class <%=properCaseName%>ServiceClient {

    private final Logger logger = LoggerFactory.getLogger(<%=properCaseName%>ServiceClient.class);

    private final OAuth2RestTemplate restTemplate;
    private final String serviceUrl;

    public <%=properCaseName%>ServiceClient(OAuth2RestTemplate restTemplate, String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public ExampleResponse helloWorld() throws IOException {

        logger.debug("Saying Hello");

        UriComponentsBuilder serviceHelloWorldUri = UriComponentsBuilder.fromHttpUrl(serviceUrl + "/helloWorld");
        URI serviceUri = serviceHelloWorldUri.build().encode().toUri();
        return restTemplate.getForObject(serviceUri, ExampleResponse.class);
    }
}
