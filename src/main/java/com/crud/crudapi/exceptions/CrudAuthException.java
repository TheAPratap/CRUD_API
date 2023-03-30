package com.crud.crudapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CrudAuthException extends RuntimeException
{
    public CrudAuthException(String message)
    {
        super(message);
    }
}
