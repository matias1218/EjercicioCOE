package cl.coe.ejercicio1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.coe.ejercicio1.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findByEmail(String email);

}
