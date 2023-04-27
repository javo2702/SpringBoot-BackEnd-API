package com.armonia10.backend.exception;

public class BusinessException extends Exception{
    public BusinessException(String mensaje){
        super(mensaje);
    }
}