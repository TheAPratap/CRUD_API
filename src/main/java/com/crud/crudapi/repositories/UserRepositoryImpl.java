package com.crud.crudapi.repositories;

import com.crud.crudapi.domain.User;
import com.crud.crudapi.exceptions.CrudAuthException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository
{
    private static final String SQL_CREATE = "INSERT INTO crud_users(user_id, first_name, last_name, email, password) VALUES(NEXTVAL('crud_users_seq'), ?, ?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM crud_users WHERE email = ?";
    private static final String SQL_FIND_BY_ID = "SELECT user_id, first_name, last_name, email, password " + "FROM crud_users WHERE user_id = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT user_id, first_name, last_name, email, password " + "FROM crud_users WHERE email =?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws CrudAuthException
    {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);

                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("user_id");
        }catch(Exception e){
            throw new CrudAuthException("Invalid details, Please enter correct details to create your account.");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws CrudAuthException
    {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, new Object[]{email});
            
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new CrudAuthException("Invalid email/password");

            return user;
        }catch(EmptyResultDataAccessException e){
            throw new CrudAuthException("Invalid email/password");
        }
    }

    @Override
    public Integer getCountByEmail(String email)
    {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, new Object[]{email});
    }

    @Override
    public User findById(Integer userId)
    {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper, new Object[]{userId});
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"));
    });
}
