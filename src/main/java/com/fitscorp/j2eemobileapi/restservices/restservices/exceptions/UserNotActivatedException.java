package com.fitscorp.j2eemobileapi.restservices.restservices.exceptions;

public class UserNotActivatedException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }
}
