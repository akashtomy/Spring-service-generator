package com.corelogic.clp.starters.<%=packageName%>;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class <%=properCaseName%>ServiceClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private String version;
    private String applicationName;

    public <%=properCaseName%>ServiceClientHttpRequestInterceptor(String version, String applicationName) {
        this.version = version;
        this.applicationName = applicationName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("User-Agent", "<%=properCaseName%>ServiceStarter/" + version + " (" + applicationName + ")");

        return execution.execute(request, body);
    }
}
