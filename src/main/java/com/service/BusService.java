package com.service;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Bus;
import com.dao.BusDAO;
 
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
    public Bus updateBus(Integer busId, Bus busDetails) {
        Bus existingBus = busDAO.findById(busId).orElse(null);
        if (existingBus != null) {
            existingBus.setAgencyOffice(busDetails.getAgencyOffice());
            existingBus.setRegistrationNumber(busDetails.getRegistrationNumber());
            existingBus.setCapacity(busDetails.getCapacity());
            existingBus.setType(busDetails.getType());
            return busDAO.save(existingBus);
        }
        return null;
    }
 
    public Bus getBusById(Integer busId) {
        return busDAO.findById(busId).orElse(null);
    }
 
    public void deleteBus(Integer busId) {
        busDAO.deleteById(busId);
    }
 
    public List<Bus> getBusesByOfficeId(Integer officeId) {
        return busDAO.findByagencyOffice_OfficeId(officeId);
    }
}