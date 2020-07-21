package com.corelogic.clp.starters.<%=packageName%>;

import com.corelogic.clp.starters.<%=packageName%>.dtos.ErrorResponse;
import com.corelogic.clp.starters.<%=packageName%>.exceptions.<%=properCaseName%>ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;

public class ErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) {
        try {
            super.handleError(response);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            String rawMessage = getResponseBodyAsString(e);
            int statusCode = e.getStatusCode().value();
            throw new <%=properCaseName%>ServiceException(statusCode, rawMessage);
        } catch (Exception e) {
            throw new <%=properCaseName%>ServiceException(e);
        }
    }

    private String getResponseBodyAsString(HttpStatusCodeException e) {
        try {
            return new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class).toString();
        } catch (IOException ex) {
            throw new <%=properCaseName%>ServiceException(e);
        }
    }
}
