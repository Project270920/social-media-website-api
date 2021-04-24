package com.learning.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.entities.Gender;
import com.learning.api.entities.Profile;
import com.learning.api.entities.User;
import com.learning.api.repositories.ProfileRepository;
import com.learning.api.repositories.UserRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<Profile> getAllProfile()		{
		return repo.findAll();
	}
	
	public boolean addNewProfile(Profile obj)	{
		
		if(repo.existsById(obj.getId()))
			return false;
		else
			if(repo.save(obj) == null)
				return false;
			else
				return true;
	}
	
	public Profile addNewProfileToUser(String user, Profile profile)	{
		
		User obj = userRepo.findUserByUsernameOrEmailOrPhone(user);

		if(obj == null)
			return null;
		else
		{
			repo.save(profile);
			return userRepo.save(obj.setProfile(profile)).getProfile();
		}
	}
	
	public Profile getProfileById(int id)		{
		return repo.getOne(id);
	}
	
	public Profile getProfileByUsernameOrEmailOrPhone(String user)	{
		return userRepo.findUserByUsernameOrEmailOrPhone(user).getProfile();
	}
	
	public Profile updateUserProfile(String obj, Profile profile)	{

		User user = userRepo.findUserByUsernameOrEmailOrPhone(obj);
		if(user == null)
			return null;
		else
		{
			repo.save(profile);

			user.setProfile(profile);
			userRepo.save(user);

			return userRepo.findByUsername(obj).get().getProfile();
		}
	}
	
	public String deleteUserProfile(String obj)						{
		
		User user = userRepo.findUserByUsernameOrEmailOrPhone(obj);

		if(user == null)
			return null;
		else
			repo.deleteById(user.getProfile().getId());
			return "User's Profile Deleted Successfully";
	}
	
	public List<Profile> getFriendListofProfile(int id)				{

		Profile obj = repo.getOne(id);
		
		if(obj == null)
			return null;
		else
			return obj.getFriendlist();
	}
	
	public List<Profile> getBlockedFriendListofProfile(int id)		{
		Profile obj = repo.getOne(id);
		
		if(obj == null)
			return null;
		else
			return obj.getBlocklist();
	}
	
	public Profile updateNameInProfile(String obj, String name)		{
		
		User user = userRepo.findUserByUsernameOrEmailOrPhone(obj);

		if(user == null)
			return null;
		else
		{
			Profile profile = user.getProfile().setName(name);
			repo.save(profile);
			user.setProfile(profile);
			userRepo.save(user);
			return userRepo.findByUsername(obj).get().getProfile();
		}
	}
	
	public Profile updateGenderInProfile(String obj, Gender gender)	{
		
		User user = userRepo.findUserByUsernameOrEmailOrPhone(obj);

		if(user == null)
			return null;
		else
		{
			Profile profile = user.getProfile().setGender(gender);
			repo.save(profile);
			user.setProfile(profile);
			userRepo.save(user);
			return userRepo.findByUsername(obj).get().getProfile();
		}
	}


/**************************************************************************************************/

	
	public Profile addFriendToBlockList(String user, Profile friend){
		
		Profile profile = userRepo.findUserByUsernameOrEmailOrPhone(user).getProfile();
		List<Profile> friendList = profile.getFriendlist();
		List<Profile> blockList = profile.getBlocklist();
		
		if(friendList.remove(friend))
			if(blockList.add(profile))
				return repo.save(profile.setFriendlist(friendList).setBlocklist(blockList));

		return null;	
	}

}
