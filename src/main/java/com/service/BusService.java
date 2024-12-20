package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BusDAO;
import com.model.Bus;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusDAO busDAO;

    public Bus addBus(Bus bus) {
        return busDAO.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busDAO.findAll();
    }
    public Bus updateBus(Bus updatedBus) {
        return busDAO.save(updatedBus);
    }
 
    public Bus getBusById(Integer busId) {
        return busDAO.findById(busId).orElse(null);
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
