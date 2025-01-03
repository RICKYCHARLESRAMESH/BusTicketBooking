//package com.DaoTest;
// 
//import com.dao.DriverDAO;
//import com.model.Driver;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import java.util.ArrayList;
//import java.util.List;
// 
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
// 
//@ExtendWith(MockitoExtension.class)
//public class DriverDAOTest {
// 
//    @Mock
//    private DriverDAO driverDAO;
// 
//    private Driver driver1;
//    private Driver driver2;
// 
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
// 
//        // Create sample data
//        driver1 = new Driver();
//        driver1.setId(1);
//        driver1.setName("John Doe");
//        driver1.setAgencyOfficeAgencyAgencyId(1);
//        driver1.setAgencyOfficeOfficeId(1);
// 
//        driver2 = new Driver();
//        driver2.setId(2);
//        driver2.setName("Jane Smith");
//        driver2.setAgencyOfficeAgencyAgencyId(1);
//        driver2.setAgencyOfficeOfficeId(2);
//    }
// 
//    @Test
//    public void testFindByAgencyOfficeAgencyAgencyId() {
//        List<Driver> mockDrivers = new ArrayList<>();
//        mockDrivers.add(driver1);
//        mockDrivers.add(driver2);
// 
//        // Mock DAO behavior
//        when(driverDAO.findByAgencyOfficeAgencyAgencyId(1)).thenReturn(mockDrivers);
// 
//        // Test method
//        List<Driver> drivers = driverDAO.findByAgencyOfficeAgencyAgencyId(1);
// 
//        // Verify results
//        assertThat(drivers).hasSize(2);
//        assertThat(drivers.get(0).getName()).isEqualTo("John Doe");
//        assertThat(drivers.get(1).getName()).isEqualTo("Jane Smith");
// 
//        // Verify mock interaction
//        verify(driverDAO, times(1)).findByAgencyOfficeAgencyAgencyId(1);
//    }
// 
//    @Test
//    public void testFindByAgencyOfficeOfficeId() {
//        List<Driver> mockDrivers = new ArrayList<>();
//        mockDrivers.add(driver1);
// 
//        // Mock DAO behavior
//        when(driverDAO.findByAgencyOfficeOfficeId(1)).thenReturn(mockDrivers);
// 
//        // Test method
//        List<Driver> drivers = driverDAO.findByAgencyOfficeOfficeId(1);
// 
//        // Verify results
//        assertThat(drivers).hasSize(1);
//        assertThat(drivers.get(0).getName()).isEqualTo("John Doe");
// 
//        // Verify mock interaction
//        verify(driverDAO, times(1)).findByAgencyOfficeOfficeId(1);
//    }
//}
