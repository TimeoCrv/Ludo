package utils;

import java.util.regex.Pattern;

import model.AdherentDAO;

public class ValidatorManager {
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	
	public static boolean isValidEmail(String email) {
		return PATTERN.matcher(email).matches(); 
	}
	
	// Probablement à changer en isEmailInBD et faire une DAO sur la table utilisateur seulement
	public static boolean isAdherentEmailInBD(String email) {
		return AdherentDAO.getInstance().getIdByEmail(email)!=0;
	}

}
