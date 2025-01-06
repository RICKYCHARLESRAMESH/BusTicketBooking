package com.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.Bus;
import com.service.BusService;
 
/**
* BusController is a REST controller that handles CRUD operations for Bus entities.
* It provides endpoints to create, retrieve, update, and delete bus records.
*/
@RestController
@RequestMapping("/api/buses")
public class BusController {
 
    @Autowired
    private BusService busService;
 
    /**
     * Creates a new bus record.
     *
     * @param bus The Bus object to be created.
     * @return ResponseEntity containing a success message with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> createBus(@RequestBody Bus bus) {
        busService.addBus(bus);
        return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
    }
 
    /**
     * Retrieves all bus records.
     *
     * @return ResponseEntity containing a list of Bus objects with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }
 
    /**
     * Updates an existing bus record by ID.
     *
     * @param busId      The ID of the bus to be updated.
     * @param busDetails The Bus object containing updated details.
     * @return ResponseEntity containing a success message if updated, or an error message with HTTP status 404 (Not Found) if not found.
     */
    @PutMapping("/{bus_id}")
    public ResponseEntity<String> updateBus(@PathVariable("bus_id") Integer busId, @RequestBody Bus busDetails) {
        Bus updatedBus = busService.updateBus(busId, busDetails);
        if (updatedBus != null) {
            return new ResponseEntity<>("Record Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
 
    /**
     * Retrieves a specific bus record by ID.
     *
     * @param busId The ID of the bus to be retrieved.
     * @return ResponseEntity containing the Bus object if found, or HTTP status 404 (Not Found) if not found.
     */
    @GetMapping("/{bus_id}")
    public ResponseEntity<Bus> getBusById(@PathVariable("bus_id") Integer busId) {
        Bus bus = busService.getBusById(busId);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
    /**
     * Deletes a specific bus record by ID.
     *
     * @param busId The ID of the bus to be deleted.
     * @return ResponseEntity containing a success message if deleted, or an error message with HTTP status 404 (Not Found) if not found.
     */
    @DeleteMapping("/{bus_id}")
    public ResponseEntity<String> deleteBus(@PathVariable("bus_id") Integer busId) {
        if (busService.getBusById(busId) != null) {
            busService.deleteBus(busId);
            return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
}
