/**
 * Copyright (c) 2014 Events.com.
 * All Rights Reserved.
 */
package com.events.testservice.rest.v1.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Product data transfer object.
 * 
 * @author mkent
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto implements Serializable {

	private Long id;
	private String name;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	/**
	 * 
	 * @author mkent
	 *
	 */
	public static class Builder {
		private Long id;
		private String name;
		private BigDecimal price;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public ProductDto build() {
			return new ProductDto(this);
		}
	}

	private ProductDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.price = builder.price;
	}

	// Jackson needs a no-arg constructor
	public ProductDto() {
	}
}
