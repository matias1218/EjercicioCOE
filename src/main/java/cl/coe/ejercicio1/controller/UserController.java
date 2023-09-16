package cl.coe.ejercicio1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.coe.ejercicio1.dto.StatusUserDTO;
import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.service.UserService;
import cl.coe.ejercicio1.util.Constants;
import cl.coe.ejercicio1.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
public class UserController {
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping(path = "/getUsers")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PostMapping(path = "/newUser")
	public ResponseEntity<?> createUser(@RequestBody User user){
		
		ResponseEntity<?> response = null;
		StatusUserDTO userDetail = userService.createUser(user);
		
		if(userDetail.isValidEmail()) {
			response = ResponseEntity.status(HttpStatus.OK).body(userDetail.getUserDTO());
		}
		else if(!userDetail.isValidPwd()) {
			response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Utils.setJsonErrorMessage(Constants.SCOD_OTHER_ERROR_MSGE).toString());
		}
		else {
			response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Utils.setJsonErrorMessage(Constants.SCOD_MAIL_EXIST_MSGE).toString());
		}
		return response;
	}

}