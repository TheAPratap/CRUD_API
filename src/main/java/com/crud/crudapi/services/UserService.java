package com.crud.crudapi.services;

import com.crud.crudapi.domain.User;
import com.crud.crudapi.exceptions.CrudAuthException;

public interface UserService {
    User validateUser(String email, String password) throws CrudAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws  CrudAuthException;


}
