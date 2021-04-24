package com.learning.api.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Profile
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Gender gender;
	
	@ManyToMany
	@JsonIgnore
	private List<Profile> friendlist = new ArrayList<>();

	@ManyToMany
	@JsonIgnore
	private List<Profile> blocklist  = new ArrayList<>();
	
	@ManyToMany
	@JsonIgnore
	private List<Profile> waitlist  = new ArrayList<>();
	
	public Profile(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public Profile setId(int id) 							{
		this.id = id;
		return this;
	}
	public Profile setName(String name) 					{
		this.name = name;
		return this;
	}
	public Profile setGender(Gender gender) 				{
		this.gender = gender;
		return this;
	}
	
	public Profile setFriendlist(List<Profile> friendlist)	{
		this.friendlist = friendlist;
		return this;
	}
	public Profile setBlocklist(List<Profile> blocklist)	{
		this.blocklist = blocklist;
		return this;
	}
	public Profile setWaitlist(List<Profile> waitlist)		{
		this.waitlist = waitlist;
		return this;
	}

	public Profile addFriendsToFriendList(Profile obj)		{
		friendlist.add(obj);
			return this;
	}
	public Profile addFriendsToBlockList(Profile obj)		{
		friendlist.add(obj);
		return this;
	}
	public Profile addFriendsToWaitlist(Profile obj)		{
		this.waitlist.add(obj);
		return this;
	}

}
