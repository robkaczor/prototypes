package com.events.testservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The customer entity line object.
 * @author Robert Kaczor
 *
 */
@Entity
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @OneToOne
    private ProductEntity product;

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
    
	public static class Builder {
		private Long id;
		private Integer quantity;
		private ProductEntity product;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public Builder product(ProductEntity product) {
			this.product = product;
			return this;
		}

		public OrderLineEntity build() {
			return new OrderLineEntity(this);
		}
	}
    
	private OrderLineEntity(Builder builder) {
		this.id = builder.id;
		this.quantity = builder.quantity;
		this.product = builder.product;
	}
	
	private OrderLineEntity() {}
}
