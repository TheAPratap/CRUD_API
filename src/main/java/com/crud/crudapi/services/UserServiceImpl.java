package com.crud.crudapi.services;

import com.crud.crudapi.domain.User;
import com.crud.crudapi.exceptions.CrudAuthException;
import com.crud.crudapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws CrudAuthException {
        if(email != null) email = email.toLowerCase();

        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws CrudAuthException
    {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new CrudAuthException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new CrudAuthException("Email already in use");

        Integer userId = userRepository.create(firstName, lastName, email, password);

        return userRepository.findById(userId);
    }
}
