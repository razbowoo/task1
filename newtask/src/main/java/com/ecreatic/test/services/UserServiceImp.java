package com.ecreatic.test.services;

import com.ecreatic.test.model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImp implements UserService {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findBy(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
