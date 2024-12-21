package com.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Agency;
import com.model.AgencyOffice;
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
	

	@PostMapping("/addAgency")//---------------------------------------------------1----------------------------
    public ResponseEntity<String> addAgency(@RequestBody Agency agency) {
       
        String response = agencyService.addAgency(agency);
        return ResponseEntity.status(201).body(response);  // Created status for POST
    }

	// Get Agency details by agency_id-------------------------------------------2---------------------------------
	@GetMapping("/{agencyId}")
    public ResponseEntity<Agency> getAgencyById(@PathVariable int agencyId) {
        Optional<Agency> agency = agencyService.getAgencyById(agencyId);
       
        return ResponseEntity.ok(agency.get());
    }

	@PutMapping("/updateAgency/{agencyId}") //----------------------------------3-------------------------------
    public ResponseEntity<String> updateAgency(@PathVariable int agencyId, @RequestBody Agency agency) {
        String response = agencyService.updateAgency(agencyId, agency);
        
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/offices/{agencyId}")//-----------------------------------------4----------------------------
    public ResponseEntity<List<AgencyOffice>> getOfficesByAgencyId(@PathVariable int agencyId) {
        List<AgencyOffice> offices = agencyService.getOfficesByAgencyId(agencyId);
       
        return ResponseEntity.ok(offices);
    }
	
	 @GetMapping("/offices/{agencyId}/{officeId}")//------------------------------5--------------------------
	    public ResponseEntity<AgencyOffice> getOfficeById(@PathVariable int agencyId, @PathVariable int officeId) {
	        AgencyOffice office = agencyService.getOfficeById(agencyId, officeId);
	        
	        return ResponseEntity.ok(office);
	    }
}