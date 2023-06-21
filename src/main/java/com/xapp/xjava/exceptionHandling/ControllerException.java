package com.xapp.xjava.exceptionHandling;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ControllerException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    
}
