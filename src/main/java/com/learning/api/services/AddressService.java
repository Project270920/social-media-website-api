package com.learning.api.services;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.entities.Address;
import com.learning.api.entities.Profile;
import com.learning.api.entities.User;
import com.learning.api.repositories.AddressRepository;
import com.learning.api.repositories.UserRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Address updateNumberInUserAddress(String obj, String data)
	{
		User user = userRepo.findUserByUsernameOrEmailOrPhone(obj);

		if(user == null)
			return null;
		else
		{
			Address address = user.getAddress().setNumber(data);
			repo.save(address);
			user.setAddress(address);
			userRepo.save(user);
			return userRepo.findByUsername(obj).get().getAddress();
		}
	}

}

enum AddressEntites
{
	NUMBER("number"),
	LOCALITY("locality"),
	DISTRICT("district"),
	CITY("city"),
	STATE("state"),
	PINCODE("pincode"),
	COUNTRY("country");

	private String value;

	private AddressEntites(String value) 					{
		this.value = value;
	}

	public static AddressEntites fromValue(String value)	{
		for (AddressEntites category : values())
			if (category.value.equalsIgnoreCase(value))
				return category;

		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	public static AddressEntites getRandomTypes()			{
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
