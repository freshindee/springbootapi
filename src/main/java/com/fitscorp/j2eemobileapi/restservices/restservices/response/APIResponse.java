package com.fitscorp.j2eemobileapi.restservices.restservices.response;


public class APIResponse {
    
    private String status;
    private String errorMessages;

    public String getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(String errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
