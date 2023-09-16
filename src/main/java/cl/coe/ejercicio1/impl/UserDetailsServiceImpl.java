package cl.coe.ejercicio1.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDetailsServiceImpl.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));
		User userDetails = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
		return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),userDetails.getPassword(),true, true, true, true,roles);
	}

}
