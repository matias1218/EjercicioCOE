package cl.coe.ejercicio1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.coe.ejercicio1.dto.PhoneDTO;
import cl.coe.ejercicio1.service.PhoneService;

// TODO: Auto-generated Javadoc
/**
 * The Class PhoneController.
 */
@RestController
public class PhoneController {
	
	/** The phone service. */
	@Autowired
	private PhoneService phoneService;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping(path = "/getPhones")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(phoneService.findAll());
	}
	
	/**
	 * Creates the phone.
	 *
	 * @param phoneDto the phone dto
	 * @return the response entity
	 */
	@PostMapping(path = "/newPhone")
	public ResponseEntity<?> createPhone(@RequestBody PhoneDTO phoneDto){
		return ResponseEntity.status(HttpStatus.OK).body(phoneService.createPhone(phoneDto));
	}
	
}
