//package com.controllerTest;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.controller.BusController;
//import com.model.Bus;
//import com.service.BusService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//public class BusControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private BusService busService;
//
//    @InjectMocks
//    private BusController busController;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(busController).build();
//    }
//
//    @Test
//    void testCreateBus() throws Exception {
//        Bus bus = new Bus();
//        bus.setBusId(1);
//        bus.setRegistrationNumber("AB123");
//        bus.setCapacity(40);
//        bus.setType("Luxury");
//
//        when(busService.addBus(any(Bus.class))).thenReturn(bus);
//
//        mockMvc.perform(post("/api/buses")
//                .contentType("application/json")
//                .content("{ \"registrationNumber\": \"AB123\", \"capacity\": 40, \"type\": \"Luxury\" }"))
//                .andExpect(status().isCreated())
//                .andExpect(content().string("Record Created Successfully"));
//    }
//
//    @Test
//    void testGetAllBuses() throws Exception {
//        Bus bus1 = new Bus(1, null, "AB123", 40, "Luxury");
//        Bus bus2 = new Bus(2, null, "CD456", 50, "Economy");
//        List<Bus> buses = Arrays.asList(bus1, bus2);
//
//        when(busService.getAllBuses()).thenReturn(buses);
//
//        mockMvc.perform(get("/api/buses"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].registrationNumber").value("AB123"))
//                .andExpect(jsonPath("$[1].registrationNumber").value("CD456"));
//    }
//
//    @Test
//    void testUpdateBus() throws Exception {
//        Bus bus = new Bus(1, null, "AB123", 40, "Luxury");
//        when(busService.updateBus(eq(1), any(Bus.class))).thenReturn(bus);
//
//        mockMvc.perform(put("/api/buses/1")
//                .contentType("application/json")
//                .content("{ \"registrationNumber\": \"AB123\", \"capacity\": 40, \"type\": \"Luxury\" }"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Record Updated Successfully"));
//    }
//
//    @Test
//    void testGetBusById() throws Exception {
//        Bus bus = new Bus(1, null, "AB123", 40, "Luxury");
//        when(busService.getBusById(1)).thenReturn(bus);
//
//        mockMvc.perform(get("/api/buses/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.registrationNumber").value("AB123"))
//                .andExpect(jsonPath("$.capacity").value(40));
//    }
//
//    @Test
//    void testDeleteBus() throws Exception {
//        Bus bus = new Bus(1, null, "AB123", 40, "Luxury");
//        when(busService.getBusById(1)).thenReturn(bus);
//
//        mockMvc.perform(delete("/api/buses/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Record Deleted Successfully"));
//    }
//
//    @Test
//    void testDeleteBusNotFound() throws Exception {
//        when(busService.getBusById(999)).thenReturn(null);
//
//        mockMvc.perform(delete("/api/buses/999"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("Record Not Found"));
//    }
//
//    @Test
//    void testGetBusesByOfficeId() throws Exception {
//        Bus bus1 = new Bus(1, null, "AB123", 40, "Luxury");
//        Bus bus2 = new Bus(2, null, "CD456", 50, "Economy");
//        List<Bus> buses = Arrays.asList(bus1, bus2);
//
//        when(busService.getBusesByOfficeId(1)).thenReturn(buses);
//
//        mockMvc.perform(get("/api/buses/office/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].registrationNumber").value("AB123"))
//                .andExpect(jsonPath("$[1].registrationNumber").value("CD456"));
//    }
//}
