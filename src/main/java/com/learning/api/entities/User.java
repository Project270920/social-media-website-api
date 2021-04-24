package com.learning.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name="user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private boolean active;
	private String roles;
	private int otp;

	@OneToOne
	private Profile profile;
	
	@OneToOne
	private Address address;

	public User setId(int id) 					{
		this.id = id;
		return this;
	}
	public User setUsername(String username) 	{
		this.username = username;
		return this;
	}
	public User setPassword(String password) 	{
		this.password = password;
		return this;
	}
	public User setEmail(String email) 			{
		this.email = email;
		return this;
	}
	public User setPhone(String phone) 			{
		this.phone = phone;
		return this;
	}
	public User setActive(boolean active) 		{
		this.active = active;
		return this;
	}
	public User setRoles(String roles) 			{
		this.roles = roles;
		return this;
	}
	
	public User setProfile(Profile profile) 	{
		this.profile = profile;
		return this;
	}
	public User setAddress(Address address)		{
		this.address = address;
		return this;
	}
	
}
