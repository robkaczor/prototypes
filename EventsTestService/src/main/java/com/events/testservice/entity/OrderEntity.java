package com.events.testservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @OneToOne
	private CustomerEntity customer;
	
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
	
	/**
	 * 
	 * @author Robert Kaczor
	 *
	 */
	public static class Builder {
		
		private Long id;
		private CustomerEntity customer;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder customer(CustomerEntity customer) {
			this.customer = customer;
			return this;
		}
		
		public OrderEntity build() {
			return new OrderEntity(this);
		}
	}
	
	public OrderEntity(Builder builder){
		 this.id = builder.id;
		 this.customer = builder.customer;
	}
	
	public OrderEntity() {}
}
