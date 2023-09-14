package cl.coe.ejercicio1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.coe.ejercicio1.dto.PhoneDto;
import cl.coe.ejercicio1.service.PhoneService;

@RestController
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	@GetMapping(path = "/getPhones")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(phoneService.findAll());
	}
	
	@PostMapping(path = "/newPhone")
	public ResponseEntity<?> createPhone(@RequestBody PhoneDto phoneDto){
		return ResponseEntity.status(HttpStatus.OK).body(phoneService.createPhone(phoneDto));
	}
	
}
