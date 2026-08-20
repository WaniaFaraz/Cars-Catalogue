package com.example.Cars;

import java.util.List;

public class Hev extends Car {
    public final String type = "HEV";
    private int fuelMileage;
    private int electricityMileage;
    
    //--------  CONSTRUCTORS
    public Hev(String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                     int numSeats, List<String> drive, double length,  List<String> features, String image, int fuelMileage, int electricityMileage) {
        super(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, image);
        
        setFuelMileage(fuelMileage);
        setElectricityMileage(electricityMileage);
    }
    
    //--------  SETTERS
    public void setFuelMileage(int fuelMileage) {
        this.fuelMileage = fuelMileage;
    }

    public void setElectricityMileage(int electricityMileage) {
        this.electricityMileage = electricityMileage;
    }
    //--------  GETTERS
    public String getType(){
        return type;
    }

    public int getFuelMileage() {
        return fuelMileage;
    }

    public int getElectricityMileage() {
        return electricityMileage;
    }

    //--------  USUAL FUNCTIONS
    public String toString() {
        /*
        Cherry Tiggo 2008
        Cost: 12,000,000 pkr
        Resale Value after 4 years: 150,000 pkr
        Resale Value after 60,000 km: 150,000 pkr
        7 seater, AWD
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
               Fuel Mileage:                 %,d
               Electricity Mileage:          %,d
               Features:
               %s  %n
            """, company, model, price, resaleValueYears, resaleValueDistance, numSeats, drive, 
                fuelMileage, electricityMileage, features.toString());
        return display;
    }
    
    
    
}
