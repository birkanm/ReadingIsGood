package com.birkan.rig.controller;

import com.birkan.rig.exception.BookNotFoundException;
import com.birkan.rig.exception.CustomerNotFoundException;
import com.birkan.rig.exception.NoStockLeftException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReadingIsGoodControllerAdvice {

    @ResponseBody
    @ExceptionHandler(NoStockLeftException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String noStockLeftHandler(NoStockLeftException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler( {CustomerNotFoundException.class, BookNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String customerNotFound(CustomerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler( {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String dropAll(Exception ex) {
        return ex.getMessage();
    }
}
