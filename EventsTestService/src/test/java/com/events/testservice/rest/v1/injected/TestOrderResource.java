package com.events.testservice.rest.v1.injected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
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
 * Test Create(Post), Find(Get), Update(Put), and Delete
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
	
    private ProductDto productDto1;
    private ProductDto productDto2;
    private CustomerDto customerDto1;
    private CustomerDto customerDto2;
    
    @Before
    public void resetDtoForEachTest(){
    	if (customerDto1 == null){
        	customerDto1 = new CustomerDto.Builder()
            		.firstName("test_first_1")
            		.lastName("test_last_1")
            		.email("test1@test.com")
            		.streetAddress("123 Main St.")
            		.city("San Diego")
            		.stateProvince("CA")
            		.postalCode("90210")
        			.build();
        	
    		Response respose = customerResource.createCustomer(customerDto1);
    		assertNotNull(respose);
    		Long id = (Long) respose.getEntity();
    		
    		respose = customerResource.getCustomer(id);
    		assertNotNull(respose);
    		customerDto1 = (CustomerDto) respose.getEntity();
    	}
    	
    	if (customerDto2 == null){
        	customerDto2 = new CustomerDto.Builder()
            		.firstName("test_first_2")
            		.lastName("test_last_2")
            		.email("test2@test.com")
            		.streetAddress("456 Main St.")
            		.city("Chicago")
            		.stateProvince("IL")
            		.postalCode("60618")
        			.build();
        	
    		Response respose = customerResource.createCustomer(customerDto2);
    		assertNotNull(respose);
    		Long id = (Long) respose.getEntity();
    		
    		respose = customerResource.getCustomer(id);
    		assertNotNull(respose);
    		customerDto2 = (CustomerDto) respose.getEntity();
    	}
    	
    	if (productDto1 == null){
        	productDto1 = new ProductDto.Builder()
            		.name("Blue Widget")
            		.price(new BigDecimal(19.99))
        			.build();
        	
    		Response respose = productResource.createProduct(productDto1);
    		assertNotNull(respose);
    		Long id = (Long) respose.getEntity();
    		
    		respose = productResource.getProduct(id);
    		assertNotNull(respose);
    		productDto1 = (ProductDto) respose.getEntity();
    	}
    	
    	if (productDto2 == null){
    		productDto2 = new ProductDto.Builder()
            		.name("Red Widget")
            		.price(new BigDecimal(14.95))
        			.build();
        	
    		Response respose = productResource.createProduct(productDto2);
    		assertNotNull(respose);
    		Long id = (Long) respose.getEntity();
    		
    		respose = productResource.getProduct(id);
    		assertNotNull(respose);
    		productDto2 = (ProductDto) respose.getEntity();
    	}
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
    
    private void verifyProductDto(ProductDto product1, ProductDto product2){
    	assertEquals(product1.getName(), product2.getName());
    	assertEquals(product1.getPrice(), product2.getPrice());
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
        assertEquals(foundOrder.getOrderLines().size(), 1);
        
        //verify product
        verifyProductDto(productDto1, foundOrder.getOrderLines().get(0).getProduct());
    }
    
    @Test
    @Transactional
    public void testCreateDeleteOrder() {
    	
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
        
        Long orderId = (Long) createResponse.getEntity();
        
        Response findResponse = orderResource.getOrder(orderId);
        assertNotNull(findResponse.getEntity());
        
        //delete the order
        Response deleteResponse = orderResource.deleteOrder(orderId);
        assertNotNull(deleteResponse.getEntity());
        
        //try to find order to ensure it's gone
        Response findResponseAfter = orderResource.getOrder(orderId);
        assertNull(findResponseAfter.getEntity());
    }
    
    @Test
    @Transactional
    public void testCreateAndUpdateOrder() {
    	//create an order line and list
        OrderLineDto orderLine1 = new OrderLineDto.Builder()
        		.quantity(1)
        		.product(productDto1)
        		.build();
        List<OrderLineDto> orderLineList = new ArrayList<OrderLineDto>();
        orderLineList.add(orderLine1);

        //build the order
        OrderDto orderDto = new OrderDto.Builder()
        		.customer(customerDto2)
        		.orderLines(orderLineList)
        		.build();
        
        //create the order
        Response createResponse = orderResource.createOrder(orderDto);
        assertNotNull(createResponse.getEntity());
        
        Long orderId = (Long) createResponse.getEntity();
        
        Response firstFindResponse = orderResource.getOrder(orderId);
        assertNotNull(firstFindResponse.getEntity());
        
        //first get
        OrderDto firstGet = (OrderDto) firstFindResponse.getEntity();
        
        //add a line to the order
        OrderLineDto orderLine2 = new OrderLineDto.Builder()
        		.quantity(2)
        		.product(productDto2)
        		.build();
        orderLineList.add(orderLine2);
        firstGet.getOrderLines().add(orderLine2);
        
        //update order
        Response updateResponse = orderResource.updateOrder(orderId, firstGet);
        assertNotNull(updateResponse.getEntity());
        
        Response secondFindResponse = orderResource.getOrder(orderId);
        assertNotNull(secondFindResponse.getEntity());
        
        //second get
        OrderDto secondGet = (OrderDto) secondFindResponse.getEntity();
        assertTrue(secondGet.getOrderLines().size() == 2);

    }
}
