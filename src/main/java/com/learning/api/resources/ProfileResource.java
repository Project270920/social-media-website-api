package com.learning.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.entities.Gender;
import com.learning.api.entities.Profile;
import com.learning.api.services.ProfileService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("api/v1/profile")
public class ProfileResource {
	
	@Autowired
	private ProfileService service;

	@PostMapping(value="/")
	@ResponseBody
	@ApiOperation(value="Create New Profile", notes="", response=Profile.class)
	public ResponseEntity<?> addNewUserProfile(@RequestParam(value="user", required = true) String user,
											   @RequestBody(required = true) Profile profile)
	{	
		if(profile == null)
			return new ResponseEntity<String>("Didn't received any Input", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			profile = service.addNewProfileToUser(user, profile);
			if(profile != null)
				return new ResponseEntity<Profile>(profile, HttpStatus.OK);
			else
				return new ResponseEntity<String>("Failed to Add Data", HttpStatus.NO_CONTENT);
		}
	}
		
	@GetMapping("/")
	@ResponseBody
	@ApiOperation(value="Retrive all the Profiles from User", notes="", response=Profile.class)
	public ResponseEntity<?> getUserProfile(@RequestParam("id") String user)
	{
		if(user == null)
			return new ResponseEntity<String>("Didn't received any Input", HttpStatus.NOT_ACCEPTABLE);
		
		Profile obj = service.getProfileByUsernameOrEmailOrPhone(user);
		if(obj == null)
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Profile>(obj, HttpStatus.OK);
	}
	
	@PatchMapping("update")
	@ResponseBody
	@ApiOperation(value="Update Profile Data using username/email/phone", notes="", response=Profile.class)
	public ResponseEntity<?> patchUserProfile(@RequestParam(value="username", required=true) String username,
											  @RequestBody(required=true) Profile profile)
	{
		if(username == null || profile == null)
			return new ResponseEntity<String>("Didn't received and Input", HttpStatus.NOT_ACCEPTABLE);
		
		Profile obj = service.updateUserProfile(username, profile);
		if(obj == null)
			return new ResponseEntity<String>("Data Not Updated....[FAILED]", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Profile>(obj, HttpStatus.OK);
	}

	@PutMapping(value="update/name")
	@ResponseBody
	@ApiOperation(value="update NAME in the User Profile", notes="", response=Profile.class)
	public ResponseEntity<?> updateProfilesName(@RequestParam(value="user", required=true) String user,
												@RequestParam(value="name", required=true) String name)
	{
		Profile profile = service.updateNameInProfile(user, name);
		if(profile == null)
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
	
	@PutMapping(value="update/gender")
	@ResponseBody
	@ApiOperation(value="update GENDER in the User Profile", notes="", response=Profile.class)
	public ResponseEntity<?> getUpdateProfilesGender(@RequestParam(value="user", required=true) String user,
													 @RequestParam(value="gender", required=true) String gender)
	{
		Profile profile = service.updateGenderInProfile(user, Gender.valueOf(gender));
		if(profile == null)
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
	}
	
	@DeleteMapping("delete")
	@ApiOperation(value="Delete Profile Data using username/email/phone", notes="", response=Profile.class)
	public ResponseEntity<?> deleteUserProfile(@RequestParam("id") String user)
	{
		if(user == null)
			return new ResponseEntity<String>("Didn't received and Input", HttpStatus.NOT_ACCEPTABLE);
		
		String obj = service.deleteUserProfile(user);
		if(obj == null)
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<String>(obj, HttpStatus.OK);
	}


/*********************************************************************************************************************/

	@GetMapping(value="all")
	@ApiOperation(value="Retrive all Profile Data from Database", notes="", response=Profile.class)
	public ResponseEntity<?> getAllProfiles()
	{
		List<Profile> list = service.getAllProfile();
		if(list.isEmpty())
			return new ResponseEntity<String>("Not Data Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Profile>>(list, HttpStatus.OK);
	}
}
