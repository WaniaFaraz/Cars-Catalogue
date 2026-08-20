package com.example.Cars;

import java.io.Serializable;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tools.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(contentAs = Car.class)
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY, 
        property = "type",
        visible = true
    )
    @JsonSubTypes({
        @JsonSubTypes.Type(value = com.example.Cars.FuelCar.class, name = "Fuel"),
        @JsonSubTypes.Type(value = com.example.Cars.Phev.class, name = "PHEV"),
        @JsonSubTypes.Type(value = com.example.Cars.Hev.class, name = "HEV")
    })

public abstract class Car implements Serializable {
    /*
     * Car
     */
    protected String name;
    protected String company;
    protected String model;
    protected int year;
    protected int price; // in pkr
    protected int resaleValueYears; // after 4 years...
    protected int resaleValueDistance; // after 60,000 km...
    protected int numSeats;
    protected List<String> drive;
    protected double length; // length from head to tail in meters
    protected Features features;
    protected String image;

    private static final long serialVersionUID = 7579247524826567332L;

    

    //--------  CONSTRUCTORS
    public Car(String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                 int numSeats, List<String> drive, double length, List<String> features, String image) {
        setCompany(company);
        setModel(model);
        setYear(year);
        setPrice(price);
        setDrive(drive);
        setResaleValueYears(resaleValueYears);
        setResaleValueDistance(resaleValueDistance);
        setNumSeats(numSeats);
        setLength(length);
        setFeatures(features);
        setImage(image);
        setName();
    }

    public Car() {
    }

    // -------- SETTERS
    public void setName() {
        name = company + " " + model + " " + year;
    }

    public void setCompany(String company) {
        this.company = company;
        if (model != null && company != null && year != 0) {
            setName();
        }
    }

    public void setModel(String model) {
        this.model = model;
        if (model != null && company != null && year != 0) {
            setName();
        }
    }

    public void setYear(int year) {
        this.year = year;
        if (model != null && company != null && year != 0) {
            setName();
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setResaleValueYears(int resaleValue) {
        this.resaleValueYears = resaleValue;
    }

    public void setResaleValueDistance(int resaleValue) {
        this.resaleValueDistance = resaleValue;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public void setDrive(List<String> drive) {
        this.drive = drive;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public void setFeatures(List<String> features) {
        Features allFeatures = new Features(features);
        this.features = allFeatures;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // -------- GETTERS
    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public List<String> getDrive() {
        return drive;
    }

    public int getResaleValueYears() {
        return resaleValueYears;
    }

    public int getResaleValueDistance() {
        return resaleValueDistance;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public double getLength() {
        return length;
    }

    public String getImage() {
        return image;
    }

    @JsonProperty("features")
    public List<String> getFeatures() {
        return features.getFeatures();
    }

    public abstract String getType();

    // -------- USUAL FUNCTIONS
    public abstract String toString();

    // -------- OTHER FUNCTIONS
    public boolean addFeature(String feature) {
        return features.addFeature(feature);
    }

    public boolean hasFeature(String feature) {
        if (features == null)
            return false;
        return features.hasFeature(feature);
    }

}
