package com.events.testservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The customer entity object.
 * @author Robert Kaczor
 *
 */
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
	private String firstName;
    
    @Column(name = "lastName")
	private String lastName;
    
    @Column(name = "email")
	private String email;

    @Column(name = "streetAddress")
	private String streetAddress;
    
    @Column(name = "city")
	private String city;
    
    @Column(name = "stateProvince")
	private String stateProvince;
    
    @Column(name = "postalCode")
	private String postalCode;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * 
	 * @author Robert Kaczor
	 *
	 */
	public static class Builder {
		
		private Long id;
		private String firstName;
		private String lastName;
		private String email;
		private String streetAddress;
		private String city;
		private String stateProvince;
		private String postalCode;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder streetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
			return this;
		}
		
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder stateProvince(String stateProvince) {
			this.stateProvince = stateProvince;
			return this;
		}
		
		public Builder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		public CustomerEntity build() {
			return new CustomerEntity(this);
		}
	}
    
    private CustomerEntity(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
		this.streetAddress = builder.streetAddress;
		this.city = builder.city;
		this.stateProvince = builder.stateProvince;
		this.postalCode = builder.postalCode;
    }
	
	public CustomerEntity() {}
	
}
