package com.fitscorp.j2eemobileapi.restservices.restservices.handlers;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private int status;
    private String body;
    private List<String> errorMessages;

    public ApiError() {
        super();
    }

    public ApiError(final int status, final String body, final List<String> errorMessages) {
        super();
        this.status = status;
        this.body = body;
        this.errorMessages = errorMessages;
    }

    public ApiError(final int status, final String message, final String errorMessage) {
        super();
        this.status = status;
        this.body = message;
        errorMessages = Arrays.asList(errorMessage);
    }

    //

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(final List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setErrorMessage(final String errorMessage) {
    	errorMessages = Arrays.asList(errorMessage);
    }

}