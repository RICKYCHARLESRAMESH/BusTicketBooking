package com.BusTicketBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JwtFilter;
import com.service.CustomUserDetailsService;

@EntityScan("com.model")
@SpringBootApplication(scanBasePackages="com.controller,com.service")
@EnableJpaRepositories("com.dao")
public class BusTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusTicketBookingApplication.class, args);
	}
	

	@Bean
	@DependsOn("userDetailsService")
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    CustomUserDetailsService service=userDetailsService();

	  
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    
	    provider.setUserDetailsService(service);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}

	@Bean
	public CustomUserDetailsService userDetailsService() {
		
	 
	    return new CustomUserDetailsService(); // Implement your own user details service
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); // Use a password encoder of your choice
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    System.out.println("invoked");
		http
	        .csrf().disable() // Disable CSRF protection
	        
	        .authorizeRequests()
	           
	            
	                .requestMatchers(HttpMethod.PUT,"/api/manager/register/*").hasRole("ADMIN")
	                .requestMatchers("/api/user/register").permitAll()
	                
	                .requestMatchers("/api/agencies").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST, "/api/agencies/addAgency").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies/{agencyId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies/name/{agencyName}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies/contactPerson/{contactPersonName}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/agencies/updateAgency/{agencyId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies/offices/{agencyId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/agencies/offices/{agencyId}/{officeId}").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST,"/api/buses").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/buses").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/buses/{bus_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/buses/{bus_id}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.DELETE,"/api/buses/{bus_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/buses/office/{office_id}").hasAnyRole("ADMIN","USER")
	                
	                .requestMatchers(HttpMethod.POST,"/api/customers").hasAnyRole("USER","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/{customerId}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/email/{email}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/phone/{phone}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/city/{city}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/country/{country}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.PUT,"/api/customers/update/{customerId}/In").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.PUT,"/api/customers/update/{customerId}/email").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.PUT,"/api/customers").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/state/{state}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/customers/address/{customerId}").hasAnyRole("ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.PUT,"/api/customers/address/{customerId}").hasAnyRole("USER")
	                
	                .requestMatchers(HttpMethod.GET,"/api/drivers").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.POST,"/api/drivers").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/drivers/{driverId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/drivers//name/{driverName}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/drivers/{driverId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE,"/api/drivers/{driverId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/drivers/agency/{agencyId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/drivers/office/{officeId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/drivers/address/{driverId}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/drivers/address/{driverId}").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST,"/api/payment/add").hasAnyRole("USER","ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/payment/{payment_id}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/payment/").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/payment/status/{paymentStatus}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/payment/customer/{customer_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/payment/booking/{booking_id}").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST,"/api/reviews").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.GET,"/api/reviews").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/reviews/{review_id}").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.DELETE,"/api/reviews/{review_id}").hasAnyRole("USER")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/tripid/{trip_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/customerid/{customer_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/{review_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/agency/{agency_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/driver/{driver_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/reviews/office/{office_id}").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.GET,"/api/routes").hasAnyRole("USER","ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.POST,"/api/routes/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/routes/{routeId}").hasAnyRole("USER","ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/routes/from_city/{fromCity}").hasAnyRole("USER","ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/routes/to_city/{toCity}").hasAnyRole("USER","ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/routes/{from_city}/{to_city}").hasAnyRole("USER","ADMIN","DRIVER")
	                .requestMatchers(HttpMethod.PUT,"/api/routes").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE,"/api/routes/{routeId}").hasAnyRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.POST,"/api/trips/add").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/trips/get").hasAnyRole("ADMIN","USER","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/{trip_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT,"/api/trips/update").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/trips/from_city/{from_city}").hasAnyRole("ADMIN","USER","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/to_city/{to_city}").hasAnyRole("ADMIN","USER","DRIVER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/bus_type/{type}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/store/bus_type/{type}/trip_date/{trip_date}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/{from_city}/{to_city}/{trip_date}/{bus_type}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/trips/{from_city}/{to_city}/{trip_date}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.DELETE,"/api/trips/{trip_id}").hasAnyRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/api/trips/trip_date/{trip_date}").hasAnyRole("ADMIN","USER")
	                
	                
	                .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()
	                .requestMatchers(HttpMethod.POST, "/api/driver/register").permitAll()
	                .requestMatchers(HttpMethod.POST, "/api/admin/register").permitAll()
	                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
	                
	                
	                
	                .requestMatchers(HttpMethod.GET,"/api/bookings").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.GET,"/api/bookings/{id}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.POST,"/api/bookings").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.PUT,"/api/bookings/{id}").hasAnyRole("ADMIN","USER")
	                .requestMatchers(HttpMethod.DELETE,"/api/bookings/{id}").hasAnyRole("ADMIN","USER")
	   
	               
	                  

	            .anyRequest().authenticated()
	            .and()
	             .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
	           
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .sessionManagement().disable()
	            	
	            
	                
	        .authenticationManager(new ProviderManager(daoAuthenticationProvider()));
	        
	
	        
	    return http.build();
	}

}