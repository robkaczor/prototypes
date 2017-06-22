package com.events.testservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.entity.CustomerEntity;
import com.events.testservice.repository.CustomerRepository;

/**
 * Data access object for Customer.
 * @author Robert Kaczor
 *
 */
@Component
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;
	
    /**
     * Finds a customer record by id.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public CustomerEntity findCustomerById(Long id) {
        return customerRepository.findOne(id);
    }

    /**
     * Creates a customer record.
     * @param entity
     * @return
     */
    @Transactional
    public CustomerEntity createCustomer(CustomerEntity entity) {
        return customerRepository.save(entity);
    }
}
