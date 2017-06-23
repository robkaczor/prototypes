package com.events.testservice.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.events.testservice.dao.OrderDao;
import com.events.testservice.entity.CustomerEntity;
import com.events.testservice.entity.OrderEntity;
import com.events.testservice.entity.OrderLineEntity;
import com.events.testservice.entity.ProductEntity;
import com.events.testservice.rest.v1.dto.CustomerDto;
import com.events.testservice.rest.v1.dto.OrderDto;
import com.events.testservice.rest.v1.dto.OrderLineDto;
import com.events.testservice.rest.v1.dto.ProductDto;

/**
 * Order resource implementation.
 * @author Robert Kaczor
 *
 */
@Service
public class OrderResourceImpl implements OrderResource {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Response getOrder(Long id) {
        if (id == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        OrderEntity orderEntity = orderDao.findOrderById(id);
        if (orderEntity == null) {
            return Response.status(HttpStatus.NO_CONTENT.value()).build();
        }
        
        //order lines
        List<OrderLineDto> orderLineList = new ArrayList<OrderLineDto>();
        for(OrderLineEntity orderLineEntity : orderEntity.getOrderLineList()){
        	//map the entity lines to the dto lines
        	OrderLineDto orderLineDto = new OrderLineDto.Builder()
        			.id(orderLineEntity.getId())
        			.product(new ProductDto.Builder()
        					.id(orderLineEntity.getProduct().getId())
    			            .name(orderLineEntity.getProduct().getName())
    			            .price(orderLineEntity.getProduct().getPrice())
        					.build())
        			.quantity(orderLineEntity.getQuantity())
        			.build();
        	orderLineList.add(orderLineDto);
        }
        
        //order header
        OrderDto orderDto = new OrderDto.Builder()
                .id(orderEntity.getId())
                .customer(
                		new CustomerDto.Builder()
                		.id(orderEntity.getCustomer().getId())
                        .firstName(orderEntity.getCustomer().getFirstName())
                        .lastName(orderEntity.getCustomer().getLastName())
                        .email(orderEntity.getCustomer().getEmail())
                        .streetAddress(orderEntity.getCustomer().getStreetAddress())
                        .city(orderEntity.getCustomer().getCity())
                        .stateProvince(orderEntity.getCustomer().getStateProvince())
                        .postalCode(orderEntity.getCustomer().getPostalCode())
                		.build()
                		)
                .orderLines(orderLineList)
                .build();
            return Response.status(HttpStatus.OK.value()).entity(orderDto).build();
	}

	@Override
	public Response createOrder(OrderDto orderDto) {
		//using POST to update an order results in a conflict, should use PUT
		if (orderDto.getId() != null){
			Response.status(HttpStatus.CONFLICT.value()).build();
		}
		
        //map and validate order
        OrderEntity orderEntity = MapOrderDtoToOrderEntity(orderDto);
        if (orderEntity == null){
        	Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        
        //persist order
        orderEntity = orderDao.saveOrder(orderEntity);
        
        return Response.status(HttpStatus.OK.value()).entity(orderEntity.getId()).build();
	}

	@Override
	public Response updateOrder(Long id, OrderDto orderDto) {
		//map and validate order
        OrderEntity orderEntity = MapOrderDtoToOrderEntity(orderDto);
        if (orderEntity == null){
        	Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
		
        //get original order
        OrderEntity originalOrderEntity = orderDao.findOrderById(id);
        
        //if the is not valid, return 404 not found
        if (originalOrderEntity == null){
        	Response.status(HttpStatus.NOT_FOUND.value()).build();
        }
        
        //make sure the order update does not change the customer
        if (originalOrderEntity.getCustomer().getId() != orderEntity.getCustomer().getId()){
        	Response.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).build();
        }
        
        //update order
        orderEntity.setId(id);
        orderEntity = orderDao.saveOrder(orderEntity);
        
        return Response.status(HttpStatus.OK.value()).entity(orderEntity.getId()).build();
	}

	@Override
	public Response deleteOrder(Long id) {
        if (id == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        OrderEntity orderEntity = orderDao.findOrderById(id);
        if (orderEntity == null) {
            return Response.status(HttpStatus.NO_CONTENT.value()).build();
        }
        orderDao.deleteOrder(id);
        return Response.status(HttpStatus.OK.value()).entity(id).build();
	}

	private OrderEntity MapOrderDtoToOrderEntity(OrderDto orderDto) {
		
		//an order must be complete before processing
        if (orderDto == null || orderDto.getCustomer() == null || 
        		orderDto.getOrderLines() == null ||
        		orderDto.getOrderLines().size() == 0) {
            return null;
        }
		
		//order lines
        List<OrderLineEntity> orderLineList = new ArrayList<OrderLineEntity>();
        for(OrderLineDto orderLineDto : orderDto.getOrderLines()){
        	
        	//check for a valid product
        	if (orderLineDto.getProduct() == null){
        		return null;
        	}
        	
        	//map the dto lines to the entity lines
        	OrderLineEntity orderLineEntity = new OrderLineEntity.Builder()
        			.id(orderLineDto.getId())
        			.product(new ProductEntity.Builder()
			            .id(orderLineDto.getProduct().getId())
			            .name(orderLineDto.getProduct().getName())
			            .price(orderLineDto.getProduct().getPrice())
			            .build())
        			.quantity(orderLineDto.getQuantity())
        			.build();
        	orderLineList.add(orderLineEntity);
        }

        //order header
        OrderEntity orderEntity = new OrderEntity.Builder()
            .id(orderDto.getId())
            .customer(new CustomerEntity.Builder()
            		.id(orderDto.getCustomer().getId())
                    .firstName(orderDto.getCustomer().getFirstName())
                    .lastName(orderDto.getCustomer().getLastName())
                    .email(orderDto.getCustomer().getEmail())
                    .streetAddress(orderDto.getCustomer().getStreetAddress())
                    .city(orderDto.getCustomer().getCity())
                    .stateProvince(orderDto.getCustomer().getStateProvince())
                    .postalCode(orderDto.getCustomer().getPostalCode())
            		.build())
            .orderLineList(orderLineList)
            .build();
		return orderEntity;
	}
	
}
