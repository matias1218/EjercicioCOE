package cl.coe.ejercicio1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;

@SpringBootApplication
public class Ejercicio1Application {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);
	}
	
	@Bean
	CommandLineRunner init() {
		return args -> {
			User user = new User();
			user.setUsername("jose521");
			user.setEmail("juanitoperez@gmail.com");
			user.setPassword(passwordEncoder.encode("contrasena"));
			
			userRepository.save(user);
			
			User user2 = new User();
			user.setUsername("matias1234");
			user.setEmail("matias.rodriguez@gmail.com");
			user.setPassword(passwordEncoder.encode("cebra1212"));
			
			userRepository.save(user2);
			
			
			User user3 = new User();
			user.setUsername("pedritoXD");
			user.setEmail("xd.pedrito@gmail.com");
			user.setPassword(passwordEncoder.encode("root"));
			
			userRepository.save(user3);
		};
	}

}
