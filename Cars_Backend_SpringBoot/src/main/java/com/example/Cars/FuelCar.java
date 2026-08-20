package com.example.Cars;

import java.util.List;

public class FuelCar extends Car {
    public final String type = "Fuel";
    private int mileage;
    
    //--------  CONSTRUCTOR
    public FuelCar(String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                 int numSeats, List<String> drive, double length, List<String> features, String image, int mileage) {
        super(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, image);
        setMileage(mileage);
    }

    //--------  Getters and setters
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getMileage() {
        return mileage;
    }

    public int getFuelMileage() {
        //method name consistent with siblings...
        return mileage;
    }

    public String getElectricityMileage() {
        //for consistency with sibling methods
        return "Not applicable";
    }

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
        Fuel Mileage:
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
            Fuel Mileage:                 %,d km
            Features:
            %s %n      
            """
            , company, model, year, price, resaleValueYears, resaleValueDistance,
                    numSeats, drive, mileage, features.toString());
        
        return display;
        

    }

}
