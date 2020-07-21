package com.corelogic.clp.starters.<%=packageName%>.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorMessage> errors = new ArrayList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "{" +
                "errors=" + errors.toString() +
                ", message='" + message + '\'' +
                '}';
    }
}