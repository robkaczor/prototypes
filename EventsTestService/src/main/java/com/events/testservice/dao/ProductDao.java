/**
 * Copyright (c) 2014 Events.com.
 * All Rights Reserved.
 */
package com.events.testservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.entity.ProductEntity;
import com.events.testservice.repository.ProductRepository;

/**
 * Data access object for Product.
 * @author mkent
 *
 */
@Component
public class ProductDao {
    
    @Autowired
    private ProductRepository productRespository;

    /**
     * Finds a product record by id.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ProductEntity findProductById(Long id) {
        return productRespository.findOne(id);
    }

    /**
     * Creates a product record.
     * @param entity
     * @return
     */
    @Transactional
    public ProductEntity createProduct(ProductEntity entity) {
        return productRespository.save(entity);
    }
}
