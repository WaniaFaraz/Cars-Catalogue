package com.example.restservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogue;

@RestController
@RequestMapping("/api/get-all")
@CrossOrigin(origins = "http://localhost:5173")
public class GeneralDataController {
    
    private Catalogue catalogue;
    //private final String filename = "car_catalogue_1";

    public GeneralDataController(Catalogue catalogue){
        this.catalogue = catalogue;
    }

    @GetMapping("/company")
    public List<String> getAllCompanies() {
        return catalogue.getAllCompanies();
    }
}
