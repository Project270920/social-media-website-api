package com.learning.api.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.entities.Address;
import com.learning.api.entities.Profile;
import com.learning.api.entities.User;
import com.learning.api.entities.UserLogin;
import com.learning.api.repositories.AddressRepository;
import com.learning.api.repositories.ProfileRepository;
import com.learning.api.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	public boolean verifyUser(User user)		{
		
		if(repo.findByUsernameAndEmailAndPhone(user.getUsername(), user.getEmail(), user.getPhone()) == null)
			return true;
		else
			return false;
	}
	public boolean registerUser(User user)		{

		if(verifyUser(user))
		{
			if(!user.isActive())
				user.setActive(true);
			
			if(user.getRoles() == null)
				user.setRoles("USER");

			Profile profile = new Profile();
			profileRepo.save(profile);
			
			Address address = new Address();
			addressRepo.save(address);
			
			repo.save(user.setProfile(profile).setAddress(address));
			return true;
		}
		else
			return false;
	}
	public List<User> getUsers()				{

		List<User> list = repo.findAll();
		
		if(list == null)
			return null;
		else
			return list;
	}

	public User validateUser(UserLogin login)		{
		return repo.findByUsernameAndPassword(login.getObject(), login.getPassword());
	}
	public String generateResetCode(String user)	{
		User obj = repo.findUserByUsernameOrEmailOrPhone(user);
		if(obj != null)
			return sendResetCode(obj);
		else
			return null;
	}
	public String sendResetCode(User user)			{
		return "CODE: "+ String.valueOf(000000 + new Random().nextInt(999999)) + ""+ user.getEmail() + user.getPhone();
	}
	public boolean verifyOTP(String obj, int otp)	{
		repo.findByUsernameOrEmailOrPhoneAndOtp(obj, otp);
		return false;
	}
}
