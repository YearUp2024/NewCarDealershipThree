package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(
                BasicDataSource basicDataSource = getConfiguredDataSource(args);
        ){
            DataManager dataManager = new DataManager(basicDataSource);
            UserInterface userInterface = new UserInterface(dataManager);
            userInterface.display();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static BasicDataSource getConfiguredDataSource(String[] args) {
        String sqlServerURL = "jdbc:mysql://localhost:3306/cardealership";
        String userName = args[0];
        String password = args[1];

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(sqlServerURL);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }
}