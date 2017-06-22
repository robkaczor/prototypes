package com.events.testservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.testservice.entity.CustomerEntity;

/**
 * Spring data customer CRUD repository.
 * @author Robert Kaczor
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

}
