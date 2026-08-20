package com.example.restservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogue;

@RestController
@RequestMapping("/api/edit")
@CrossOrigin(origins = "http://localhost:5173")
public class EditCarsController {

    private Catalogue catalogue;

    public EditCarsController(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    @GetMapping("/company")
    public boolean editCompany(@RequestParam() String name, String company) {
        return catalogue.editCompany(name, company);
    }

    @GetMapping("/model")
    public boolean editModel(@RequestParam() String name, String model) {
        return catalogue.editModel(name, model);
    }

    @GetMapping("/year")
    public boolean editYear(@RequestParam() String name, int year) {
        return catalogue.editYear(name, year);
    }

    @GetMapping("/price")
    public boolean editPrice(@RequestParam() String name, int price) {
        return catalogue.editPrice(name, price);
    }

    @GetMapping("/resale-value-years")
    public boolean editResaleValueYears(@RequestParam() String name, int resaleValueYears) {
        return catalogue.editResaleValueYears(name, resaleValueYears);
    }

    @GetMapping("/resale-value-distance")
    public boolean editResaleValueDistance(@RequestParam() String name, int resaleValueDistance) {
        return catalogue.editResaleValueDistance(name, resaleValueDistance);
    }

    @GetMapping("/num-seats")
    public boolean editNumSeats(@RequestParam() String name, int numSeats) {
        return catalogue.editNumSeats(name, numSeats);
    }

    @GetMapping("/drive")
    public boolean editDrive(@RequestParam() String name, String drive) {
        List<String> driveList = Arrays.asList(drive.split(","));
        return catalogue.editDrive(name, driveList);
    }

    @GetMapping("/length")
    public boolean editLength(@RequestParam() String name, double length) {
        return catalogue.editLength(name, length);
    }


}
