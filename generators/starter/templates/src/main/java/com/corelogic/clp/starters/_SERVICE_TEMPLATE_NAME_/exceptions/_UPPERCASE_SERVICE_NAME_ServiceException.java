package com.corelogic.clp.starters.<%=packageName%>.exceptions;

public class <%=properCaseName%>ServiceException extends RuntimeException {

    private int statusCode;

    public <%=properCaseName%>ServiceException(Throwable exception, String message) {
        super(message, exception);
    }

    public <%=properCaseName%>ServiceException(Exception e) {
        super(e);
    }

    public <%=properCaseName%>ServiceException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
