package com.learning.api.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.entities.User;
import com.learning.api.services.UserService;

@RestController
@RequestMapping("api/v1/user")
public class RegistrationResource
{
	@Autowired
	private UserService userService;

	@PostMapping("register")
	@ResponseBody
	public ResponseEntity<?> registerUser(@RequestBody(required = true) User user)
	{
		if(user == null)
			return new ResponseEntity<String>("Not Data Recived...[FAILED]", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(userService.registerUser(user))
				return new ResponseEntity<String>("New User Created...[OK]", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Username/Email/Phone already registered...[FAILED]", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("forget")
	@ResponseBody
	public ResponseEntity<?> forgetPassword(@RequestParam(required = true, value="input") String user)
	{
		if(user == null)
			return new ResponseEntity<String>("Not Data Recived...[FAILED]", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			String obj = userService.generateResetCode(user);
			if(obj != null)
				return new ResponseEntity<String>(obj, HttpStatus.OK);
			else
				return new ResponseEntity<String>("Username/Email/Phone not correct...[FAILED]", HttpStatus.EXPECTATION_FAILED);
		}
	}
}
