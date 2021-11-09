package com.ozzy.loginapihibernate.aspect;


import com.ozzy.loginapihibernate.exceptions.DataNotCreatedException;
import com.ozzy.loginapihibernate.exceptions.DataNotFoundException;
import com.ozzy.loginapihibernate.exceptions.DataNotUpdatedException;
import com.ozzy.loginapihibernate.exceptions.DataNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingAspect {
    
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleDataNotFoundException(){}
    
    @ExceptionHandler(DataNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public void handleDataNotValidException(){}
    
    @ExceptionHandler(DataNotCreatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleDataNotCreatedException(){}
    
    @ExceptionHandler(DataNotUpdatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleDataNotUpdatedException(){}
}
