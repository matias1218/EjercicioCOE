package cl.coe.ejercicio1.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.coe.ejercicio1.model.Phone;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret.key}")
	private String secretKey;
	
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	
	public Key getSignature() {
		byte[] chain = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(chain);
	}
	
	
	public String generateAccesToken(String username) {
		return Jwts.builder()
				   .setSubject(username)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				   .signWith(getSignature(),SignatureAlgorithm.HS256)
				   .compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignature()).build().parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignature()).build().parseClaimsJws(token).getBody();
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = getAllClaims(token);
		return claimsTFunction.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
}
