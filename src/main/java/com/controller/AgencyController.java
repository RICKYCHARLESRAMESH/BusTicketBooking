package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exception.CustomException;
import com.model.Agency;
import com.model.AgencyOffice;
import com.model.Review;
import com.service.AgencyService;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    public AgencyService getAgenciesService() {
        return agencyService;
    }

    public void setAgencyService(AgencyService agencyService) {
        this.agencyService = agencyService;
    }
    
    
    // Get all agencies
    @GetMapping
    public ResponseEntity<List<Agency>> getAllAgencies() {
        List<Agency> agency = agencyService.getAllAgencies();
        return new ResponseEntity<>(agency, HttpStatus.OK);
    }

    // Add a new agency
    @PostMapping("/addAgency")
    public ResponseEntity<String> addAgency(@RequestBody Agency agency) {
        if (agency == null || agency.getName() == null || agency.getName().isEmpty()) {
            throw new CustomException("POSTFAILS", "Agency name cannot be null or empty");
        }
        String response = agencyService.addAgency(agency);
        return ResponseEntity.status(201).body(response); // Created status for POST
    }
    

    // Get Agency details by agency ID
    @GetMapping("/{agencyId}")
    public ResponseEntity<Agency> getAgencyById(@PathVariable int agencyId) {
        Optional<Agency> agency = agencyService.getAgencyById(agencyId);
        if (agency.isEmpty()) {
            throw new CustomException("NOTFOUND", "Agency not found with ID: " + agencyId);
        }
        return ResponseEntity.ok(agency.get());
    }
    
    @GetMapping("/name/{agencyName}")
    public ResponseEntity<Agency> getAgencyByName(@PathVariable String agencyName) {
        Optional<Agency> agency = agencyService.getAgencyByName(agencyName);
        if (agency.isEmpty()) {
            throw new CustomException("NOTFOUND", "Agency not found with Name: " + agencyName);
        }
        return ResponseEntity.ok(agency.get());
    }

    // Update Agency details
    @PutMapping("/updateAgency/{agencyId}")
    public ResponseEntity<String> updateAgency(@PathVariable int agencyId, @RequestBody Agency agency) {
        if (agencyId <= 0) {
            throw new CustomException("UPDATEFAILS", "Invalid agency ID: " + agencyId);
        }
        if (agency == null || agency.getName() == null || agency.getName().isEmpty()) {
            throw new CustomException("UPDATEFAILS", "Agency name cannot be null or empty");
        }
        String response = agencyService.updateAgency(agencyId, agency);
        return ResponseEntity.ok(response);
    }

    // Get all offices for a specific agency
    @GetMapping("/offices/{agencyId}")
    public ResponseEntity<List<AgencyOffice>> getOfficesByAgencyId(@PathVariable int agencyId) {
        if (agencyId <= 0) {
            throw new CustomException("FETCHFAILS", "Invalid agency ID: " + agencyId);
        }
        List<AgencyOffice> offices = agencyService.getOfficesByAgencyId(agencyId);
        if (offices.isEmpty()) {
            throw new CustomException("NOTFOUND", "No offices found for agency ID: " + agencyId);
        }
        return ResponseEntity.ok(offices);
    }

    // Get a specific office by agency ID and office ID
    @GetMapping("/offices/{agencyId}/{officeId}")
    public ResponseEntity<AgencyOffice> getOfficeById(@PathVariable int agencyId, @PathVariable int officeId) {
        if (agencyId <= 0 || officeId <= 0) {
            throw new CustomException("FETCHFAILS", "Invalid agency ID or office ID");
        }
        AgencyOffice office = agencyService.getOfficeById(agencyId, officeId);
        if (office == null) {
            throw new CustomException("NOTFOUND", "Office not found with agency ID: " + agencyId + " and office ID: " + officeId);
        }
        return ResponseEntity.ok(office);
    }
}
