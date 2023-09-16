package cl.coe.ejercicio1.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtUtils.
 */
@Component
public class JwtUtils {

	/** The secret key. */
	@Value("${jwt.secret.key}")
	private String secretKey;
	
	/** The time expiration. */
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	
	/**
	 * Gets the signature.
	 *
	 * @return the signature
	 */
	public Key getSignature() {
		byte[] chain = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(chain);
	}
	
	
	/**
	 * Generate acces token.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String generateAccesToken(String username) {
		return Jwts.builder()
				   .setSubject(username)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				   .signWith(getSignature(),SignatureAlgorithm.HS256)
				   .compact();
	}
	
	/**
	 * Checks if is token valid.
	 *
	 * @param token the token
	 * @return true, if is token valid
	 */
	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignature()).build().parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Gets the all claims.
	 *
	 * @param token the token
	 * @return the all claims
	 */
	public Claims getAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignature()).build().parseClaimsJws(token).getBody();
	}
	
	/**
	 * Gets the claim.
	 *
	 * @param <T> the generic type
	 * @param token the token
	 * @param claimsTFunction the claims T function
	 * @return the claim
	 */
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = getAllClaims(token);
		return claimsTFunction.apply(claims);
	}

	/**
	 * Gets the username from token.
	 *
	 * @param token the token
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
}
