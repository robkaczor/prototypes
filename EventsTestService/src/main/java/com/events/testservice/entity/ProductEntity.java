/**
 * Copyright (c) 2014 Events.com.
 * All Rights Reserved.
 */
package com.events.testservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The product entity object.
 * @author mkent
 *
 */
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
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

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }

    private ProductEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
    }
    
	// Jackson needs a no-arg constructor
    public ProductEntity(){}
}
