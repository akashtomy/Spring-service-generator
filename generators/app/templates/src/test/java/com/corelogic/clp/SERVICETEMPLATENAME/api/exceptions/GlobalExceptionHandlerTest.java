package com.corelogic.clp.<%=packageName%>.api.exceptions;

import com.corelogic.clp.<%=packageName%>.api.dtos.ErrorResponse;
import com.corelogic.clp.starters.chargeback.ChargeBackServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.*;
import javax.validation.ConstraintViolationException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private MissingServletRequestParameterException missingServletRequestParameterException;

    @Mock
    private ConstraintViolationException constraintViolationException;

    @Mock
    private MethodArgumentTypeMismatchException methodArgumentTypeMismatchException;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private List<FieldError> fieldErrors;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ChargeBackServiceClient chargeBackServiceClient;

    private GlobalExceptionHandler globalExceptionHandler;

    @Before
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler(chargeBackServiceClient);
    }

    @Test
    public void handleMissingServletRequestParameterException_returns400WithMissingParamMessage() {
        when(missingServletRequestParameterException.getParameterName()).thenReturn("parameterName");
        when(missingServletRequestParameterException.getParameterType()).thenReturn("parameterType");
        when(request.getRequestURI()).thenReturn("/someEndpoint/");

        ResponseEntity response = globalExceptionHandler.handleMissingServletRequestParameterException(request, missingServletRequestParameterException);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getMessage()).isEqualTo("Your request is missing the << parameterName, parameterType >> parameter. Please check all parameters and resubmit.");
        assertThat(errorResponse.getErrors()).hasSize(0);
    }

    @Test
    public void handleConstraintViolationException_returns400WithMessage() {
        when(request.getRequestURI()).thenReturn("/someEndpoint");

        ResponseEntity response = globalExceptionHandler.handleConstraintViolationException(request, constraintViolationException);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getMessage()).isEqualTo("There was an error with your request. Please check your parameters and resubmit.");
        assertThat(errorResponse.getErrors()).hasSize(0);
    }

    @Test
    public void handleMethodArgumentTypeMismatchException_returns400WithMessage() {
        when(request.getRequestURI()).thenReturn("/someEndpoint");

        ResponseEntity response = globalExceptionHandler.handleMethodArgumentTypeMismatchException(request, methodArgumentTypeMismatchException);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getMessage()).isEqualTo("There was an error with your request. Please check your parameters and resubmit.");
        assertThat(errorResponse.getErrors()).hasSize(0);
    }

    @Test
    public void handleMethodArgumentNotValidException_returns422WithMessage() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(request.getRequestURI()).thenReturn("/someEndpoint");

        ResponseEntity response = globalExceptionHandler.handleMethodArgumentNotValidException(request, methodArgumentNotValidException);

        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(errorResponse.getMessage()).isEqualTo("There was an error with your request. Please check your parameters and resubmit.");
        assertThat(errorResponse.getErrors()).hasSize(0);
    }
}
