package com.corelogic.clp.starters.<%=packageName%>;

import com.corelogic.clp.starters.<%=packageName%>.exceptions.<%=properCaseName%>ServiceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.client.MockClientHttpResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
public class ErrorHandlerTest {

    private ErrorHandler errorHandler;
    private MockClientHttpResponse clientHttpResponseMock;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        errorHandler = new ErrorHandler();
    }

    @Test
    public void handleError_when<%=properCaseName%>ServiceRespondsWithHttpStatus5xx_throws<%=properCaseName%>ServiceException() {
        String errorResponse5xx = "{\"message\":\"some message\"}";
        clientHttpResponseMock = new MockClientHttpResponse(errorResponse5xx.getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);

        exception.expect(<%=properCaseName%>ServiceException.class);
        exception.expect(hasProperty("statusCode", is(500)));
        exception.expectMessage(equalTo("{errors=[], message='some message'}"));

        errorHandler.handleError(clientHttpResponseMock);
    }

    @Test
    public void handleError_when<%=properCaseName%>ServiceRespondsWithHttpStatus4xx_throws<%=properCaseName%>ServiceException_withAllMessages() {
        String errorResponse4xx = "{\"message\":\"some message\"}";
        clientHttpResponseMock = new MockClientHttpResponse(errorResponse4xx.getBytes(), HttpStatus.UNPROCESSABLE_ENTITY);

        exception.expect(<%=properCaseName%>ServiceException.class);
        exception.expect(hasProperty("statusCode", is(422)));
        exception.expectMessage(equalTo("{errors=[], message='some message'}"));

        errorHandler.handleError(clientHttpResponseMock);
    }

    @Test
    public void handleError_whenJsonIsMalformed_throws<%=properCaseName%>ServiceException_withMessages() {
        String errorResponse4xx = "{\"message\":\"some message\",\"errors\":[]}";
        clientHttpResponseMock = new MockClientHttpResponse(errorResponse4xx.getBytes(), HttpStatus.CONFLICT);

        exception.expect(<%=properCaseName%>ServiceException.class);
        exception.expectMessage(equalTo("{errors=[], message='some message'}"));
        exception.expect(hasProperty("statusCode", is(409)));

        errorHandler.handleError(clientHttpResponseMock);
    }

    @Test
    public void handleError_whenTheErrorResponseFrom<%=properCaseName%>ServiceGetsOtherException_throws<%=properCaseName%>ServiceExceptionWithException() {
        clientHttpResponseMock = new MockClientHttpResponse("{\"message\":\"some message\",\"errors\":[]}".getBytes(), HttpStatus.NOT_FOUND);

        exception.expect(<%=properCaseName%>ServiceException.class);
        exception.expectMessage(equalTo("{errors=[], message='some message'}"));
        exception.expect(hasProperty("statusCode", is(404)));

        errorHandler.handleError(clientHttpResponseMock);
    }
}
