package com.ozzy.loginapihibernate.exceptions;

public class DataNotUpdatedException extends RuntimeException{
    public DataNotUpdatedException(String msn){super(msn);}
}