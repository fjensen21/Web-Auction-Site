package com.auction.dao;

import com.auction.model.Message;
import com.auction.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageDao {
    private Connection connection;

    public MessageDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addMessage(String msg, String username) {
        String msgQuery = "insert into messages(id, username, message, msg_datetime) " +
                "values(NULL, ?,?,?)";

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String formattedSchedule = localDateTime.format(format);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(msgQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, msg);
            preparedStatement.setString(3, formattedSchedule);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Message> getMessagesByUsername(String username){
        String query = "select * from messages where username=? order by msg_datetime";
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Message msg = new Message();
                msg.setId(rs.getInt("id"));
                msg.setUsername(rs.getString("username"));
                msg.setMessage(rs.getString("message"));
                msg.setMsg_datetime(rs.getString("msg_datetime"));
                messages.add(msg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
