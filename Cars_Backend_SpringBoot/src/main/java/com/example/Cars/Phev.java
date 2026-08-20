package com.example.Cars;

import java.util.List;

public class Phev extends Car {
    public final String type = "PHEV";

    private int fuelMileage;
    private int electricityMileage;
    private double chargingTime;
    private String charger;

    //Constructor
    public Phev(String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                 int numSeats, List<String> drive, double length, List<String> features, String image, int fuelMileage, int electricityMileage,
                double chargingTime, String charger) {
                
        super(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, image);
        setFuelMileage(fuelMileage);
        setElectricityMileage(electricityMileage);
        setChargingTime(chargingTime);
        setCharger(charger);
    }

    //Getters and Setters
    public void setFuelMileage(int fuelMileage) {
        this.fuelMileage = fuelMileage;
    }

    public void setElectricityMileage(int electricityMileage) {
        this.electricityMileage = electricityMileage;
    }

    public void setChargingTime(double time) {
        this.chargingTime = time;
    }
    public void setCharger(String charger) {
        this.charger = charger;
    }

    public int getFuelMileage() {
        return fuelMileage;
    }

    public int getElectricityMileage() {
        return electricityMileage;
    }

    public double getChargingTime() {
        return chargingTime;
    }

    public String getCharger() {
        return charger;
    }

    //equals
    
    public String getType(){
        return type;
    }

    public String toString() {
        /*
        Cherry Tiggo 2008
        Cost: 12,000,000 pkr
        Resale Value after 4 years: 150,000 pkr
        Resale Value after 60,000 km: 150,000 pkr
        7 seater, AWD
        Charging Time: 2hrs
        Charger Type: lvl 2
        Fuel Mileage:
        Electricity Mileage:
        Features: 
        feature1, feature2, feature3, ...
        */
       String display = String.format(
        """
           -------- %s %s %d
           Cost:                         %,d pkr
           Resale Value after 4 years:   %,d pkr  
           Resale Value after 60,000 km: %,d pkr
           Seats, drive:                 %d seater, %s
           Charging Time:                %.1f hrs
           Charger Type:                 %s
           Fuel Mileage:                 %,d km
           Electricity Mileage:          %,d km
           Features:
           %s %n

        """, company, model, year, price, resaleValueYears, resaleValueDistance, numSeats,
        drive, chargingTime, charger, fuelMileage, electricityMileage, features.toString());
        return display;
    }
}
