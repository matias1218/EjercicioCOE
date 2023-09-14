package cl.coe.ejercicio1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.coe.ejercicio1.dto.PhoneDto;
import cl.coe.ejercicio1.model.Phone;
import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.PhoneRepository;
import cl.coe.ejercicio1.repository.UserRepository;

@Service
public class PhoneService {
	
	 @Autowired
	 private PhoneRepository phoneRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 
	 public Iterable<Phone> findAll(){
		 return phoneRepository.findAll();
	 }
	 
	 public Phone createPhone(PhoneDto phoneDto) {
		 Phone phone = null;
		 Optional<User> user = userRepository.findById(phoneDto.getIdUser());
		 if(user.isPresent()) {
			 phone = new Phone();
			 phone.setCitycode(phoneDto.getCitycode());
			 phone.setContrycode(phoneDto.getContrycode());
			 phone.setNumber(phoneDto.getNumber());
			 phone.setUser(user.get());
			 phone =  phoneRepository.save(phone);
		 }
		 return phone;
		 
	 }

}
