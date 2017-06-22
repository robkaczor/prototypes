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
import com.events.testservice.rest.v1.OrderResource;
import com.events.testservice.rest.v1.ProductResource;
import com.events.testservice.rest.v1.dto.CustomerDto;
import com.events.testservice.rest.v1.dto.OrderLineDto;
import com.events.testservice.rest.v1.dto.ProductDto;

/**
 * Integration test class for the order resource endpoints.
 * @author Robert Kaczor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class TestOrderResource {

    @Autowired
    private ProductResource productResource;
	
    @Autowired
    private CustomerResource customerResource;
	
    @Autowired
    private OrderResource orderResource;
	
    @Test
    @Transactional
    public void testCreateAndFindOrder() {
    	//create customer
        CustomerDto createCustomer = new CustomerDto.Builder().firstName("test_first").lastName("test_last").build();
        Response createCustomerResponse = customerResource.createCustomer(createCustomer);
        assertNotNull(createCustomerResponse.getEntity());
        
        //get customer
        Response findCustomerResponse = customerResource.getCustomer((Long) createCustomerResponse.getEntity());
        assertNotNull(findCustomerResponse.getEntity());
        CustomerDto foundCustomer = (CustomerDto) findCustomerResponse.getEntity();
        assertEquals("test_first", foundCustomer.getFirstName());
        
        //create a product
        ProductDto createProduct = new ProductDto.Builder().name("Red Widget").build();
        Response createProductResponse = productResource.createProduct(createProduct);
        assertNotNull(createProductResponse.getEntity());
        
        //get product
        Response findProductResponse = productResource.getProduct((Long) createProductResponse.getEntity());
        assertNotNull(findProductResponse.getEntity());
        ProductDto foundProduct = (ProductDto) findProductResponse.getEntity();
        
        //create an order line
        OrderLineDto orderLine = new OrderLineDto.Builder().quantity(2).product(foundProduct).build();
        
        
        
    }
}
