package com.events.testservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.testservice.entity.OrderLineEntity;

/**
 * Spring data order line CRUD repository.
 * @author Robert Kaczor
 *
 */
@Repository
public interface OrderLineRepository extends CrudRepository<OrderLineEntity, Long> {

}
