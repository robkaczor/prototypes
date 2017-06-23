/**
 * Copyright (c) 2015 Events.com.
 * All Rights Reserved.
 */
package com.events.testservice.rest.v1.injected;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.ApplicationConfig;
import com.events.testservice.rest.v1.ProductResource;
import com.events.testservice.rest.v1.dto.ProductDto;

/**
 * Integration test class for the product resource endpoints.
 * @author mkent
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class TestProductResource {

    @Autowired
    private ProductResource productResource;

    @Test
    @Transactional
    public void testCreateAndFindProduct() {
        ProductDto createProduct = new ProductDto.Builder()
        		.name("test_it")
        		.price(new BigDecimal(19.99))
        		.build();
        Response createResponse = productResource.createProduct(createProduct);
        assertNotNull(createResponse.getEntity());
        
        Response findResponse = productResource.getProduct((Long) createResponse.getEntity());
        assertNotNull(findResponse.getEntity());
        
        ProductDto foundProduct = (ProductDto) findResponse.getEntity();
        assertEquals("test_it", foundProduct.getName());
        assertEquals(new BigDecimal(19.99), foundProduct.getPrice());
    }
}
