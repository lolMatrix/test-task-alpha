package com.matrix.currencytogif.controllers;

import com.matrix.currencytogif.exception.CurrencyNotFoundException;
import com.matrix.currencytogif.exception.ExternalServiceErrorException;
import com.matrix.currencytogif.exception.GifNotFoundException;
import com.matrix.currencytogif.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ExternalServiceErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModel handleExternalServiceError(HttpServletRequest req, ExternalServiceErrorException exception) {
        return ErrorModel.builder()
                .setRawMessage(exception.getMessage())
                .setCode(500)
                .setMessage("Невозможно получить доступ к сервису");
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleCurrencyNotFound(HttpServletRequest req, CurrencyNotFoundException exception) {
        return ErrorModel.builder()
                .setMessage(exception.getMessage() + " " + exception.getCurrency())
                .setCode(404);
    }

    @ExceptionHandler(GifNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleGifNotFound(HttpServletRequest req, GifNotFoundException exception) {
        return ErrorModel.builder()
                .setMessage(exception.getMessage())
                .setCode(404);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorModel handleOtherError(HttpServletRequest req, Exception exception){
        return ErrorModel.builder()
                .setCode(500)
                .setMessage("Произошла непредвиденная ошибка");
    }



}
