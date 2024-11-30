package com.pluralsight.Contracts;

import com.pluralsight.ITextEncodable;
import com.pluralsight.Vehicle;

public abstract class Contract implements ITextEncodable {
    private int dateOfContract;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;

    public Contract(int dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public int getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(int dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    @Override
    public String toString() {
        return String.format("Contract Date: %d, Customer: %s, Email: %s, Vehicle: %s",
                dateOfContract,
                customerName,
                customerEmail,
                vehicleSold.toString());
    }
}
