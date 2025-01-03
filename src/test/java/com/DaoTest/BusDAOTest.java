package com.DaoTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.dao.BusDAO;
import com.model.AgencyOffice;
import com.model.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class) // Use Mockito extension for JUnit 5
public class BusDAOTest {
    @Mock
    private BusDAO busDAO;
    private AgencyOffice agencyOffice;
    private Bus bus;
    @BeforeEach
    public void setUp() {
        // Create sample data for AgencyOffice
        agencyOffice = new AgencyOffice();
        agencyOffice.setOfficeId(1);
        // Set other properties of agencyOffice as needed
        // Create sample data for Bus
        bus = new Bus();
        bus.setAgencyOffice(agencyOffice);
        bus.setRegistrationNumber("ABC123");
        bus.setCapacity(50);
        bus.setType("Luxury");
    }
    @Test
    public void testSaveBus() {
        // Mock DAO behavior
        when(busDAO.save(bus)).thenReturn(bus);
        // Test method
        Bus savedBus = busDAO.save(bus);
        // Verify results
        assertNotNull(savedBus);
        assertEquals(bus.getRegistrationNumber(), savedBus.getRegistrationNumber());
        assertEquals(bus.getCapacity(), savedBus.getCapacity());
        // Verify mock interaction
        verify(busDAO, times(1)).save(bus);
    }
    @Test
    public void testFindByAgencyOffice_OfficeId() {
        // Mock DAO behavior
        when(busDAO.findByagencyOffice_OfficeId(1)).thenReturn(List.of(bus));
        // Save the bus first to ensure it's associated with the agency office
        busDAO.save(bus);
        // Test method
        List<Bus> buses = busDAO.findByagencyOffice_OfficeId(1);
        // Verify results
        assertNotNull(buses);
        assertFalse(buses.isEmpty());
        assertEquals(1, buses.size());
        // Verify mock interaction
        verify(busDAO, times(1)).findByagencyOffice_OfficeId(1);
    }
}