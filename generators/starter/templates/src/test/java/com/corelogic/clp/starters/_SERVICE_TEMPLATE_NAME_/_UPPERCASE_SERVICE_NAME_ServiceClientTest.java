package com.corelogic.clp.starters.<%=packageName%>;

import com.corelogic.clp.starters.<%=packageName%>.dtos.ExampleResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class <%=properCaseName%>ServiceClientTest {

    @Mock
    private OAuth2RestTemplate restTemplateMock;

    private <%=properCaseName%>ServiceClient <%=camelCaseName%>ServiceClient;

    private static final String SERVICE_URL = "http://localhost:8084";

    @Before
    public void setUp() {
        <%=camelCaseName%>ServiceClient = new <%=properCaseName%>ServiceClient(restTemplateMock, SERVICE_URL);
    }

    @Test
    public void helloWorld_returnsExampleResponse() throws IOException {
        ExampleResponse exampleResponse = new ExampleResponse();
        exampleResponse.setMessage("Hello World");
        URI uri = UriComponentsBuilder.fromHttpUrl(SERVICE_URL + "/helloWorld").build().encode().toUri();;
        given(restTemplateMock.getForObject(uri, ExampleResponse.class)).willReturn(exampleResponse);

        ExampleResponse response = <%=camelCaseName%>ServiceClient.helloWorld();
        assert(response.getMessage().equals("Hello World"));
    }
}
