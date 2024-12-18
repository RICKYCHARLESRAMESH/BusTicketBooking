 package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BusDAO;
import com.model.Bus;

@Service
public class BusService {

    @Autowired
    private BusDAO busDAO;

    public BusDAO getBusDAO() {
        return busDAO;
    }

    public void setBusDAO(BusDAO busDAO) {
        this.busDAO = busDAO;
    }
    public Bus addBus(Bus bus) {
        return busDAO.save(bus);
    }
    public List<Bus> getAllBuses() {
        return busDAO.findAll();
    }
    public Optional<Bus> updateBus(Integer busId, Bus busDetails) {
        Optional<Bus> existingBusOpt = busDAO.findById(busId);
        
        if (existingBusOpt.isPresent()) {
            Bus existingBus = existingBusOpt.get();
            existingBus.setAgencyOffice(busDetails.getAgencyOffice());
            existingBus.setRegistrationNumber(busDetails.getRegistrationNumber());
            existingBus.setCapacity(busDetails.getCapacity());
            existingBus.setType(busDetails.getType());
            return Optional.of(busDAO.save(existingBus));
        }
        return Optional.empty();
    }

    // Search bus details by bus_id
    public Optional<Bus> getBusById(Integer busId) {
        return busDAO.findById(busId);
    }

    public boolean deleteBus(Integer busId) {
        if (busDAO.existsById(busId)) {
            busDAO.deleteById(busId);
            return true; 
        }
        return false;
    }
    public List<Bus> getBusesByOfficeId(Integer officeId) {
        return busDAO.findByAgencyOffice_Id(officeId);
    }
}
