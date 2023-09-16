package cl.coe.ejercicio1.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.coe.ejercicio1.jwt.JwtUtils;
import cl.coe.ejercicio1.model.User;
import cl.coe.ejercicio1.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtAuthenticationFilter.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	
	/** The user repository. */
	private UserRepository userRepository;
	
	/** The jwt utils. */
	private JwtUtils jwtUtils;
	
	/**
	 * Instantiates a new jwt authentication filter.
	 *
	 * @param jwtUtils the jwt utils
	 * @param userRepository the user repository
	 */
	public JwtAuthenticationFilter(JwtUtils jwtUtils, UserRepository userRepository) {
		this.jwtUtils = jwtUtils;
		this.userRepository = userRepository;
	}
	
	/**
	 * Attempt authentication.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the authentication
	 * @throws AuthenticationException the authentication exception
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		User user = null;
		String username = "";
		String password = "";
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			username = user.getUsername();
			password = user.getPassword();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
		return getAuthenticationManager().authenticate(auth);
	}
	
	/**
	 * Successful authentication.
	 *
	 * @param request the request
	 * @param response the response
	 * @param chain the chain
	 * @param authResult the auth result
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		String token = jwtUtils.generateAccesToken(user.getUsername());
		
		User userToAuth = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
		userToAuth.setLastLogin(new Timestamp(System.currentTimeMillis()));
		userToAuth.setToken(token);
		userRepository.save(userToAuth);
		
		response.addHeader("Authorization", token);
		
		Map<String, Object> httpResponse = new HashMap<>();
		httpResponse.put("token",token);
		httpResponse.put("Message", "Usuario autenticado");
		httpResponse.put("Username", user.getUsername());
		
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
		response.setStatus(HttpStatus.OK.value());
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
