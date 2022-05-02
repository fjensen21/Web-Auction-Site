package com.auction.dao;

import com.auction.model.User;
import com.auction.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;

import com.auction.model.CustomerQuestion;

public class CustomerServiceDao {
    private Connection connection;

    public CustomerServiceDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addQuestion(CustomerQuestion question) {
        String addQuery = "insert into customerServiceQuestions(username, message) values(?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(addQuery);
            preparedStatement.setString(1, question.get_username());
            preparedStatement.setString(2, question.get_message());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPastQuestions(User user) {
        String getQuestionQuery = "select message from customerServiceQuestions where username = \"" + user.getUsername() + "\"";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQuestionQuery);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<String> rsArr = new ArrayList<>();
            while (rs.next()) {
                rsArr.add(rs.getString(1));
//                System.out.println(rs.getString(1));
            }
            return rsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
