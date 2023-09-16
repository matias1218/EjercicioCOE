package cl.coe.ejercicio1.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class StatusUserDTO.
 */
public class StatusUserDTO {
	
	/** The user DTO. */
	private UserDTO userDTO;
	
	/** The is valid email. */
	private boolean isValidEmail;
	
	/** The is valid pwd. */
	private boolean isValidPwd;

	/**
	 * Gets the user DTO.
	 *
	 * @return the user DTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * Sets the user DTO.
	 *
	 * @param userDTO the new user DTO
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * Checks if is valid email.
	 *
	 * @return true, if is valid email
	 */
	public boolean isValidEmail() {
		return isValidEmail;
	}

	/**
	 * Sets the valid email.
	 *
	 * @param isValidEmail the new valid email
	 */
	public void setValidEmail(boolean isValidEmail) {
		this.isValidEmail = isValidEmail;
	}

	/**
	 * Checks if is valid pwd.
	 *
	 * @return true, if is valid pwd
	 */
	public boolean isValidPwd() {
		return isValidPwd;
	}

	/**
	 * Sets the valid pwd.
	 *
	 * @param isValidPwd the new valid pwd
	 */
	public void setValidPwd(boolean isValidPwd) {
		this.isValidPwd = isValidPwd;
	}
	
	

}
