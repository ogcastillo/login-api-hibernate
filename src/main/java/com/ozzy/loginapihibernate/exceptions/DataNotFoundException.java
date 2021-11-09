package com.ozzy.loginapihibernate.exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String msn) {
        super(msn);
    }
}
