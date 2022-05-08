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

    public ArrayList<String> getPastUnansweredQuestions(User user) {
        String getQuestionQuery = "select message from customerServiceQuestions where username = \"" + user.getUsername() + "\"";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQuestionQuery);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<String> rsArr = new ArrayList<>();
            while (rs.next()) {
                if (rs != null )
                rsArr.add(rs.getString(1));
            }
            return rsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUnansweredQuestions(User user) {
        String getQuestionQuery = "delete from customerServiceQuestions where username = \"" + user.getUsername() + "\"";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQuestionQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllAnsweredQuestions(User user) {
        String getQA = "select question, answer from customerServiceQA where username = \"" + user.getUsername() + "\" " + "order by messagePairId";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQA);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<String> rsArr = new ArrayList<>();
            while (rs.next()) {
                if (rs != null )
                    rsArr.add("Question: " +  rs.getString(1) + "\nAnswer: " + rs.getString(2));
            }
            return rsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAnsweredQuestions(User user) {
        String getQuestionQuery = "delete from customerServiceQA where username = \"" + user.getUsername() + "\"";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQuestionQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
