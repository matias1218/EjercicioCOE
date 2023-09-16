package cl.coe.ejercicio1.util;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public class Constants {
	
	/** The Constant SCOD_REGEX_PWD. */
	public static final String SCOD_REGEX_PWD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2}).*$";
	
	/** The Constant SCOD_REGEX_EMAIL. */
	public static final String SCOD_REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	/** The Constant SCOD_MAIL_EXIST_MSGE. */
	public static final String SCOD_MAIL_EXIST_MSGE = "El correo ya está registrado";
	
	/** The Constant SCOD_OTHER_ERROR_MSGE. */
	public static final String SCOD_OTHER_ERROR_MSGE = "Contraseña o password mal ingresados";
}
