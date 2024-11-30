package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private static String fileNameContract = "contracts.csv";

    public static Dealership getDealership(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Dealership dealership = null;

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameContract));

            String line;

            String[] firstLineData = bufferedReader.readLine().split("\\|");
            String name = firstLineData[0];
            String address = firstLineData[1];
            String phone = firstLineData[2];
            dealership = new Dealership(name, address, phone, vehicles);

            while((line = bufferedReader.readLine()) != null){
                String[] newLine = line.split("\\|");
                if(newLine.length == 8){
                    int vinNumber = Integer.parseInt(newLine[0]);
                    int makeYear = Integer.parseInt(newLine[1]);
                    String make = newLine[2];
                    String model = newLine[3];
                    String vehicleType = newLine[4];
                    String color = newLine[5];
                    int odometer = Integer.parseInt(newLine[6]);
                    double price = Double.parseDouble(newLine[7]);
                    vehicles.add(new Vehicle(vinNumber, makeYear, make, model, vehicleType, color, odometer, price));
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            System.out.println("Error getting dealership: " + e.getMessage());
        }
        return dealership;
    }

    public static String saveDealership(Dealership dealership){
        if(dealership == null){
            return "Dealership is Empty";
        }else{
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameContract))){
                bufferedWriter.write(new StringBuilder()
                        .append(dealership.getName()).append("|")
                        .append(dealership.getAddress()).append("|")
                        .append(dealership.getPhone()).append("\n").toString()
                );

                for(Vehicle vehicle : dealership.displayAllVehicle()){
                    bufferedWriter.write(new StringBuilder()
                            .append(vehicle.getVin()).append("|")
                            .append(vehicle.getYear()).append("|")
                            .append(vehicle.getMake()).append("|")
                            .append(vehicle.getModel()).append("|")
                            .append(vehicle.getVehicleType()).append("|")
                            .append(vehicle.getColor()).append("|")
                            .append(vehicle.getOdometer()).append("|")
                            .append(vehicle.getPrice()).append("\n").toString()
                    );
                }
            }catch(Exception e){
                System.out.println("Error saving dealership: " + e.getMessage());
            }
        }
        return "Dealership added successfully!";
    }
}

