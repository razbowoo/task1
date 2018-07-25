package com.ecreatic.test.services;

import com.ecreatic.test.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
        List<User> findAll();

        Optional<User> findBy(String email);


        abstract User save(User user);

        User update(User user);

        void delete(User user);


}

