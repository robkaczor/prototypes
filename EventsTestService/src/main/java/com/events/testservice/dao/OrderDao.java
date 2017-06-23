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
	
	@Autowired
	private CustomerDao customerDao;
	
    /**
     * Finds an order record by id.
     * @param id
     * @return OrderEntity
     */
    @Transactional(readOnly = true)
    public OrderEntity findOrderById(Long id) {
        return orderRepository.findOne(id);
    }

    /**
     * Creates an order record.
     * @param entity
     * @return OrderEntity
     */
    @Transactional
    public OrderEntity saveOrder(OrderEntity entity) {
    	//persist if new customer - this allows new customers at order placement
    	if (entity.getCustomer().getId() == null){
    		entity.setCustomer(customerDao.createCustomer(entity.getCustomer()));
    	}
        return orderRepository.save(entity);
    }
    
    /**
     * Deletes an order record and order lines.
     * @param id
     */
    @Transactional
	public void deleteOrder(Long id) {
    	orderRepository.delete(id);
	}
}
