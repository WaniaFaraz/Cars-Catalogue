package com.example;
/* 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;

import org.springframework.stereotype.Service;

import com.example.Cars.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.PostConstruct;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


import java.util.Comparator;


@Service
public class Catalogue {

    List<Car> carsList = new ArrayList<>();
    
    @JsonIgnore
    HashMap<String, Car> cars; //Car full name, Car object
                            //ex: Cherry Tiggo 2008, (Car object)
    //private static final String filename = "car_catalogue_1";

    @PostConstruct
    public void init() {
        load();
        /*
        try {
            load(filename);
            System.err.println("Successfully loaded");
        }catch(FileNotFoundException e) {
            System.err.println("File not found");
            System.out.println("LOOKING HERE: " + new java.io.File(filename).getAbsolutePath());
        }catch(IOException e) {
            System.err.println("IO Exception");
        }catch(ClassNotFoundException e) {
            System.err.println("Class not found.");
            e.printStackTrace();
        }
        loadIntoList();
         */
         
    }

    public void loadIntoList() {
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            carsList.add(car);
        }
    }

    public List<Car> getCars() {
        return carsList;
    }

    public Car getCar(String name) {
        return cars.get(name);
    }

    public void setCars(ArrayList<Car> carsList) {
        this.carsList = carsList;
    }
    
    //--------  CONSTRUCTORS
    public Catalogue() {
        cars = new HashMap<>();
        carsList = new ArrayList<>();
    }

    //--------  CHECK FOR CAR
    public boolean hasCar(String name) {
        return cars.containsKey(name);
    }    

    //--------  Displaying
    public void printDetailed() {
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = (Car) entry.getValue();
            System.out.println(car);
        }
    }
    
    public void printNonDetailed() {
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            String car = (String) entry.getKey();
            System.out.println(car);
        }
    }

    
    //--------  Adding, Editing, Deleting
    //--------  Add cars
    //TODO: Check for duplicates, print both and prompt to keep one
    public synchronized boolean addFuelCar( String company, String model, int year, int price, int resaleValueYears,
                        int resaleValueDistance, int numSeats, List<String> drive, double length, List<String> features, String image,
                        int fuelMileage) {

        //Create car
        Car car = new FuelCar(company, model, year, price, resaleValueYears, resaleValueDistance,
                 numSeats, drive, length, features, image, fuelMileage);
        //Add to catalogue
        cars.put(car.getName(), car);
        //Add to list
        carsList.add(car);
        return true;
    }

    public synchronized boolean addHEV(String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                int numSeats, List<String> drive, double length, List<String> features, String image, int fuelMileage, int electricityMileage) {
                
        Car car = new Hev(company, model, year, price, resaleValueYears, resaleValueDistance,
            numSeats, drive, length, features, image, fuelMileage, electricityMileage);

        cars.put(car.getName(), car);
        carsList.add(car);
        return true;
    }

    public synchronized boolean addPHEV (String company, String model, int year, int price, int resaleValueYears, int resaleValueDistance,
                int numSeats, List<String> drive, double length, List<String> features, String image, int fuelMileage, int electricityMileage, 
                double chargingTime, String charger) {
        
        Car car = new Phev(company, model, year, price, resaleValueYears, resaleValueDistance,
                numSeats, drive, length, features, image, fuelMileage, electricityMileage, chargingTime, charger);
        
        cars.put(car.getName(), car);
        carsList.add(car);
        return true;

    }

    //--------  Edit cars
    //Company
    public synchronized boolean editCompany(String name, String company) {
        if (cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setCompany(company);
            return true;
        }
        else {
            return false;
        }
    }
    //Model
    public synchronized boolean editModel(String name, String model) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setModel(model);
            return true;
        }
        else {
            return false;
        }
    }
    //Year
    public synchronized boolean editYear(String name, int year) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setYear(year);
            return true;
        }
        else {
            return false;
        }
    }
    //Price
    public synchronized boolean editPrice(String name, int price) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setPrice(price);
            return true;
        }
        else {
            return false;
        }
    }
    //ResaleValueYears
    public synchronized boolean editResaleValueYears(String name, int resaleValueYears) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setResaleValueYears(resaleValueYears);
            return true;
        }
        else {
            return false;
        }
    }
    //ResaleValueDistance
    public synchronized boolean editResaleValueDistance(String name, int resaleValueDistance) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setResaleValueDistance(resaleValueDistance);
            return true;
        }
        else {
            return false;
        }
    }
    //numSeats
    public synchronized boolean editNumSeats(String name, int numSeats) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setNumSeats(numSeats);
            return true;
        }
        else {
            return false;
        }
    }
    //Drive
    public synchronized boolean editDrive(String name, List<String> drive) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setDrive(drive);
            return true;
        }
        else {
            return false;
        }
    }
    //Length
    public synchronized boolean editLength(String name, double length) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setLength(length);
            return true;
        }
        else {
            return false;
        }
    }

    //image
    public synchronized boolean editImage(String name, String image) {
        if(cars.containsKey(name)) {
            Car car = cars.get(name);
            car.setImage(image);
            return true;
        }
        return false;
    }
    //Features
    //TODO: Refine
    /*
    public boolean editFeatures(String name, String something) {
        return true;
    }

    */
    //--------  Delete cars
    public synchronized boolean remove(String name) {
        //remove a car from the hash map
        Car car = cars.remove(name);
        if(car == null) return false;
        carsList.remove(car);
        return true;
    }


    //--------  Filtering functions
    public synchronized List<Car> displayFilteredPrice(int min, int max) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet() ) {
            Car car = entry.getValue();
            if(car.getPrice() >= min && car.getPrice() <= max) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredCompany(String company) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if (company.equalsIgnoreCase(car.getCompany())) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredYear(int year) {
        boolean found = false;
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if (car.getYear() >= year) {
                filteredCars.add(car);
                found = true;
            }
        }
        if(found) {
            //sort by year and print
            List<Car> sortedCars = filteredCars.stream()
                                    .sorted(Comparator.comparingInt(Car::getYear))
                                    .toList();
            return sortedCars;
        }
        else {
            //no cars passed the filter
            return null;
        }
    }

    public synchronized List<Car> displayFilteredSeats(int numSeats) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if(car.getNumSeats() == numSeats) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredDrive(String drive) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            for(String current: car.getDrive()) {
                if(current.equals(drive));
                filteredCars.add(car);
            }
           
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredResaleValueYears(int resaleValueYears) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if(car.getResaleValueYears() >= resaleValueYears) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredResaleValueDistance(int resaleValueDistance) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if(car.getResaleValueDistance() >= resaleValueDistance) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    public synchronized List<Car> displayFilteredFeature(String feature) {
        List<Car> filteredCars = new ArrayList<>();
        for(Map.Entry<String, Car> entry : cars.entrySet()) {
            Car car = entry.getValue();
            if(car.hasFeature(feature)) {
                filteredCars.add(car);
            }
        }
        if(filteredCars.size() == 0) return null;
        return filteredCars;
    }

    //Get all data
    public synchronized List<String> getAllCompanies() {
        Set<String> allCompanies = new HashSet<>();
        for(Car car : carsList) {
            String company = car.getCompany();
            allCompanies.add(company);
        }
        return new ArrayList<>(allCompanies);
    }

    public synchronized List<String> getAllFeatures() {
        Set<String> allFeatures = new HashSet<>();
        for(Car car: carsList) {
            List<String> features = car.getFeatures();
            for(String feature: features) {
                allFeatures.add(feature);
            }
        }
        return new ArrayList<>(allFeatures);
    }

    //--------  PERSISTENCE - SAVE AND LOAD
    /* 
    public boolean save(String filename) throws FileNotFoundException, IOException {
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            
            for(Map.Entry<String, Car> entry : cars.entrySet()) {
                oos.writeObject(entry.getValue());
            }
            saveAsJSON();
            return true;
        } 
    }
*/
    public synchronized boolean save() {
        ObjectMapper om = new ObjectMapper();
        om.writerWithDefaultPrettyPrinter().writeValue(new File("cars_json.json"), carsList);
        return true;
    }

   /*
    public boolean load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            while(true) {
                Object obj = ois.readObject();
                Car car = (Car) obj;
                cars.put(car.getName(), car);
            }
            
        }catch(EOFException e) { //reached the end of the file - normal
            return true;
        }
    }
    */   
 
    public boolean load() {
        ObjectMapper om = new ObjectMapper();
        List<Car> readCars = om.readValue(new File("cars_json.json"), new TypeReference<List<Car>>() {});
        carsList = readCars;
        loadIntoMap();
        return true;
    }

    public void loadIntoMap() {
        for(Car car: carsList) {
            String name = car.getName();
            cars.put(name, car);
        }
    }
 
}
