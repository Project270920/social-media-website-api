package com.learning.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.learning.api.async.RabbitMQConfiguration;
import com.learning.api.entities.Profile;
import com.learning.api.repositories.ProfileRepository;

public class AddFriendService {

	@Autowired
	private ProfileRepository repo;
	
	@Autowired
	private RabbitMQConfiguration amqpConfig;
	
	public void addFriendsToFriendList(Profile ProfileId, Profile friendId)
	{
	}
	
	public void addFriendsToFriendList(int ProfileId, int friendId)
	{
		Profile Profile = repo.getOne(ProfileId);
		Profile friend 		= repo.getOne(friendId);
	}

	public boolean addNewFriendToProfile(int ProfileId, int friendId)	{
		
		Profile Profile = repo.getOne(ProfileId);
		Profile friend = repo.getOne(friendId);
		
		for(Profile list:Profile.getFriendlist())
			if(list.getId() == friend.getId())
				return false;
				
		Profile.addFriendsToFriendList(friend);
		repo.save(Profile);
		return true;
	}
}
