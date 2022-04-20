package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.auction.model.User;
import com.auction.util.DbUtil;

public class UserDao {

    private Connection connection;

    public UserDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        String addQuery = "insert into user(username,firstname,lastname,email,isEndUser,isCustomerRep,isAdmin,password) " +
                "values(?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(addQuery);
            // Parameters

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirst_name());
            preparedStatement.setString(3, user.getLast_name());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setBoolean(5, user.isEndUser());
            preparedStatement.setBoolean(6, user.isCustomerRep());
            preparedStatement.setBoolean(7, user.isAdmin());
            preparedStatement.setString(8, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUniqueUsername(String username) {
        String query = "select * from user where username=?";
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();

            if(!rs.next()) { return true; }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from user where username=?;");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                user.setUsername(rs.getString("username"));
                user.setFirst_name(rs.getString("firstname"));
                user.setLast_name(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setEndUser(rs.getBoolean("isEndUser"));
                user.setCustomerRep(rs.getBoolean("isCustomerRep"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean validateCredentials(String username, String password) {
        String query = "select password from user where username=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                // Validate password is correct
                if (password.equals(rs.getString("password"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
