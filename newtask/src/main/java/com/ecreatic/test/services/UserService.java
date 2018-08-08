package com.ecreatic.test.services;

import com.ecreatic.test.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> findAll();

    Optional<User> findBy(String email);

    Optional<User> findById(int id);

    User save(User user);

    void update(User user);

    void delete(User user);


}

