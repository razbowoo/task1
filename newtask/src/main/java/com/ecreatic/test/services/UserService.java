package com.ecreatic.test.services;

import com.ecreatic.test.DAO.UserDAO;
import com.ecreatic.test.model.User;


public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user){
        userDAO.insert(user);
    }

    public  void

