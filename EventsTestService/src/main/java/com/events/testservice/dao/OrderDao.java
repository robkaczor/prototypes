package com.events.testservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.entity.OrderEntity;
import com.events.testservice.repository.OrderRepository;

/**
 * Data access object for Order.
 * @author Robert Kaczor
 *
 */
@Component
public class OrderDao {

	@Autowired
	private OrderRepository orderRepository;
	
    /**
     * Finds a order record by id.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public OrderEntity findOrderById(Long id) {
        return orderRepository.findOne(id);
    }

    /**
     * Creates a order record.
     * @param entity
     * @return
     */
    @Transactional
    public OrderEntity createOrder(OrderEntity entity) {
        return orderRepository.save(entity);
    }
}
