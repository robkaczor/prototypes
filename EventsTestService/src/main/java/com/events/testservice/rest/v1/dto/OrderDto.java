package com.events.testservice.rest.v1.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order data transfer object.
 * 
 * @author Robert Kaczor
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto implements Serializable {
	
	private Long id;
	private CustomerDto customer;
	private List<OrderLineDto> orderLineList;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public CustomerDto getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	
	public List<OrderLineDto> getOrderLines() {
		return orderLineList;
	}

	public void setOrderLines(List<OrderLineDto> orderLines) {
		this.orderLineList = orderLines;
	}
	
	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", customer=" + customer + ", orderLines=" + orderLineList + "]";
	}

	public static class Builder {
		private Long id;
		private CustomerDto customer;
		private List<OrderLineDto> orderLineList;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder customer(CustomerDto customer) {
			this.customer = customer;
			return this;
		}
		
		public Builder orderLines(List<OrderLineDto> orderLineList) {
			this.orderLineList = orderLineList;
			return this;
		}

		public OrderDto build() {
			return new OrderDto(this);
		}
	}
	
	private OrderDto(Builder builder) {
		this.id = builder.id;
		this.customer = builder.customer;
		this.orderLineList = builder.orderLineList;
	}
	
	private OrderDto() {}
}
