package com.ldg.main.exceptions;

import org.springframework.http.HttpStatus;

public class HttpStatusCodeException extends Exception {
    private HttpStatus statusCode;

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public HttpStatusCodeException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
