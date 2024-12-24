package com.serviceTest;
import com.dao.BusDAO;
import com.model.Bus;
import com.model.AgencyOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.service.BusService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class BusServiceTest {
    @InjectMocks
    private BusService busService;
    @Mock
    private BusDAO busDAO;
    private Bus bus;
    private AgencyOffice agencyOffice;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize test data
        agencyOffice = new AgencyOffice();
        agencyOffice.setOfficeId(1);
        bus = new Bus();
        bus.setBusId(1);
        bus.setAgencyOffice(agencyOffice);
        bus.setRegistrationNumber("REG123");
        bus.setCapacity(50);
        bus.setType("Luxury");
    }
    @Test
    void testAddBus() {
        when(busDAO.save(bus)).thenReturn(bus);
        Bus result = busService.addBus(bus);
        assertNotNull(result);
        assertEquals(bus.getBusId(), result.getBusId());
        verify(busDAO, times(1)).save(bus);
    }
    @Test
    void testGetAllBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(bus);
        when(busDAO.findAll()).thenReturn(buses);
        List<Bus> result = busService.getAllBuses();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(busDAO, times(1)).findAll();
    }
    @Test
    void testGetBusById() {
        when(busDAO.findById(1)).thenReturn(Optional.of(bus));
        Bus result = busService.getBusById(1);
        assertNotNull(result);
        assertEquals(bus.getBusId(), result.getBusId());
        verify(busDAO, times(1)).findById(1);
    }
    @Test
    void testGetBusByIdNotFound() {
        when(busDAO.findById(1)).thenReturn(Optional.empty());
        Bus result = busService.getBusById(1);
        assertNull(result);
        verify(busDAO, times(1)).findById(1);
    }
    @Test
    void testUpdateBus() {
        Bus updatedBus = new Bus();
        updatedBus.setAgencyOffice(agencyOffice);
        updatedBus.setRegistrationNumber("NEW123");
        updatedBus.setCapacity(60);
        updatedBus.setType("Semi-Luxury");
        when(busDAO.findById(1)).thenReturn(Optional.of(bus));
        when(busDAO.save(any(Bus.class))).thenReturn(updatedBus);
        Bus result = busService.updateBus(1, updatedBus);
        assertNotNull(result);
        assertEquals("NEW123", result.getRegistrationNumber());
        assertEquals(60, result.getCapacity());
        verify(busDAO, times(1)).findById(1);
        verify(busDAO, times(1)).save(any(Bus.class));
    }
    @Test
    void testUpdateBusNotFound() {
        Bus updatedBus = new Bus();
        updatedBus.setAgencyOffice(agencyOffice);
        updatedBus.setRegistrationNumber("NEW123");
        updatedBus.setCapacity(60);
        updatedBus.setType("Semi-Luxury");
        when(busDAO.findById(1)).thenReturn(Optional.empty());
        Bus result = busService.updateBus(1, updatedBus);
        assertNull(result);
        verify(busDAO, times(1)).findById(1);
        verify(busDAO, times(0)).save(any(Bus.class));
    }
    @Test
    void testDeleteBus() {
        doNothing().when(busDAO).deleteById(1);
        busService.deleteBus(1);
        verify(busDAO, times(1)).deleteById(1);
    }
    @Test
    void testGetBusesByOfficeId() {
        List<Bus> buses = new ArrayList<>();
        buses.add(bus);
        when(busDAO.findByagencyOffice_OfficeId(1)).thenReturn(buses);
        List<Bus> result = busService.getBusesByOfficeId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(busDAO, times(1)).findByagencyOffice_OfficeId(1);
    }
}
