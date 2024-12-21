package com.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.Bus;
import com.service.BusService;
 
@RestController
@RequestMapping("/api/buses")
public class BusController {
 
    @Autowired
    private BusService busService;
 
    @PostMapping
    public ResponseEntity<String> createBus(@RequestBody Bus bus) {
        busService.addBus(bus);
        return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
    }
 
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }
 
    @PutMapping("/{bus_id}")
    public ResponseEntity<String> updateBus(@PathVariable("bus_id") Integer busId, @RequestBody Bus busDetails) {
        Bus updatedBus = busService.updateBus(busId, busDetails);
        if (updatedBus != null) {
            return new ResponseEntity<>("Record Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
 
    @GetMapping("/{bus_id}")
    public ResponseEntity<Bus> getBusById(@PathVariable("bus_id") Integer busId) {
        Bus bus = busService.getBusById(busId);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
 
    @DeleteMapping("/{bus_id}")
    public ResponseEntity<String> deleteBus(@PathVariable("bus_id") Integer busId) {
        if (busService.getBusById(busId) != null) {
            busService.deleteBus(busId);
            return new ResponseEntity<>("Record Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not Found", HttpStatus.NOT_FOUND);
    }
 
    @GetMapping("/office/{office_id}")
    public ResponseEntity<List<Bus>> getBusesByOfficeId(@PathVariable("office_id") Integer officeId) {
        List<Bus> buses = busService.getBusesByOfficeId(officeId);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }
}