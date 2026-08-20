package com.example.restservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogue;
import com.example.Cars.*;

@RestController
@RequestMapping("/api/filter")
@CrossOrigin(origins = "http://localhost:5173")
public class FilteringController {
    
    private Catalogue catalogue;

    public FilteringController(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    @GetMapping("/company")
    public List<Car> filterCompany(@RequestParam() String company) {
        List<Car> filteredCars = catalogue.displayFilteredCompany(company);
        return filteredCars;
    }

    @GetMapping("/price")
    public List<Car> filterPrice(@RequestParam() int min, int max) {
        List<Car> filteredCars = catalogue.displayFilteredPrice(min, max);
        return filteredCars;
    }

    @GetMapping("/feature")
    public List<Car> filterFeature(@RequestParam() String feature) {
        List<Car> filteredCars = catalogue.displayFilteredFeature(feature);
        return filteredCars;
    }

    @GetMapping("/year")
    public List<Car> filterYear(@RequestParam() int year) {
        List<Car> filteredCars = catalogue.displayFilteredYear(year);
        return filteredCars;
    }

    @GetMapping("/seat")
    public List<Car> filterSeat(@RequestParam() int numSeats) {
        List<Car> filteredCars = catalogue.displayFilteredSeats(numSeats);
        return filteredCars;
    }

    @GetMapping("/resale-value-distance")
    public List<Car> filterResaleValueDistance(@RequestParam() int resaleValueDistance) {
        List<Car> filteredCars = catalogue.displayFilteredResaleValueDistance(resaleValueDistance);
        return filteredCars;
    }

    @GetMapping("/resale-value-years")
    public List<Car> filterResaleValueYears(@RequestParam() int resaleValueYears) {
        List<Car> filteredCars = catalogue.displayFilteredResaleValueYears(resaleValueYears);
        return filteredCars;
    }
    
    @GetMapping("/drive")
    public List<Car> filterDrive(@RequestParam() String drive) {
        List<Car> filteredCars = catalogue.displayFilteredDrive(drive);
        return filteredCars;
    }


}
