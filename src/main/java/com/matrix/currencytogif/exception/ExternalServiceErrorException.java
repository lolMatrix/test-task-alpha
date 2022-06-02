package com.matrix.currencytogif.exception;

public class ExternalServiceErrorException extends Exception {
    private Integer statusCode;

    public ExternalServiceErrorException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
