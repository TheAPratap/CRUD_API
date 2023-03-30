package com.crud.crudapi.repositories;

import com.crud.crudapi.domain.User;
import com.crud.crudapi.exceptions.CrudAuthException;

public interface UserRepository
{
    Integer create(String firstName, String lastName, String email, String password) throws CrudAuthException;

    User findByEmailAndPassword(String email, String password) throws CrudAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer usedId);
}
