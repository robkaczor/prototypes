package com.events.testservice.rest.v1.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order line data transfer object.
 * 
 * @author Robert Kaczor
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderLineDto {
	
	private Long id;
	private Integer quantity;
	private ProductDto product;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public ProductDto getProduct() {
		return product;
	}
	
	public void setProduct(ProductDto product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderLineDto [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}
	
	public static class Builder {
		private Long id;
		private Integer quantity;
		private ProductDto product;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Builder product(ProductDto product) {
			this.product = product;
			return this;
		}

		public OrderLineDto build() {
			return new OrderLineDto(this);
		}
	}
	
	private OrderLineDto(Builder builder) {
		this.id = builder.id;
		this.quantity = builder.quantity;
		this.product = builder.product;
	}
	
	private OrderLineDto() {}
}
