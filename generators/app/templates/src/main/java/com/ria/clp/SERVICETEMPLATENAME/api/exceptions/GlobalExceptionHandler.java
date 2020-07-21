package com.corelogic.clp.<%=packageName%>.api.exceptions;

import com.corelogic.clp.<%=packageName%>.api.dtos.ErrorMessage;
import com.corelogic.clp.<%=packageName%>.api.dtos.ErrorResponse;
import com.corelogic.clp.starters.chargeback.ChargeBackServiceClient;
import com.corelogic.clp.starters.chargeback.dtos.ChargeBackRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String THERE_WAS_AN_ERROR_WITH_YOUR_REQUEST = "There was an error with your request. Please check your parameters and resubmit.";

    private ChargeBackServiceClient chargeBackServiceClient;

    @Autowired
    public GlobalExceptionHandler(ChargeBackServiceClient chargeBackServiceClient) {
        this.chargeBackServiceClient = chargeBackServiceClient;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingServletRequestParameterException(HttpServletRequest req, MissingServletRequestParameterException missingServletRequestParameterException) {
        LOGGER.error("A missing servlet request parameter error occurred: {}", missingServletRequestParameterException);

        ErrorResponse errorResponseDto = new ErrorResponse();
        errorResponseDto.setMessage(String.format("Your request is missing the << %s, %s >> parameter. Please check all parameters and resubmit.", missingServletRequestParameterException.getParameterName(), missingServletRequestParameterException.getParameterType()));
        saveChargeBack(req, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException exception) {
        LOGGER.error("A constraint violation exception error occurred: {}", exception);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(THERE_WAS_AN_ERROR_WITH_YOUR_REQUEST);

        saveChargeBack(req, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(HttpServletRequest req, MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        LOGGER.error("An argument type mismatch error occurred: {}", methodArgumentTypeMismatchException);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(THERE_WAS_AN_ERROR_WITH_YOUR_REQUEST);

        saveChargeBack(req, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException methodArgumentNotValidException) {
        LOGGER.error("An argument validation error occurred: {}", methodArgumentNotValidException);

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<ErrorMessage> errors = fieldErrors.stream()
            .map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
            .sorted(Comparator.comparing(ErrorMessage::getField))
            .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(THERE_WAS_AN_ERROR_WITH_YOUR_REQUEST);
        errorResponse.setErrors(errors);

        saveChargeBack(req, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private void saveChargeBack(HttpServletRequest req, HttpStatus httpStatus) {
        String uri = req.getRequestURI();
        String endpoint = uri;
        final String NO_CLIENT_ID = "NO CLIENT ID";
        final String SERVICE_NAME = "<%=fullServiceName%>";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String clientId = auth == null ? NO_CLIENT_ID : ((OAuth2Authentication) auth).getOAuth2Request().getClientId();

        ChargeBackRequest chargeBackRequest = ChargeBackRequest.builder()
                .service(SERVICE_NAME)
                .endpoint(endpoint)
                .httpStatus(httpStatus.value())
                .clientId(clientId)
                .build();

        chargeBackServiceClient.chargeBack(chargeBackRequest);
    }
}
