package com.events.testservice.rest.v1.injected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.ApplicationConfig;
import com.events.testservice.rest.v1.CustomerResource;
import com.events.testservice.rest.v1.dto.CustomerDto;

/**
 * Integration test class for the customer resource endpoints.
 * @author Robert Kaczor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class TestCustomerResource {

    @Autowired
    private CustomerResource customerResource;
	
    @Test
    @Transactional
    public void testCreateAndFindCustomer() {
        CustomerDto createCustomer = new CustomerDto.Builder().firstName("test_first").lastName("test_last").build();
        Response createResponse = customerResource.createCustomer(createCustomer);
        assertNotNull(createResponse.getEntity());
        
        Response findResponse = customerResource.getCustomer((Long) createResponse.getEntity());
        assertNotNull(findResponse.getEntity());
        CustomerDto foundCustomer = (CustomerDto) findResponse.getEntity();
        assertEquals("test_first", foundCustomer.getFirstName());
        assertEquals("test_last", foundCustomer.getLastName());
    }
	
}
