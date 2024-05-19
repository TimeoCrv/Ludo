package utils;

import java.util.regex.Pattern;


import model.AdherentDAO;

// Une classe qui permet de rassembler toutes les fonctions de validation de champs de saisie

import model.PersonnelDAO;


public class ValidatorManager {
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
	
	// Vérification de la conformité d'un Email saisi

	public static boolean isValidEmail(String email) {
		return PATTERN.matcher(email).matches(); 
	}
	// Vérification de la présence d'un email d'adhérent dans la BDD
	// Probablement à changer en isEmailInBD et faire une DAO sur la table utilisateur seulement
	public static boolean isAdherentEmailInBD(String email) {
		return AdherentDAO.getInstance().getIdByEmail(email)!=0;
	}



	// Probablement à changer en isEmailInBD et faire une DAO sur la table utilisateur seulement
	public static boolean isPersonnelEmailInBD(String email) {
		return PersonnelDAO.getInstance().getIdByEmail(email)!=0;
	}}


