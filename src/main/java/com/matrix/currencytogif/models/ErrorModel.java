package com.matrix.currencytogif.models;

public class ErrorModel {
    private String rawMessage;
    private String message;
    private Integer code;

    public static ErrorModel builder() {
        return new ErrorModel();
    }

    public String getMessage() {
        return message;
    }

    public ErrorModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ErrorModel setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public ErrorModel setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
        return this;
    }
}
