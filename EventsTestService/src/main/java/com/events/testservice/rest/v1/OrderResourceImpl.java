package com.events.testservice.rest.v1;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.events.testservice.dao.OrderDao;
import com.events.testservice.entity.CustomerEntity;
import com.events.testservice.entity.OrderEntity;
import com.events.testservice.rest.v1.dto.CustomerDto;
import com.events.testservice.rest.v1.dto.OrderDto;

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
        OrderEntity entity = orderDao.findOrderById(id);
        if (entity == null) {
            return Response.status(HttpStatus.NO_CONTENT.value()).build();
        }
        OrderDto dto = new OrderDto.Builder()
                .id(entity.getId())
                .customer(
                		new CustomerDto.Builder()
                		.id(entity.getCustomer().getId())
                        .firstName(entity.getCustomer().getFirstName())
                        .lastName(entity.getCustomer().getLastName())
                        .email(entity.getCustomer().getEmail())
                		.build()
                		)
                .build();
            return Response.status(HttpStatus.OK.value()).entity(dto).build();
	}

	@Override
	public Response createOrder(OrderDto sampleDto) {
        if (sampleDto == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        OrderEntity entity = new OrderEntity.Builder()
            .id(sampleDto.getId())
            .customer(new CustomerEntity.Builder()
            		.id(sampleDto.getCustomer().getId())
                    .firstName(sampleDto.getCustomer().getFirstName())
                    .lastName(sampleDto.getCustomer().getLastName())
                    .email(sampleDto.getCustomer().getEmail())
            		.build()
            		)
            .build();
        entity = orderDao.createOrder(entity);
        return Response.status(HttpStatus.OK.value()).entity(entity.getId()).build();
	}

}
