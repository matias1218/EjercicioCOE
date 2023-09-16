package cl.coe.ejercicio1.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.coe.ejercicio1.dto.StatusUserDTO;
import cl.coe.ejercicio1.dto.UserDTO;
import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;
import cl.coe.ejercicio1.util.Constants;
import cl.coe.ejercicio1.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service
public class UserService {
	
	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
	
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the status user DTO
	 */
	public StatusUserDTO createUser(User user) {
		
		StatusUserDTO statusUserResponse = new StatusUserDTO();
		UserDTO userResponse = null;
		String password = "";
		
		
		if(Utils.valdiateRegex(user.getPassword(),Constants.SCOD_REGEX_PWD) && Utils.valdiateRegex(user.getEmail(),Constants.SCOD_REGEX_EMAIL)) {
			
			if(Objects.equals(userRepository.findByEmail(user.getEmail()), null)) {
				userResponse = new UserDTO();
				password = user.getPassword();
				
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
				
				userResponse.setId(user.getIdUser());
				userResponse.setCreated(user.getCreatedAt());
				userResponse.setModified(user.getCreatedAt());
				userResponse.setLastLogin(user.getLastLogin());  
				userResponse.setToken(user.getToken());			// DEBIDO A ESTO, NO DEVUELVE TOKEN EN LA RESPUESTA YA QUE NO SE AUTENTICA
				userResponse.setIsactive(user.isActive());
				
				
				statusUserResponse.setValidEmail(true);
				statusUserResponse.setUserDTO(userResponse);
			}
			else {
				statusUserResponse.setValidEmail(false);
				statusUserResponse.setValidPwd(true);
			}
		}
		else {
			statusUserResponse.setValidEmail(false);
			statusUserResponse.setValidPwd(false);
		}
		return statusUserResponse;
	}
}
