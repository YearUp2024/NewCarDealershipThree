package com.pluralsight.Contracts;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract{
    private final double salesTaxPercentage = 0.05;
    private int recordingFee;
    private int processingFee;
    private double salesTaxAmount;
    private boolean wantsToFinance;

    public SalesContract(int dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean wantsToFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295 : 495;
        this.salesTaxAmount = vehicleSold.getPrice() * salesTaxPercentage;
        this.wantsToFinance = wantsToFinance;
    }

    public SalesContract(int dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, int recordingFee, int processingFee, double salesTaxAmount, boolean wantsToFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.salesTaxAmount = salesTaxAmount;
        this.wantsToFinance = wantsToFinance;
    }

    public double getSalesTaxPercentage() {
        return salesTaxPercentage;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public boolean isWantsToFinance() {
        return wantsToFinance;
    }

    public void setWantsToFinance(boolean wantsToFinance) {
        this.wantsToFinance = wantsToFinance;
    }

    @Override
    public double getTotalPrice(){
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment(){
        if(!wantsToFinance){
            return 0;
        }

        double loanAmount = getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
        double interestRate;
        int loanMonth;

        if(getVehicleSold().getPrice() >= 10000){
            interestRate = 0.0425;
            loanMonth = 48;
        }else {
            interestRate = 0.0525;
            loanMonth = 24;
        }

        double monthlyInterestRate = interestRate / 12;
        return (loanAmount * monthlyInterestRate) / ( 1 - Math.pow(1 + monthlyInterestRate, - loanMonth));
    }

    @Override
    public String toString() {
        return String.format("Sales Contract - Customer: %s, Vehicle: %s, Total Price: $%.2f, Monthly Payment: $%.2f",
                getCustomerName(),
                getVehicleSold().getMake() + " " + getVehicleSold().getModel(),
                getTotalPrice(),
                getMonthlyPayment());
    }

    @Override
    public String encode() {
        return "SALE|" +
                this.getDateOfContract() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getSalesTaxAmount() + "|" +
                this.getRecordingFee() + "|" +
                this.getProcessingFee() + "|" +
                this.getTotalPrice() + "|" +
                (this.isWantsToFinance() ? "YES" : "NO") + "|" +
                this.getMonthlyPayment();

    }
}
