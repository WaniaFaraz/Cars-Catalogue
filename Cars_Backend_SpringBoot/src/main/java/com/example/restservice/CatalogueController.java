package com.example.restservice;

/* 
import java.io.FileNotFoundException;
import java.io.IOException;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.Catalogue;
import com.example.Cars.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:5173")
public class CatalogueController {

    private Catalogue catalogue;
    //private final String filename = "car_catalogue_1";

    public CatalogueController(Catalogue catalogue){
        this.catalogue = catalogue;
    }

    //load happens automatically at startup
    @GetMapping("/save")
    public boolean saveCars() {
        //saves as JSON (list)
        catalogue.save();
        return true;
    }
    
    @GetMapping("/get-car")
    public Car getCar(@RequestParam() String name) {
        //gets a car from the catalogue by name
        Car car = catalogue.getCar(name);
        return car;
    }

    @GetMapping("/get-catalogue")
    public List<Car> getCatalogue() {
        //gets the entire catalogue in the form of a list
        catalogue.load();
        return catalogue.getCars();
    }

    

    //useless
    @GetMapping("/returnsCar")
    public Car returnCar() {
        List<String> features = new ArrayList<>();
        features.add("features");
        List<String> drive = new ArrayList<>();
        drive.add("4WD");
        drive.add("AWD");
        String image = "https://www.google.com/imgres?q=toyota&imgurl=https%3A%2F%2Fscene7.toyota.eu%2Fis%2Fimage%2Ftoyotaeurope%2FLC_300_City_Connected_KV1_Crop1%3Fwid%3D1280%26fit%3Dfit%2C1%26ts%3D0%26resMode%3Dsharp2%26op_usm%3D1.75%2C0.3%2C2%2C0&imgrefurl=https%3A%2F%2Fwww.toyota-europe.com%2Fbrands-and-services%2Ftoyota%2Four-models&docid=SCIl1X3nmEOJUM&tbnid=7FC9ColRPKnloM&vet=12ahUKEwiDypX52ZWWAxWDhf0HHdrtOacQnPAOegUImAEQAA..i&w=1280&h=837&hcb=2&ved=2ahUKEwiDypX52ZWWAxWDhf0HHdrtOacQnPAOegUImAEQAA";
        return new FuelCar("Toyota", "Corolla", 2008, 2000000, 500000, 500000, 7, drive, -1, features, image , 200);
    }

    //useless
    @GetMapping("/WynaCar")
    public String WynaCar() {
        List<String> features = new ArrayList<>();
        features.add("Sun roofs");
        features.add("Voice Assistant");
        features.add("7 seater");
        features.add("360 camera");
        List<String> drive = new ArrayList<>();
        drive.add("4WD");
        drive.add("RWD");
        String image = "https://www.google.com/imgres?q=toyota&imgurl=https%3A%2F%2Fscene7.toyota.eu%2Fis%2Fimage%2Ftoyotaeurope%2FLC_300_City_Connected_KV1_Crop1%3Fwid%3D1280%26fit%3Dfit%2C1%26ts%3D0%26resMode%3Dsharp2%26op_usm%3D1.75%2C0.3%2C2%2C0&imgrefurl=https%3A%2F%2Fwww.toyota-europe.com%2Fbrands-and-services%2Ftoyota%2Four-models&docid=SCIl1X3nmEOJUM&tbnid=7FC9ColRPKnloM&vet=12ahUKEwiDypX52ZWWAxWDhf0HHdrtOacQnPAOegUImAEQAA..i&w=1280&h=837&hcb=2&ved=2ahUKEwiDypX52ZWWAxWDhf0HHdrtOacQnPAOegUImAEQAA";
        boolean success = catalogue.addFuelCar("Toyota", "Land Cruiser Prado", 2006, 14000000, 1400000, 140000, 7, drive, -1, features, image, 140);
        if(success) {
            return "car added";
        }else {
            return "car not added";
        }
    }

    @PostMapping("/addCar")
    public boolean addCar(@RequestParam MultiValueMap<String, String> allParams) {
        //adds a car to the map - which adds it to the list
        String type = allParams.getFirst("type");
        boolean success;
        //extract attributes
        String company = allParams.getFirst("company");
        String model = allParams.getFirst("model");
        int year = Integer.parseInt(allParams.getFirst("year"));
        int price = Integer.parseInt(allParams.getFirst("price"));
        int resaleValueYears = Integer.parseInt(allParams.getFirst("resaleValueYears"));
        int resaleValueDistance = Integer.parseInt(allParams.getFirst("resaleValueDistance"));
        int numSeats = Integer.parseInt(allParams.getFirst("numSeats"));
        List<String> drive = allParams.get("drive");
        double length = Double.parseDouble(allParams.getFirst("length"));
        List<String> features = allParams.get("features");
        System.out.println("features: " + features);
        String image = allParams.getFirst("image");
        if(type.equals("Fuel")) {
            int fuelMileage = Integer.parseInt(allParams.getFirst("fuelMileage"));
            success = catalogue.addFuelCar(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, image, fuelMileage);
        }
        else if (type.equals("HEV")) {
            int electricityMileage = Integer.parseInt(allParams.getFirst("electricityMileage"));
            int fuelMileage = Integer.parseInt(allParams.getFirst("fuelMileage"));
            success = catalogue.addHEV(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features,image, fuelMileage, electricityMileage);
        }
        else if(type.equals("PHEV")) {
            int fuelMileage = Integer.parseInt(allParams.getFirst("fuelMileage"));
            int electricityMileage = Integer.parseInt(allParams.getFirst("electriciyMileage"));
            double chargingTime = Double.parseDouble(allParams.getFirst("chargingTime"));
            String charger = allParams.getFirst("charger");
            success = catalogue.addPHEV(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features,image, fuelMileage, electricityMileage, chargingTime, charger);
        }
        else {
            return false;
        }
        saveCars();
        
        return success;
    }

    @GetMapping("/delete-car")
    public boolean deleteCar(@RequestParam() String name) {
        return catalogue.remove(name);
    }
}
