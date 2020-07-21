package com.corelogic.clp.starters.<%=packageName%>;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.mock.http.client.MockClientHttpRequest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class <%=properCaseName%>ServiceClientHttpRequestInterceptorTest {
    @Mock
    ClientHttpRequestExecution clientHttpRequestExecution;

    @Captor
    private ArgumentCaptor<HttpRequest> captor;

    @Test
    public void intercept_addsUserAgentRequestHeader() throws IOException {
        <%=properCaseName%>ServiceClientHttpRequestInterceptor interceptor = new <%=properCaseName%>ServiceClientHttpRequestInterceptor("1.0", "applicationName");
        byte[] body = "body".getBytes();

        interceptor.intercept(new MockClientHttpRequest(), body, clientHttpRequestExecution);

        verify(clientHttpRequestExecution).execute(captor.capture(), eq(body));

        List<String> userAgentHeader = captor.getValue().getHeaders().get("User-Agent");
        assertThat(userAgentHeader.get(0)).isEqualTo("<%=properCaseName%>ServiceStarter/1.0 (applicationName)");
    }
}