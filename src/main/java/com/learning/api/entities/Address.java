package com.learning.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="address_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address
{	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String number;
	private String locality;
	private String district;
	private String city;
	private String state;
	private int pincode;
	private String country;

	public Address setId(String id) 			{
		this.id = id;
		return this;
	}
	public Address setNumber(String number) 	{
		this.number = number;
		return this;
	}
	public Address setLocality(String locality) {
		this.locality = locality;
		return this;
	}
	public Address setDistrict(String district) {
		this.district = district;
		return this;
	}
	public Address setCity(String city) 		{
		this.city = city;
		return this;
	}
	public Address setState(String state) 		{
		this.state = state;
		return this;
	}
	public Address setPincode(int pincode) 		{
		this.pincode = pincode;
		return this;
	}
	public Address setCountry(String country) 	{
		this.country = country;
		return this;
	}

}
