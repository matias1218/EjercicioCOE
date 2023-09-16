package cl.coe.ejercicio1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class Ejercicio1Application.
 */
@SpringBootApplication
public class Ejercicio1Application {
	
	/** The password encoder. */
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);
	}
	
	/**
	 * Inits the.
	 *
	 * @return the command line runner
	 */
	@Bean
	CommandLineRunner init() {
		return args -> {
			User user = new User();
			user.setUsername("jose521");
			user.setEmail("juanitoperez@gmail.com");
			user.setPassword(passwordEncoder.encode("contrasena"));
			
			userRepository.save(user);
			
			
			User user2 = new User();
			user2.setUsername("pedritoXD");
			user2.setEmail("xd.pedrito@gmail.com");
			user2.setPassword(passwordEncoder.encode("root"));
			
			userRepository.save(user2);
		};
	}

}
