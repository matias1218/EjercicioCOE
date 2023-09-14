package cl.coe.ejercicio1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
	
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
