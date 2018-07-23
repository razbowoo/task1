package com.ecreatic.test.services;

import com.ecreatic.test.DAO.UserDAO;
import com.ecreatic.test.model.User;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImp implements UserService {
    private final ShaPasswordEncoder shaPasswordEncoder;
    private final UserDAO userDAO;

    public UserServiceImp(ShaPasswordEncoder shaPasswordEncoder, UserDAO userDAO) {
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.userDAO = userDAO;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findBy(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User save(User user) {
        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
        user.setActive(1);
        user.setRole("USER");
        return userDAO.insert(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}

