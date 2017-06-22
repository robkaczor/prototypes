package com.events.testservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.events.testservice.entity.OrderEntity;
import com.events.testservice.entity.OrderLineEntity;
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
    	//persist if new customer - this allows new customers at order placement
    	if (entity.getCustomer().getId() == null){
    		entity.setCustomer(customerDao.createCustomer(entity.getCustomer()));
    	}
        return orderRepository.save(entity);
    }
    
    /**
     * Creates a order record and order lines.
     * @param entity
     * @return
     */
    @Transactional
	public OrderEntity createOrder(OrderEntity orderEntity, List<OrderLineEntity> orderLines) {
    	//persist if new customer - this allows new customers at order placement
    	if (orderEntity.getCustomer().getId() == null){
    		orderEntity.setCustomer(customerDao.createCustomer(orderEntity.getCustomer()));
    	}
    	orderEntity = orderRepository.save(orderEntity);
    	
    	
    	
        return orderEntity;
	}
}
