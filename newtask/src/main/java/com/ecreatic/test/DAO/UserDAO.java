package com.ecreatic.test.DAO;

import com.ecreatic.test.model.User;

public interface UserDAO {

    void insert(User user);

    User findByName(String FirstName);

    User findById(int id);
}