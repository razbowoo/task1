package com.ecreatic.test.DAO;

import com.ecreatic.test.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class UserDaoImpl implements UserDAO{
    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void insert(User user){

        String sql = "INSERT INTO USER " +
                "(ID, FIRSTNAME, LASTNAME , EMAIL ,PASSWORD) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public User findByName(String FirstName) {
        return null;
    }

    @Override
    public User findById(int id){

        String sql = "SELECT * FROM USER WHERE identity = ?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNANME"),
                        rs.getString("LASTNANME"),
                        rs.getString("EMAIL"),
                        rs.getInt("PASSWORD")
                );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}