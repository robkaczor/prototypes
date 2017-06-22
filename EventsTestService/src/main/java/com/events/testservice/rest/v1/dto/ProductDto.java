/**
 * Copyright (c) 2014 Events.com.
 * All Rights Reserved.
 */
package com.events.testservice.rest.v1.dto;

import java.io.Serializable;

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

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + "]";
	}

	/**
	 * 
	 * @author mkent
	 *
	 */
	public static class Builder {
		private Long id;
		private String name;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public ProductDto build() {
			return new ProductDto(this);
		}
	}

	private ProductDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	// Jackson needs a no-arg constructor
	public ProductDto() {
	}
}
