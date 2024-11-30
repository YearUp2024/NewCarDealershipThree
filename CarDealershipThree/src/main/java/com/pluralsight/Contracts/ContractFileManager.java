package com.pluralsight.Contracts;

import com.pluralsight.Dealership;
import com.pluralsight.Vehicle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ContractFileManager {
    private static String contractFile = "inventory.csv";

    public void saveContract(Contract contract){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(contractFile, true))){
            bufferedWriter.write(contract.encode());
            bufferedWriter.newLine();
        }catch(Exception e){
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }

    public List<Contract> loadContracts(Dealership dealership){
        List<Contract> contracts = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(contractFile))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] data = line.split("\\|");
                int date = Integer.parseInt(data[0]);
                String customerName = data[1];
                String customerEmail = data[2];
                int vin = Integer.parseInt(data[3]);
                Vehicle vehicle = dealership.getVehicleByVin(vin);

                if (data[4].equalsIgnoreCase("LEASE")){
                    double expectedEnding = Double.parseDouble(data[5]);
                    double leaseFee = Double.parseDouble(data[6]);
                    contracts.add(new LeaseContract(date, customerName, customerEmail, vehicle, expectedEnding, leaseFee));
                }else{
                    double salesTax = Double.parseDouble(data[5]);
                    int processingFee = Integer.parseInt(data[6]);
                    int recordingFee = Integer.parseInt(data[7]);
                    boolean financing = Boolean.parseBoolean(data[8]);
                    contracts.add(new SalesContract(date, customerName, customerEmail, vehicle, recordingFee, processingFee, salesTax, financing));
                }

            }
        }catch(Exception e){
            System.out.println("Error loading contracts: " + e.getMessage());
        }
        return contracts;
    }
}
