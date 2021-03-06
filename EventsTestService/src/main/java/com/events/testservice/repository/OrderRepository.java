package com.events.testservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.testservice.entity.OrderEntity;

/**
 * Spring data order CRUD repository.
 * @author Robert Kaczor
 *
 */
@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
