package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private final BasicDataSource basicDataSource;

    public DataManager(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public List<Vehicle> getVehiclesByPrice(int dealershipId, double min, double max){
        return  null;
    }

    public Vehicle getVehicleByVIN(int vin){
        Vehicle vehicle = null;

        try(
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT vehicles.VehicleVIN, vehicles.Year, vehicles.Make, vehicles.Model, vehicles.VehicleType, vehicles.Color, vehicles.Odometer, vehicles.Price\n" +
                                "FROM cardealership.vehicles\n" +
                                "JOIN cardealership.inventory ON vehicles.VehicleVIN = inventory.VehicleVIN\n" +
                                "WHERE vehicles.VehicleVIN = ?;"
                )
        ){
            preparedStatement.setInt(1, vin);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    vehicle = new Vehicle(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getDouble(8)
                    );
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicles(int dealershipId){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try(
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * \n" +
                        "FROM cardealership.Vehicles\n" +
                        "JOIN inventory on inventory.VehicleVIN = vehicles.VehicleVIN\n" +
                        "WHERE inventory.dealershipID = ?;"
                )
        ){
            preparedStatement.setInt(1, dealershipId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    vehicles.add(new Vehicle(
                            resultSet.getInt(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getDouble(8)
                    ));
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public void addVehicle(int dealershipId, Vehicle vehicle){

    }
}
