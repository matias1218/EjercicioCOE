package cl.coe.ejercicio1.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {
	
	
	/**
	 * Valdiate regex.
	 *
	 * @param text the text
	 * @param regex the regex
	 * @return true, if successful
	 */
	public static boolean valdiateRegex(String text, String regex) {
		
		boolean flagValidate = true;
		Pattern p = Pattern.compile(regex);
		if(text == null) {
			flagValidate = false;
		}
		else {
			Matcher m = p.matcher(text);
			flagValidate =  m.matches();
		}
		return flagValidate;
	}
	
	/**
	 * Sets the json error message.
	 *
	 * @param msge the msge
	 * @return the JSON object
	 */
	public static JSONObject setJsonErrorMessage(String msge) {
		JSONObject json = new JSONObject();
		json.put("mensaje", msge);
		return json;
	}
}
