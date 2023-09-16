package cl.coe.ejercicio1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.coe.ejercicio1.filter.JwtAuthenticationFilter;
import cl.coe.ejercicio1.filter.JwtAuthorizationFilter;
import cl.coe.ejercicio1.impl.UserDetailsServiceImpl;
import cl.coe.ejercicio1.jwt.JwtUtils;
import cl.coe.ejercicio1.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConfig.
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	/** The jwt utils. */
	@Autowired
	JwtUtils jwtUtils;
	
	/** The user details service. */
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	/** The authorization filter. */
	@Autowired
	JwtAuthorizationFilter authorizationFilter;
	
	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Security filter chain.
	 *
	 * @param httpSecurity the http security
	 * @param authenticationManager the authentication manager
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
		
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils, userRepository);
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		
		return httpSecurity
				.csrf(config -> config.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/newUser").permitAll();
					auth.anyRequest().authenticated();
				})
				.sessionManagement( session ->{
					 session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				})
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Authentication manager.
	 *
	 * @param httpSecurity the http security
	 * @param passwordEncoder the password encoder
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}
}
