package com.ecreatic.test.services;

import com.ecreatic.test.model.User;

import java.util.List;
import java.util.Optional;


    public interface UserService {
        List<User> findAll();

        Optional<org.h2.engine.User> findBy(String email);

        org.h2.engine.User save(org.h2.engine.User user);

        org.h2.engine.User update(org.h2.engine.User user);

        void delete(org.h2.engine.User user);
    }

