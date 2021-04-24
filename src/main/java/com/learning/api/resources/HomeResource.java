package com.learning.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.api.entities.User;
import com.learning.api.entities.UserLogin;
import com.learning.api.services.UserService;

@RestController
@RequestMapping("api/v1/user")
public class HomeResource {

	@Autowired
	private UserService userService;

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<?> loginUser(@RequestBody(required = true) UserLogin user)
	{
		System.out.print(user);
		User obj = userService.validateUser(user);
		
		if(obj == null)
			return new ResponseEntity<String>("Not Data Found...[FAILED]", HttpStatus.NOT_ACCEPTABLE);
		else
			return new ResponseEntity<User>(obj, HttpStatus.OK);
	}

/**************************************************************************************************/

	@GetMapping("all")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> list = userService.getUsers();
		
		if(list == null)
			return new ResponseEntity<String>("Not Data Found...[FAILED]", HttpStatus.NOT_ACCEPTABLE);
		else
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	
}
