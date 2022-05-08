package com.auction.dao;

import com.auction.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;

import com.auction.model.CustomerServiceResponse;

public class CustomerServiceRepDao {
    private Connection connection;

    public CustomerServiceRepDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addQuestionReponsePair(CustomerServiceResponse response) {
        String addQuery = "insert into customerServiceQA(messagePairId, username, question, answer) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(addQuery);
            preparedStatement.setInt(1, response.get_messagePairId());
            preparedStatement.setString(2, response.get_username());
            preparedStatement.setString(3, response.get_question());
            preparedStatement.setString(4, response.get_response());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getAllUnansweredQuestions() {
        String getAllQuestionQuery = "select messagePairId, username, message  from customerServiceQuestions";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getAllQuestionQuery);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<String> rsArr = new ArrayList<>();
            while (rs.next()) {
                String message = "";
                if (rs != null )
                    message = rs.getString(1);
                    message = message + " ";
                    message = message + rs.getString(2);
                    message = message + " ";
                    message = message + rs.getString(3);
                    rsArr.add(message);
            }
            return rsArr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String questionOfInterest(String username, int messagePairId) {
        String getQuestionQuery = "select message from customerServiceQuestions where username = \"" + username + "\" and messagePairId =" + messagePairId;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(getQuestionQuery);
            ResultSet rs = preparedStatement.executeQuery();
            String message = "";
            while (rs.next()) {
                message = rs.getString(1);
            }
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAnsweredQuestion(String username, int messagePairId) {
        String deleteQuestionQuery = "delete from customerServiceQuestions where username = \"" + username + "\" and messagePairId =" + messagePairId;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(deleteQuestionQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
