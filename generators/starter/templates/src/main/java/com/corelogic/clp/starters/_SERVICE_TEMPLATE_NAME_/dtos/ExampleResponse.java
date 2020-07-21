package com.corelogic.clp.starters.<%=packageName%>.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ExampleResponse {

    // TODO: Refactor this DTO to be properly named and use it as an Example.
    // TODO: Modify String message to desired value or add required fields.

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
