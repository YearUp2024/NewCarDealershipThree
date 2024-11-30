package com.pluralsight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dealership implements ITextEncodable{
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone, List<Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(List<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public List<Vehicle> getVehicleByPrice(double min, double max){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByModel(String make, String model){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByYear(int year){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getYear() == year){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByColor(String color){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByMileage(int minMileage, int maxMileage){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage){
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> getVehicleByType(String vehicleType){
        List<Vehicle> result = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                result.add(vehicle);
            }
        }
        return result;
    }

    public Vehicle getVehicleByVin(int vin){
        for(Vehicle vehicle : inventory){
            if(vehicle.getVin() == vin){
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> displayAllVehicle(){
        return new ArrayList<>(inventory);
    }

    public boolean addVehicle(Vehicle vehicle){
        if(inventory == null){
            return false;
        }

        boolean addedVehicle = inventory.add(vehicle);
        return addedVehicle;
    } 

    public boolean removeVehicle(int vin){
        Iterator<Vehicle> iterator = inventory.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getVin() == vin){
                iterator.remove();
                System.out.println("Vehicle with VIN: " + vin + " is removed!");
                return true;
            }
        }
        System.out.println("Vehicle with " + vin + " id was not found in the inventory");
        return false;
    }

    @Override
    public String encode() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getName()).append("|").append(this.getAddress()).append("|").append(this.getPhone()).append("\n");

        for(Vehicle v : this.inventory){
            sb.append(v.encode()).append("\n");
        }
        return sb.toString();
    }
}
