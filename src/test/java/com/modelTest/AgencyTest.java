package com.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.model.Agency;
import com.model.AgencyOffice;

public class AgencyTest {

    @Test
    void testAgencyDefaultConstructor() {
        Agency agency = new Agency();
        assertNotNull(agency);
    }

    @Test
    void testAgencyParameterizedConstructor() {
        AgencyOffice office = new AgencyOffice();
        office.setOfficeId(1);
        office.setOfficeMail("office@example.com");
        office.setOfficeContactPersonName("John Doe");
        office.setOfficeContactNumber("1234567890");

        List<AgencyOffice> offices = Arrays.asList(office);

        Agency agency = new Agency(1, "Test Agency", "Jane Doe", "test@example.com", "9876543210", offices);

        assertNotNull(agency);
        assertEquals(1, agency.getAgencyId());
        assertEquals("Test Agency", agency.getName());
        assertEquals("Jane Doe", agency.getContactPersonName());
        assertEquals("test@example.com", agency.getEmail());
        assertEquals("9876543210", agency.getPhone());
        assertEquals(1, agency.getOffices().size());
    }

    @Test
    void testGettersAndSetters() {
        Agency agency = new Agency();

        agency.setAgencyId(1);
        agency.setName("Sample Agency");
        agency.setContactPersonName("John Smith");
        agency.setEmail("sample@example.com");
        agency.setPhone("1234567890");

        AgencyOffice office = new AgencyOffice();
        office.setOfficeId(2);
        office.setOfficeMail("office2@example.com");
        office.setOfficeContactPersonName("Jane Doe");
        office.setOfficeContactNumber("0987654321");

        agency.setOffices(Arrays.asList(office));

        assertEquals(1, agency.getAgencyId());
        assertEquals("Sample Agency", agency.getName());
        assertEquals("John Smith", agency.getContactPersonName());
        assertEquals("sample@example.com", agency.getEmail());
        assertEquals("1234567890", agency.getPhone());
        assertNotNull(agency.getOffices());
        assertEquals(1, agency.getOffices().size());
        assertEquals(2, agency.getOffices().get(0).getOfficeId());
    }

    @Test
    void testAddMultipleOffices() {
        Agency agency = new Agency();

        AgencyOffice office1 = new AgencyOffice();
        office1.setOfficeId(1);
        office1.setOfficeMail("office1@example.com");
        office1.setOfficeContactPersonName("Contact 1");
        office1.setOfficeContactNumber("1111111111");

        AgencyOffice office2 = new AgencyOffice();
        office2.setOfficeId(2);
        office2.setOfficeMail("office2@example.com");
        office2.setOfficeContactPersonName("Contact 2");
        office2.setOfficeContactNumber("2222222222");

        List<AgencyOffice> offices = Arrays.asList(office1, office2);
        agency.setOffices(offices);

        assertNotNull(agency.getOffices());
        assertEquals(2, agency.getOffices().size());
        assertEquals("office1@example.com", agency.getOffices().get(0).getOfficeMail());
        assertEquals("office2@example.com", agency.getOffices().get(1).getOfficeMail());
    }
}
