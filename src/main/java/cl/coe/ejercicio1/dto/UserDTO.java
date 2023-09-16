package cl.coe.ejercicio1.dto;

import java.sql.Timestamp;
import java.util.Set;

import cl.coe.ejercicio1.model.Phone;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDTO.
 */
public class UserDTO {
	
	/** The id. */
	private Integer id;
	
	/** The created. */
	private Timestamp created;
	
	/** The email. */
	private Timestamp Modified;
	
	/** The last login. */
	private Timestamp lastLogin;
	
	/** The token. */
	private String token;
	
	/** The isactive. */
	private boolean isactive;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Timestamp getModified() {
		return Modified;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Timestamp modified) {
		Modified = modified;
	}

	/**
	 * Gets the last login.
	 *
	 * @return the last login
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the last login.
	 *
	 * @param lastLogin the new last login
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Checks if is isactive.
	 *
	 * @return true, if is isactive
	 */
	public boolean isIsactive() {
		return isactive;
	}

	/**
	 * Sets the isactive.
	 *
	 * @param isactive the new isactive
	 */
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
	
}
