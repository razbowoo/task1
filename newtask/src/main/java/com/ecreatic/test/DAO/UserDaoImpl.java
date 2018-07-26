package com.ecreatic.test.DAO;

import com.ecreatic.test.model.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDAO {
    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User insert(User user) {

        String sql = "INSERT INTO USERS " +
                "(FIRSTNAME, LASTNAME , EMAIL ,PASSWORD,ACTIVE,ROLE) VALUES ( ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, user.getId());
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getActive());
            ps.setString(6, user.getRole());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return user;
    }

    @Override
    public Optional<User> findByEmail(String Email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getInt("ACTIVE"),
                        rs.getString("ROLE")

                );
                rs.close();
                ps.close();

                return Optional.of(user);

            } else {
                rs.close();
                ps.close();
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User findById(int id) {

        String sql = "SELECT * FROM USERS WHERE identity = ?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getInt("ACTIVE"),
                        rs.getString("ROLE")
                );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getInt("ACTIVE"),
                        rs.getString("ROLE")
                );
                users.add(user);
            }
            rs.close();
            ps.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}