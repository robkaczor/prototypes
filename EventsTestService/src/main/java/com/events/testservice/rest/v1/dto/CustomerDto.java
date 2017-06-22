package com.events.testservice.rest.v1.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Robert Kaczor
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDto implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String streetAddress;
	private String city;
	private String stateProvince;
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

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", stateProvince=" + stateProvince
				+ ", postalCode=" + postalCode + "]";
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
		
		public CustomerDto build() {
			return new CustomerDto(this);
		}
	}
	
	private CustomerDto(Builder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.streetAddress = builder.streetAddress;
		this.city = builder.city;
		this.stateProvince = builder.stateProvince;
		this.postalCode = builder.postalCode;
	}
	
	public CustomerDto() {}
}
