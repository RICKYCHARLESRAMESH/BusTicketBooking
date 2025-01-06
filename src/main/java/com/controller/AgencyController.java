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
import com.service.AgencyService;
 
/**
* AgencyController is a RESTful controller responsible for managing agency-related operations.
* It provides endpoints to retrieve, add, update, and delete agency information.
*
* URL: /api/agencies
*/
@RestController // Marks this class as a REST controller
@RequestMapping("/api/agencies") // Base URL mapping for all endpoints in this controller
public class AgencyController {
 
    @Autowired // Automatically injects the AgencyService dependency
    private AgencyService agencyService;
 
    // Getter for AgencyService
    public AgencyService getAgenciesService() {
        return agencyService;
    }
 
    // Setter for AgencyService
    public void setAgencyService(AgencyService agencyService) {
        this.agencyService = agencyService;
    }
 
    /**
     * Retrieves all agencies from the system.
     *
     * @return A ResponseEntity containing a list of all Agency objects with HTTP status 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Agency>> getAllAgencies() {
        List<Agency> agency = agencyService.getAllAgencies(); // Fetch all agencies from the service
        return new ResponseEntity<>(agency, HttpStatus.OK); // Return the list with HTTP 200 OK
    }
 
    /**
     * Adds a new agency to the system.
     *
     * @param agency The Agency object containing the details of the agency to be added.
     * @return A ResponseEntity with a success message and HTTP status 201 Created.
     * @throws CustomException if the agency name is null or empty.
     */
    @PostMapping("/addAgency")
    public ResponseEntity<String> addAgency(@RequestBody Agency agency) {
        // Validate agency details
        if (agency == null || agency.getName() == null || agency.getName().isEmpty()) {
            throw new CustomException("POSTFAILS", "Agency name cannot be null or empty"); // Throw exception if validation fails
        }
        String response = agencyService.addAgency(agency); // Call the service to add the agency
        return ResponseEntity.status(201).body(response); // Return a success message with HTTP 201 Created
    }
 
    /**
     * Retrieves agency details by its ID.
     *
     * @param agencyId The ID of the agency to be retrieved.
     * @return A ResponseEntity containing the Agency object if found, with HTTP status 200 OK.
     * @throws CustomException if the agency is not found.
     */
    @GetMapping("/{agencyId}")
    public ResponseEntity<Agency> getAgencyById(@PathVariable int agencyId) {
        Optional<Agency> agency = agencyService.getAgencyById(agencyId); // Fetch agency by ID
        if (agency.isEmpty()) {
            throw new CustomException("NOTFOUND", "Agency not found with ID: " + agencyId); // Throw exception if agency not found
        }
        return ResponseEntity.ok(agency.get()); // Return the agency details with HTTP 200 OK
    }
 
    /**
     * Retrieves agency details by its name.
     *
     * @param agencyName The name of the agency to be retrieved.
     * @return A ResponseEntity containing the Agency object if found, with HTTP status 200 OK.
     * @throws CustomException if the agency is not found.
     */
    @GetMapping("/name/{agencyName}")
    public ResponseEntity<Agency> getAgencyByName(@PathVariable String agencyName) {
        Optional<Agency> agency = agencyService.getAgencyByName(agencyName); // Fetch agency by name
        if (agency.isEmpty()) {
            throw new CustomException("NOTFOUND", "Agency not found with Name: " + agencyName); // Throw exception if agency not found
        }
        return ResponseEntity.ok(agency.get()); // Return the agency details with HTTP 200 OK
    }
 
    /**
     * Retrieves agency details by contact person name.
     *
     * @param contactPersonName The name of the contact person.
     * @return A ResponseEntity containing the Agency object if found, with HTTP status 200 OK.
     * @throws CustomException if the agency is not found.
     */
    @GetMapping("/contactPerson/{contactPersonName}")
    public ResponseEntity<Agency> getAgencyByContactPersonName(@PathVariable String contactPersonName) {
        Optional<Agency> agency = agencyService.getAgencyByContactPersonName(contactPersonName); // Fetch agency by contact person name
        if (agency.isEmpty()) {
            throw new CustomException("NOTFOUND", "Agency not found with Contact Person Name: " + contactPersonName); // Throw exception if agency not found
        }
        return ResponseEntity.ok(agency.get()); // Return the agency details with HTTP 200 OK
    }
 
    /**
     * Updates agency details.
     *
     * @param agencyId The ID of the agency to be updated.
     * @param agency The Agency object containing the updated details.
     * @return A ResponseEntity with a success message and HTTP status 200 OK.
     * @throws CustomException if the agency ID is invalid or the agency name is null or empty.
     */
    @PutMapping("/updateAgency/{agencyId}")
    public ResponseEntity<String> updateAgency(@PathVariable int agencyId, @RequestBody Agency agency) {
        // Validate agency ID
        if (agencyId <= 0) {
            throw new CustomException("UPDATEFAILS", "Invalid agency ID: " + agencyId); // Throw exception for invalid ID
        }
        // Validate agency details
        if (agency == null || agency.getName() == null || agency.getName().isEmpty()) {
            throw new CustomException("UPDATEFAILS", "Agency name cannot be null or empty"); // Throw exception for invalid data
        }
        String response = agencyService.updateAgency(agencyId, agency); // Call the service to update the agency
        return ResponseEntity.ok(response); // Return a success message with HTTP 200 OK
    }
 
    /**
     * Retrieves all offices for a specific agency.
     *
     * @param agencyId The ID of the agency.
     * @return A ResponseEntity containing a list of AgencyOffice objects with HTTP status 200 OK.
     * @throws CustomException if the agency ID is invalid or no offices are found.
     */
    @GetMapping("/offices/{agencyId}")
    public ResponseEntity<List<AgencyOffice>> getOfficesByAgencyId(@PathVariable int agencyId) {
        // Validate agency ID
        if (agencyId <= 0) {
            throw new CustomException("FETCHFAILS", "Invalid agency ID: " + agencyId); // Throw exception for invalid ID
        }
        List<AgencyOffice> offices = agencyService.getOfficesByAgencyId(agencyId); // Fetch offices by agency ID
        if (offices.isEmpty()) {
            throw new CustomException("NOTFOUND", "No offices found for agency ID: " + agencyId); // Throw exception if no offices found
        }
        return ResponseEntity.ok(offices); // Return the list of offices with HTTP 200 OK
    }
 
    /**
     * Retrieves a specific office by agency ID and office ID.
     *
     * @param agencyId The ID of the agency.
     * @param officeId The ID of the office.
     * @return A ResponseEntity containing the AgencyOffice object if found, with HTTP status 200 OK.
     * @throws CustomException if the agency or office ID is invalid or the office is not found.
     */
    @GetMapping("/offices/{agencyId}/{officeId}")
    public ResponseEntity<AgencyOffice> getOfficeById(@PathVariable int agencyId, @PathVariable int officeId) {
        // Validate agency and office IDs
        if (agencyId <= 0 || officeId <= 0) {
            throw new CustomException("FETCHFAILS", "Invalid agency ID or office ID"); // Throw exception for invalid IDs
        }
        AgencyOffice office = agencyService.getOfficeById(agencyId, officeId); // Fetch office by agency and office ID
        if (office == null) {
            throw new CustomException("NOTFOUND", "Office not found with agency ID: " + agencyId + " and office ID: " + officeId); // Throw exception if office not found
        }
        return ResponseEntity.ok(office); // Return the office details with HTTP 200 OK
    }
}