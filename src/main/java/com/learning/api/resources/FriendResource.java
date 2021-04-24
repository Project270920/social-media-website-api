package com.learning.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.entities.Profile;
import com.learning.api.services.ProfileService;


@RestController
@RequestMapping("api/v1/profiles")
public class FriendResource
{
	@Autowired
	private ProfileService service;
	
	@GetMapping(value="friends")
	public ResponseEntity<?> ProfileFriendList(@RequestParam("id") int id)
	{
		List<Profile> list = service.getFriendListofProfile(id);
		if(list.isEmpty())
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Profile>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="blocklist")
	public ResponseEntity<?> ProfileBlockedFriendList(@RequestParam("id") int id)
	{
		List<Profile> list = service.getBlockedFriendListofProfile(id);
		if(list.isEmpty())
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Profile>>(list, HttpStatus.OK);
	}
/*
	@GetMapping(value="friends/add")
	public ResponseEntity<?> addFriendToProfile(@RequestParam("id1") int ProfileId,
													@RequestParam("id2") int friendId)
	{
		if(ProfileId == 0 || friendId == 0)
			return new ResponseEntity<String>("friend can't be added]", HttpStatus.NO_CONTENT);
		
		if(ProfileId == friendId)
			return new ResponseEntity<String>("Profile Can't add themself", HttpStatus.NOT_ACCEPTABLE);
		
		if(service.addNewFriendToProfile(ProfileId, friendId))
			return new ResponseEntity<String>("New Friend added to the Friend List", HttpStatus.OK);
		else
			return new ResponseEntity<String>("friend can't be added", HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping(value="blocklist/add")
	public ResponseEntity<?> ProfileBlockedFriendList(@RequestParam("id1") int ProfileId,
														  @RequestParam("id2") int friendId)
	{
		if(ProfileId == 0 || friendId == 0)
			return new ResponseEntity<String>("friend can't be added to Block List", HttpStatus.NO_CONTENT);

		if(ProfileId == friendId)
			return new ResponseEntity<String>("Profile Can't add themself to Block List", HttpStatus.NOT_ACCEPTABLE);
		
		if(service.addFriendToBlockList(ProfileId, friendId))
			return new ResponseEntity<String>("Friend added to the Block List", HttpStatus.OK);
		else
			return new ResponseEntity<String>("friend can't be added to Block List", HttpStatus.NO_CONTENT);
	}
*/
}
