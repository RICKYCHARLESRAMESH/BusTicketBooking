package com.controllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.model.Route;
import com.service.RouteService;
import com.controller.RouteController;
import com.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateRoute() throws Exception {
        Route route = new Route(1, "CityA", "CityB", 2, 5);
        
        when(routeService.save(any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/api/routes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(route)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Record Created Successfully"));
    }

    @Test
    void testGetAllRoutes() throws Exception {
        Route route1 = new Route(1, "CityA", "CityB", 2, 5);
        Route route2 = new Route(2, "CityC", "CityD", 1, 4);

        when(routeService.findAll()).thenReturn(Arrays.asList(route1, route2));

        mockMvc.perform(get("/api/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value(1))
                .andExpect(jsonPath("$[1].routeId").value(2));
    }

    @Test
    void testGetRouteById() throws Exception {
        Route route = new Route(1, "CityA", "CityB", 2, 5);
        
        when(routeService.findByRouteId(1)).thenReturn(Optional.of(route));

        mockMvc.perform(get("/api/routes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1))
                .andExpect(jsonPath("$.fromCity").value("CityA"))
                .andExpect(jsonPath("$.toCity").value("CityB"));
    }

   
    @Test
    void testGetRoutesByFromCity() throws Exception {
        Route route1 = new Route(1, "CityA", "CityB", 2, 5);
        Route route2 = new Route(2, "CityA", "CityC", 1, 3);

        when(routeService.findByFromCity("CityA")).thenReturn(Arrays.asList(route1, route2));

        mockMvc.perform(get("/api/routes/from_city/CityA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fromCity").value("CityA"))
                .andExpect(jsonPath("$[1].fromCity").value("CityA"));
    }


    @Test
    void testUpdateRoute() throws Exception {
        Route route = new Route(1, "CityA", "CityB", 2, 5);
        
        when(routeService.save(any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/api/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(route)))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Updated Successfully"));
    }

    @Test
    void testDeleteRoute() throws Exception {
        doNothing().when(routeService).deleteByRouteId(1);

        mockMvc.perform(delete("/api/routes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Deleted Successfully"));
    }

   
}
