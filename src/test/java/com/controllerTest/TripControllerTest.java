//package com.controllerTest;
//
//import com.model.Trip;
//import com.service.TripService;
//import com.service.BusService;
//import com.service.DriverService;
//import com.service.RouteService;
//import com.controller.TripController;
//import com.dao.BusDAO;
//import com.dao.RouteDAO;
//import com.model.Bus;
//import com.model.Driver;
//import com.model.Route;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@WebMvcTest(TripController.class)
//public class TripControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private TripService tripService;
//
//    @Mock
//    private RouteService routeService;
//
//    @Mock
//    private BusService busService;
//
//    @Mock
//    private BusDAO busDAO;
//
//    @Mock
//    private RouteDAO routeRepo;
//
//    @Mock
//    private DriverService driverService;
//
//    @InjectMocks
//    private TripController tripController;
//
//    private Trip trip;
//    private Bus bus;
//    private Route route;
//    private Driver driver;
//
//    @BeforeEach
//    public void setup() {
//        // Initialize mock data
//        bus = new Bus(); // Add properties as per your model
//        route = new Route(); // Add properties as per your model
//        driver = new Driver(); // Add properties as per your model
//
//        trip = new Trip();
//        trip.setId(1);
//        trip.setBus(bus);
//        trip.setRoute(route);
//        trip.setDriver(driver);
//    }
//
//    @Test
//    public void testCreateTrip() throws Exception {
//        when(busService.getBusById(anyInt())).thenReturn(bus);
//        when(routeService.findByRouteId(anyInt())).thenReturn(Optional.of(route));
//        when(driverService.getDriverById(anyInt())).thenReturn(Optional.of(driver));
//        when(tripService.saveTrip(any(Trip.class))).thenReturn(trip);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/trips/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"departureTime\": \"2025-01-01T10:00:00\",\n" +
//                        "  \"availableSeats\": 40,\n" +
//                        "  \"fare\": 500.00,\n" +
//                        "  \"boardingAddressId\": 1,\n" +
//                        "  \"droppingAddressId\": 2,\n" +
//                        "  \"arrivalTime\": \"2025-01-01T12:00:00\",\n" +
//                        "  \"tripDate\": \"2025-01-01T10:00:00\",\n" +
//                        "  \"route\": { \"routeId\": 1 },\n" +
//                        "  \"bus\": { \"busId\": 1 },\n" +
//                        "  \"driver\": { \"driverId\": 1 }\n" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Record Created Successfully"));
//    }
//
//    @Test
//    public void testGetAllTrips() throws Exception {
//        when(tripService.getAllTrips()).thenReturn(List.of(trip));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/trips/get")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1));
//    }
//
//    @Test
//    public void testGetTripById() throws Exception {
//        when(tripService.getTripById(anyInt())).thenReturn(trip);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/trips/{trip_id}", 1)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1));
//    }
//
//    @Test
//    public void testUpdateTrip() throws Exception {
//        when(tripService.saveTrip(any(Trip.class))).thenReturn(trip);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .put("/api/trips/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"id\": 1,\n" +
//                        "  \"departureTime\": \"2025-01-01T10:00:00\",\n" +
//                        "  \"availableSeats\": 40,\n" +
//                        "  \"fare\": 500.00,\n" +
//                        "  \"boardingAddressId\": 1,\n" +
//                        "  \"droppingAddressId\": 2,\n" +
//                        "  \"arrivalTime\": \"2025-01-01T12:00:00\",\n" +
//                        "  \"tripDate\": \"2025-01-01T10:00:00\",\n" +
//                        "  \"route\": { \"routeId\": 1 },\n" +
//                        "  \"bus\": { \"busId\": 1 },\n" +
//                        "  \"driver\": { \"driverId\": 1 }\n" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Record Updated Successfully"));
//    }
//
//    @Test
//    public void testDeleteTrip() throws Exception {
//        doNothing().when(tripService).deleteTrip(anyInt());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/api/trips/{trip_id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Record Deleted Successfully"));
//    }
//
//    @Test
//    public void testSearchByFromCity() throws Exception {
//        when(tripService.searchByFromCity(anyString())).thenReturn(List.of(trip));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/trips/from_city/{from_city}", "CityA")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1));
//    }
//
//    @Test
//    public void testSearchByToCity() throws Exception {
//        when(tripService.searchByToCity(anyString())).thenReturn(List.of(trip));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/trips/to_city/{to_city}", "CityB")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1));
//    }
//}
