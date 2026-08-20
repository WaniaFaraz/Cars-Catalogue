package com.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Driver {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {

        String menu = 
                """
                1. List all cars (Detailed)
                2. List all cars (Non-Detailed)
                3. Filter cars
                4. Add car
               - 5. Edit car
                6. Delete car
               - 7. View favorites
               - 8. Add to favorites
               - 9. Edit favorites
                10. Save and exit
            Enter choice:""" + " ";
        final int MENU_MAX = 10;

        String filterMenu = 
                """
                Filter by:
                1. Price
                2. Company
                3. Year
                4. Seats
                5. Drive
                6. Resale Value after 4 years
                7. Resale Value after 60,000 km
                8. Features
                9. Return
            Enter choice:""" + " ";
        final int FILTER_MENU_MAX = 9;

        String carMenu = 
                """
                1. Fuel
                2. PHEV
                3. HEV
                4. Return 
            Enter choice (integer):""" + " ";
        final int CAR_MENU_MAX = 4;
        final int TYPE_FUEL = 1;
        final int TYPE_PHEV = 2;
        final int TYPE_HEV = 3;
        final int ADD_CAR_RETURN = 4;

        String editMenu =
                """
                Choose attribute to edit:
                1.  Company
                2.  Model
                3.  Year
                4.  Price
                5.  Resale Value after 4 years
                6.  Resale Value after 60,000 km
                7.  Number of seats
                8.  Drive
                9.  Length
                10. Features
                11. Fuel Mileage
                12. Electricity Mileage (HEV and PHEV only)
                13. Charging time (PHEV)
                14. Charger type (PHEV)
                Enter choice: """ + " ";
        final int EDIT_MENU_MAX = 14;
        final int EDIT_COMPANY = 1, EDIT_MODEL = 2, EDIT_YEAR = 3, EDIT_PRICE = 4,
         EDIT_RESALE_YEAR = 5, EDIT_RESALE_DISTANCE = 6, EDIT_NUM_SEATS = 7,
         EDIT_DRIVE = 8, EDIT_LENGTH = 9, EDIT_FEATURES = 10, EDIT_FUEL_MILEAGE = 11,
         EDIT_ELECTRICITY_MILEAGE = 12, EDIT_CHARGING_TIME = 13, EDIT_CHARGER_TYPE = 14;

        String filename = "car_catalogue";
        Catalogue cars = new Catalogue(); //manages operations on collection of cars
        
        //load all saved cars at start up
        try {
            cars.load(filename);
            System.out.println("\nCars loaded successfully.\n");
        }catch(FileNotFoundException e) {
            System.err.println("\nUnable to load catalogue. File not found.\n");
        } catch (ClassNotFoundException e) {
            System.err.println("\nUnable to load catalogue. Class not found.\n");
        } catch (IOException e) {
            System.err.println("\nUnable to load catalogue. IOException.\n");
            e.printStackTrace();
        }
        
        
        int option = 1; //menu option
        //General car variables
        String company, model, drive, charger, name;
        int year, price, resaleValueYears, resaleValueDistance, numSeats, fuelMileage, electricityMileage;
        double length, chargingTime;
        List<String> features;
        boolean added = false; //for success messages

        while(true) {
            option = menu(menu, MENU_MAX);

            if(option == 1) { //List all cars (Detailed)
                System.out.println();
                cars.printDetailed();
            }
            
            else if(option == 2) { //List all cars (Non-Detailed)
                System.out.println();
                cars.printNonDetailed();
            }

            else if(option == 3) { //Filter cars
                int filterType = menu(filterMenu, FILTER_MENU_MAX);
                switch(filterType) {
                    case 1: //price
                        System.out.print("Enter the maximum price of cars you wish to see (upper limit): ");
                        try {
                            int max = keyboard.nextInt();
                            System.out.print("Enter the minimum price of cars you wish to see (lower limit): ");
                            int min = keyboard.nextInt();
                            cars.displayFilteredPrice(min, max);
                            System.out.println();
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.out.println("\nInvalid input. Integer expected. Returning to previous menu...\n");
                        }
                        
                        break;
                    case 2: //company
                        System.out.print("Enter the company for filtering: ");
                        keyboard.nextLine();
                        company = keyboard.nextLine();
                        cars.displayFilteredCompany(company);
                        System.out.println();
                        break;
                    case 3: //year
                        System.out.println("Enter year: ");
                        try {
                            year = keyboard.nextInt();
                        }catch(InputMismatchException e) {
                            System.out.println("\nInvalid input. Integer expected. Returning to previous menu...\n");
                            break;
                        }
                        cars.displayFilteredYear(year);
                        break;
                    case 4: //seats
                        System.out.print("Enter number of seats: ");
                        if(keyboard.hasNextInt()) {
                            numSeats = keyboard.nextInt();
                            cars.displayFilteredSeats(numSeats);
                            System.out.println();
                        }
                        else {
                            keyboard.nextLine();
                            System.err.println("Invalid. Integer expected. Returning to previous menu...");
                        }
                        break;
                    case 5: //drive
                        System.out.print("Enter drive: ");
                        keyboard.nextLine();
                        drive = keyboard.nextLine();
                        cars.displayFilteredDrive(drive);
                        break;
                    case 6: //resale value after 4 years
                        System.out.print("Enter minimum value: ");
                        try {
                            resaleValueYears = keyboard.nextInt();
                            cars.displayFilteredResaleValueYears(resaleValueYears);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid input. Integer expected. Returning to previous menu...\n");
                        }
                        break;
                    case 7: //resale value after 60,000 km
                        try {
                            resaleValueDistance = keyboard.nextInt();
                            cars.displayFilteredResaleValueDistance(resaleValueDistance);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid input. Integer expected. Returning to previous menu...\n");
                        }
                        break;
                    case 8: //Feature
                        System.out.print("Enter feature: ");
                        keyboard.nextLine();
                        String feature = keyboard.nextLine();
                        cars.displayFilteredFeature(feature);
                        break;
                    case 9: //Return
                        System.out.println("Returning to previous menu...");
                        break;                    
                }
            }

            else if(option == 4) { //Add car
                System.out.println("What type of car would you like to add?");
                System.out.print(carMenu);
                int carType = keyboard.nextInt();
                keyboard.nextLine(); //pickup empty line
                //validate carType entered
                if (carType == ADD_CAR_RETURN) {
                    continue;
                }
                if(carType < 1 || carType > CAR_MENU_MAX) {
                    System.err.println("Invalid choice. Returning to previous menu...");
                    continue;
                }

                System.out.print("Company: ");
                company = keyboard.nextLine();
                System.out.print("Model: ");
                model = keyboard.nextLine();
                System.out.print("Year: ");
                year = keyboard.nextInt();
                System.out.print("Price: ");
                price = keyboard.nextInt();
                System.out.print("Resale Value after 4 years: ");
                resaleValueYears = keyboard.nextInt();
                System.out.print("Resale Value after 60,000 km: ");
                resaleValueDistance = keyboard.nextInt();
                System.out.print("Number of seats: ");
                numSeats = keyboard.nextInt();
                System.out.print("Drive (ex: AWD): ");
                drive = keyboard.next();
                System.out.print("Length (if unknown, enter -1): ");
                length = keyboard.nextDouble();
                features = getFeatures();

                if(carType == TYPE_FUEL) {
                    System.out.print("Fuel Mileage: ");
                    fuelMileage = keyboard.nextInt();
                    added = cars.addFuelCar(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, fuelMileage);   
                }

                else if(carType == TYPE_PHEV) {
                    System.out.print("Fuel Mileage: ");
                    fuelMileage = keyboard.nextInt();
                    System.out.print("Electricity Mileage: ");
                    electricityMileage = keyboard.nextInt();
                    System.out.print("Charging Time: ");
                    chargingTime = keyboard.nextDouble();
                    System.out.print("Charger: ");
                    charger = keyboard.nextLine();
                    added = cars.addPHEV(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, fuelMileage, electricityMileage, chargingTime, charger);
                }

                else if(carType == TYPE_HEV) {
                    System.out.print("Fuel Mileage: ");
                    fuelMileage = keyboard.nextInt();
                    System.out.print("Electricity Mileage: ");
                    electricityMileage = keyboard.nextInt();
                    added = cars.addHEV(company, model, year, price, resaleValueYears, resaleValueDistance, numSeats, drive, length, features, fuelMileage, electricityMileage);                 
                }

                if(added) System.out.println("\nCar added successfully!\n");

            }
            else if(option == 5) { //Edit car
                boolean edited = false;
                System.out.println("Enter name of car to edit:");
                name = keyboard.nextLine();
                if(!cars.hasCar(name)) {
                    System.out.println("\nSorry, this car does not exist. Returning...\n");
                    continue;
                }
                int editOption = menu(editMenu, EDIT_MENU_MAX);
                switch(editOption) {
                    case EDIT_COMPANY:
                        System.out.print("Enter new company: ");
                        company = keyboard.nextLine();
                        edited = cars.editCompany(name, company);
                        break;
                    case EDIT_MODEL:
                        System.out.print("Enter new model: ");
                        model = keyboard.nextLine();
                        edited = cars.editModel(name, model);
                        break;
                    case EDIT_YEAR:
                        System.out.print("Enter new year:");
                        try {
                            year = keyboard.nextInt();
                            edited = cars.editYear(name, year);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Integer expected. Returning...");
                        }
                        break;
                    case EDIT_PRICE:
                        System.out.print("Enter new price:");
                        try {
                            price = keyboard.nextInt();
                            edited = cars.editPrice(name, price);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Integer expected. Returning...");
                        }
                        break;
                    case EDIT_RESALE_YEAR:
                        System.out.print("Enter new resale value (after 4 years):");
                        try {
                            resaleValueYears = keyboard.nextInt();
                            edited = cars.editResaleValueYears(name, resaleValueYears);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Integer expected. Returning...");
                        }
                        break;
                    case EDIT_RESALE_DISTANCE:
                        System.out.print("Enter new resale value (after 60,000 km):");
                        try {
                            resaleValueDistance = keyboard.nextInt();
                            edited = cars.editResaleValueDistance(name, resaleValueDistance);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Integer expected. Returning...");
                        }
                        break;
                    case EDIT_NUM_SEATS:
                        System.out.print("Enter new number of seats:");
                        try {
                            numSeats = keyboard.nextInt();
                            edited = cars.editYear(name, numSeats);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Integer expected. Returning...");
                        }
                        break;
                    case EDIT_DRIVE:
                        System.out.print("Enter new drive: ");
                        keyboard.nextLine();
                        drive = keyboard.nextLine();
                        edited = cars.editDrive(name, drive);
                        break;
                    case EDIT_LENGTH:
                        System.out.print("Enter new length: ");
                        try {
                            length = keyboard.nextDouble();
                            edited = cars.editLength(name, length);
                        }catch(InputMismatchException e) {
                            keyboard.nextLine();
                            System.err.println("\nInvalid. Decimal expected. Returning...");
                        }
                        break;
                    case EDIT_FEATURES:
                        //TODO
                    case EDIT_ELECTRICITY_MILEAGE:
                        
                    case EDIT_FUEL_MILEAGE:
                    case EDIT_CHARGING_TIME:
                    case EDIT_CHARGER_TYPE:

                    if(edited) System.out.println("\nCar edited successfully!\n");
                    else {
                        System.out.println("\nUnable to edit car. Car does not exist, or doesn't have this attribute.");
                    }
                }

                //Company
                
                //Model
                
                /*
                //Year
                cars.editYear(name, year);
                //Price
                cars.editPrice(name, price);
                //ResaleValueYears
                cars.editResaleValueYears(name, resaleValueYears);
                //ResaleValueDistance
                cars.editResaleValueDistance(name, resaleValueDistance);
                //numSeats
                cars.editNumSeats(name, numSeats);
                //Drive
                cars.editDrive(name, drive);
                //Length
                cars.editLength(name, length);
                //Features
                //TODO: refine
                cars.editFeatures(name, features);
                */

            }
            else if(option == 6) { //Delete car
                System.out.println("Enter the company, model and year of the car to delete (0 to return): ");
                keyboard.nextLine();
                name = keyboard.nextLine();
                boolean removed = cars.remove(name);
                if(removed) System.out.println("\nCar removed successfully!\n");
                else {
                    System.out.println("\nThe car entered does not exist. Nothing deleted.\n");
                }

            }
            else if(option == 7) { //View favorites

            }
            else if(option == 8) { //Add to favorites

            }

            else if(option == 9) { //Edit favorites
                
            }

            else if(option == 10) { //Save and Exit
                System.out.println("Saving...");
                //Save all cars to file
                try {
                    cars.save(filename);
                    System.out.println("Cars saved successfully!\n");
                    System.out.println("Exiting...");
                    System.exit(0);
                }catch(FileNotFoundException e) {
                    System.err.println("Unable to save cars. File not found.");
                }catch(IOException e) {
                    System.err.println("Unable to save cars. IOException.");
                }   
            }
            else {
                System.out.println("Invalid menu option. Please try again");
            }


        }
    }
    
    //---------------- menu function
    public static int menu(String menu, int max) {
        /* Prints menu, validates input, returns valid input */
        int choice;
        while(true) {
            System.out.print(menu);
            try {
                choice = keyboard.nextInt();
                if(choice < 1 || choice > max) {
                    System.err.println("\nInvalid. Enter a number from 1 to " + max + ". \n");
                }
                else {
                    return choice;
                }
            }catch(InputMismatchException e) {
                keyboard.nextLine();
                System.err.println("\nInvalid input. Enter an integer.\n");
            }
            
        }
    }
    //---------------- other functions
    public static List<String> getFeatures() {
      List<String> features = new ArrayList<>();
      String feature;
      keyboard.nextLine(); //pickup discarded line
      while(true) {
        System.out.print("Enter feature (0 to exit): ");
        feature = keyboard.nextLine();
        if(feature.equals("0")) break;
        features.add(feature);
      }
      return features;  
    }
}
