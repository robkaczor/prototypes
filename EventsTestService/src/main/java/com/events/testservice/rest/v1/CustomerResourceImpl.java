package com.events.testservice.rest.v1;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.events.testservice.dao.CustomerDao;
import com.events.testservice.entity.CustomerEntity;
import com.events.testservice.rest.v1.dto.CustomerDto;

/**
 * Customer resource implementation.
 * @author Robert Kaczor
 *
 */
@Service
public class CustomerResourceImpl implements CustomerResource {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Response getCustomer(Long id) {
        if (id == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        CustomerEntity entity = customerDao.findCustomerById(id);
        if (entity == null) {
            return Response.status(HttpStatus.NO_CONTENT.value()).build();
        }
        CustomerDto dto = new CustomerDto.Builder()
            .id(entity.getId())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .streetAddress(entity.getStreetAddress())
            .city(entity.getCity())
            .stateProvince(entity.getStateProvince())
            .postalCode(entity.getPostalCode())
            .build();
        return Response.status(HttpStatus.OK.value()).entity(dto).build();
	}

	@Override
	public Response createCustomer(CustomerDto sampleDto) {
        if (sampleDto == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        CustomerEntity entity = new CustomerEntity.Builder()
            .id(sampleDto.getId())
            .firstName(sampleDto.getFirstName())
            .lastName(sampleDto.getLastName())
            .email(sampleDto.getEmail())
            .streetAddress(sampleDto.getStreetAddress())
            .city(sampleDto.getCity())
            .stateProvince(sampleDto.getStateProvince())
            .postalCode(sampleDto.getPostalCode())
            .build();
        entity = customerDao.createCustomer(entity);
        return Response.status(HttpStatus.OK.value()).entity(entity.getId()).build();
	}

}
