package com.filterTest;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import com.filter.JwtFilter;
 
@SpringBootTest
@AutoConfigureMockMvc
public class jwtFilterTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Configuration
    static class TestConfig {
        @Bean
        public JwtFilter jwtFilter() {
            return new JwtFilter(); // Provide your JwtFilter bean here
        }
    }
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    @WithMockUser
    public void testDoFilterWithValidToken() throws Exception {
        String token = "Bearer valid.jwt.token";
 
        mockMvc.perform(get("/api/someEndpoint")
                .header(HttpHeaders.AUTHORIZATION, token));
                //.andExpect(status().isOk());
        // Additional assertions can be made based on your context.
    }
 
    @Test
    public void testDoFilterWithInvalidToken() throws Exception {
        String token = "Bearer invalid.jwt.token";
 
        // Simulate a request with an invalid token
        mockMvc.perform(get("/api/someEndpoint")
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isUnauthorized()); // Expecting an Unauthorized status
                //.andExpect(content().json("{\"error\": \"Invalid token2\"}")); // Expecting the specific error message
    }
 
    @Test
    public void testDoFilterWithoutToken() throws Exception {
        // Simulate a request without an Authorization token
        mockMvc.perform(get("/api/someEndpoint"))
                .andExpect(status().isUnauthorized()); // Expecting an Unauthorized status
                //.andExpect(content().json("{\"error\": \"Invalid token3\"}")); // Expecting the specific error message
    }
 
 
    @Test
    public void testDoFilterWithOptionsRequest() throws Exception {
        mockMvc.perform(options("/api/someEndpoint"));
              //  .andExpect(status().isOk());
    }
}
