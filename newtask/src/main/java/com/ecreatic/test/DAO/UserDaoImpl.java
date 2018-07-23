package com.ecreatic.test.DAO;

import com.ecreatic.test.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

@Component
public class UserDaoImpl implements UserDAO{
    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User insert(User user) {

        String sql = "INSERT INTO USER " +
                "(ID, FIRSTNAME, LASTNAME , EMAIL ,PASSWORD,ACTIVE,ROLE) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6,user.getActive());
            ps.setString(7,user.getRole());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String Email) {
        String sql = "SELECT * FROM USER WHERE EMAIL = ?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNANME"),
                        rs.getString("LASTNANME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD")
                );
            }
            rs.close();
            ps.close();
            return Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                        rs.getString("PASSWORD")
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