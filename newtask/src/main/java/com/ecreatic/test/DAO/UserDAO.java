package com.ecreatic.test.DAO;

import com.ecreatic.test.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User insert(User user);

    Optional<User> findByEmail(String Eamil);

    User findById(int id);


    List<User> findAll();
}