package cl.coe.ejercicio1.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.coe.ejercicio1.jwt.JwtUtils;
import cl.coe.ejercicio1.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	
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
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		String token = jwtUtils.generateAccesToken(user.getUsername());
		
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
