package com.xapp.xjava.exceptionHandling;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<String> handleControllerExceptions(ControllerException controllerException) {
        return new ResponseEntity<String>("Something went Wrong!#", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException) {
       return new ResponseEntity<String>("No Value is Present " + elementException.getMessage(), HttpStatus.NOT_FOUND);
    }   
}