package cl.coe.ejercicio1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.coe.ejercicio1.dto.PhoneDTO;
import cl.coe.ejercicio1.model.Phone;
import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.PhoneRepository;
import cl.coe.ejercicio1.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PhoneService.
 */
@Service
public class PhoneService {
	
	 /** The phone repository. */
 	@Autowired
	 private PhoneRepository phoneRepository;
	 
	 /** The user repository. */
 	@Autowired
	 private UserRepository userRepository;
	 
	 
	 /**
 	 * Find all.
 	 *
 	 * @return the iterable
 	 */
 	public Iterable<Phone> findAll(){
		 return phoneRepository.findAll();
	 }
	 
	 /**
 	 * Creates the phone.
 	 *
 	 * @param phoneDto the phone dto
 	 * @return the phone
 	 */
 	public Phone createPhone(PhoneDTO phoneDto) {
		 Phone phone = null;
		 Optional<User> user = userRepository.findById(phoneDto.getIdUser());
		 if(user.isPresent()) {
			 phone = new Phone();
			 phone.setCitycode(phoneDto.getCitycode());
			 phone.setContrycode(phoneDto.getContrycode());
			 phone.setNumber(phoneDto.getNumber());
			 phone.setUser(user.get());
			 user.get().getPhones().add(phone);
			 userRepository.save(user.get());
			 phone = phoneRepository.save(phone);
		 }
		 return phone;
		 
	 }

}
