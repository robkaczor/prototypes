package com.events.testservice.rest.v1.injected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.BeforeClass;
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
import com.events.testservice.rest.v1.dto.OrderDto;
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
    private OrderResource orderResource;
    
    @Autowired
    private CustomerResource customerResource;
    
    @Autowired
    private ProductResource productResource;
	
    private CustomerDto customerDto1;
    private CustomerDto customerDto2;
    private ProductDto productDto1;
    private ProductDto productDto2;
    
    @BeforeClass
    public static void prepareDtoForAllTests(){
    	
    }
    
    @Before
    public void resetDtoForEachTest(){
    	customerDto1 = new CustomerDto.Builder()
    			.id(1L)
        		.firstName("test_first_1")
        		.lastName("test_last_1")
        		.email("test1@test.com")
        		.streetAddress("123 Main St.")
        		.city("San Diego")
        		.stateProvince("CA")
        		.postalCode("90210")
    			.build();
    	Response respose = customerResource.createCustomer(customerDto1);
    	Long id  = (Long) respose.getEntity();
    	if (id != 1L) fail();
    	
    	customerDto2 = new CustomerDto.Builder()
    			.id(2L)
        		.firstName("test_first_2")
        		.lastName("test_last_2")
        		.email("test2@test.com")
        		.streetAddress("456 Main St.")
        		.city("Chicago")
        		.stateProvince("IL")
        		.postalCode("60618")
    			.build();
    	respose = customerResource.createCustomer(customerDto2);
    	id = (Long) respose.getEntity();
    	if (id != 2L) fail();
    	
    	productDto1 = new ProductDto.Builder()
    			.id(1L)
        		.name("Blue Widget")
        		.price(new BigDecimal(19.99))
    			.build();
    	respose = productResource.createProduct(productDto1);
    	id = (Long) respose.getEntity();
    	if (id != 1L) fail();
    	
    	productDto2 = new ProductDto.Builder()
    			.id(2L)
        		.name("Red Widget")
        		.price(new BigDecimal(14.95))
    			.build();
    	respose = productResource.createProduct(productDto2);
    	id = (Long) respose.getEntity();
    	if (id != 2L) fail();
    }
    
    private void verifyCustomerDto(CustomerDto customer1, CustomerDto customer2){
        assertEquals(customer1.getFirstName(), customer2.getFirstName());
        assertEquals(customer1.getLastName(), customer2.getLastName());
        assertEquals(customer1.getEmail(), customer2.getEmail());
        assertEquals(customer1.getStreetAddress(), customer2.getStreetAddress());
        assertEquals(customer1.getCity(), customer2.getCity());
        assertEquals(customer1.getStateProvince(), customer2.getStateProvince());
        assertEquals(customer1.getPostalCode(), customer2.getPostalCode());
    }
    
    @Test
    @Transactional
    public void testCreateAndFindOrder() {

        //create an order line and list
        OrderLineDto orderLine1 = new OrderLineDto.Builder()
        		.quantity(1)
        		.product(productDto1)
        		.build();
        List<OrderLineDto> orderLineList = new ArrayList<OrderLineDto>();
        orderLineList.add(orderLine1);

        //build the order
        OrderDto orderDto = new OrderDto.Builder()
        		.customer(customerDto1)
        		.orderLines(orderLineList)
        		.build();
        
        Response createResponse = orderResource.createOrder(orderDto);
        assertNotNull(createResponse.getEntity());
        
        Response findResponse = orderResource.getOrder((Long) createResponse.getEntity());
        assertNotNull(findResponse.getEntity());
        
        OrderDto foundOrder = (OrderDto) findResponse.getEntity();
        
        //verify the child entity
        assertNotNull(foundOrder.getCustomer());
        assertNotNull(foundOrder.getOrderLines());
        assertNotNull(foundOrder.getOrderLines().get(0).getProduct());
        
        //verify customer
        verifyCustomerDto(customerDto1, foundOrder.getCustomer());

        //verify order lines
        assertEquals(foundOrder.getOrderLines().size(), orderLineList.size());
        
    }
    
    
}
