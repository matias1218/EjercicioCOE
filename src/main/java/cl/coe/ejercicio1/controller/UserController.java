package cl.coe.ejercicio1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path = "/getUsers")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@PostMapping(path = "/newUser")
	public ResponseEntity<?> createUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
	}

}