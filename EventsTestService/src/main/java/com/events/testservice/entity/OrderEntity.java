package com.events.testservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The order entity object.
 * @author Robert Kaczor
 *
 */
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(fetch=FetchType.EAGER)
	private CustomerEntity customer;
    
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderLineEntity> orderLineList;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public CustomerEntity getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
	public List<OrderLineEntity> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLineEntity> orderLineList) {
		this.orderLineList = orderLineList;
	}

	/**
	 * 
	 * @author Robert Kaczor
	 *
	 */
	public static class Builder {
		
		private Long id;
		private CustomerEntity customer;
		private List<OrderLineEntity> orderLineList;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder customer(CustomerEntity customer) {
			this.customer = customer;
			return this;
		}
		
		public Builder orderLineList(List<OrderLineEntity> orderLineList) {
			this.orderLineList = orderLineList;
			return this;
		}	
		
		public OrderEntity build() {
			return new OrderEntity(this);
		}
	}
	
	public OrderEntity(Builder builder){
		 this.id = builder.id;
		 this.customer = builder.customer;
		 this.orderLineList = builder.orderLineList;
	}
	
	public OrderEntity() {}
}
