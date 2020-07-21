package com.corelogic.clp.starters.<%=packageName%>;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {
        "<%=packageName%>service.url=http://localhost:8084",
        "clpservices.oauth2.client.access-token-uri=https://corelogic-np-test.apigee.net/edgemicro-auth/token",
        "clpservices.oauth2.client.client-id=test-client",
        "clpservices.oauth2.client.client-secret=secret",
        "spring.application.name=<%=properCaseName%>Consumer",
        "clp-<%=lowerCaseName%>-service-starter.build.version=1.0.0"
})
public class StarterIntegrationTest {

    @Autowired
    private <%=properCaseName%>ServiceClient <%=camelCaseName%>ServiceClient;

    @Autowired
    @Qualifier("<%=camelCaseName%>ServiceRestTemplate")
    private OAuth2RestTemplate restTemplate;

    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void loadContext_autoConfiguresResourceClient() throws Exception {
        assertThat(<%=camelCaseName%>ServiceClient).isNotNull();
    }

    @Test
    public void oAuth2RestTemplate_whenSpringApplicationNameDefined_addsUserAgentHeadersWithApplicationName() throws IOException {
        server.expect(requestTo("http://localhost:8084/health"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("user-agent", "<%=properCaseName%>ServiceStarter/1.0.0 (<%=properCaseName%>Consumer)"))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
