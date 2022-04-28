package com.auction.dao;

import com.auction.model.Vehicle;
import com.auction.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleDao {

    private Connection connection;

    public VehicleDao() throws SQLException {
        connection = DbUtil.getConnection();
    }

    public void addVehicle(Vehicle v) {
        String addQuery = "insert into vehicle(vin, make, model, year, color, num_doors, bed_size, pedal_size, isCar, isTruck, isMotorBike)" +
                "values(?,?,?,?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);

            preparedStatement.setInt(1, v.getVin());
            preparedStatement.setString(2, v.getMake());
            preparedStatement.setString(3, v.getModel());
            preparedStatement.setString(4, v.getYear());
            preparedStatement.setString(5, v.getColor());
            preparedStatement.setInt(6, v.getNumdoors());
            preparedStatement.setInt(7, v.getBed_size());
            preparedStatement.setInt(8, v.getPedal_size());
            preparedStatement.setBoolean(9, v.isCar());
            preparedStatement.setBoolean(10, v.isTruck());
            preparedStatement.setBoolean(11, v.isMotorcycle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
