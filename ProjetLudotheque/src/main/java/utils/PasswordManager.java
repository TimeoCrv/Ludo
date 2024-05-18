package utils;

import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.spec.InvalidKeySpecException;  
import java.util.Arrays;  
import java.util.Base64;  
import java.util.Random;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.PBEKeySpec;

import model.AdherentDAO;
import model.Connexion; 

// Classe qui gère ce qui a trait à la création et vérification de mot de passe
// Source : https://www.javatpoint.com/how-to-encrypt-password-in-java

public class PasswordManager {
	 
    private static final Random random = new SecureRandom();  
    private static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";  
    private static final int iterations = 10000;  
    private static final int keylength = 256;  
      
    // fonction de génération d'un grain de sel  
    public static String getSaltvalue(int length)   
    {  
        StringBuilder finalval = new StringBuilder(length);  
  
        for (int i = 0; i < length; i++)   
        {  
            finalval.append(characters.charAt(random.nextInt(characters.length())));  
        }  
  
        return new String(finalval);  
    }     
  
    // Fonction de hash d'une chaîne de caractères
    public static byte[] hash(char[] password, byte[] salt)   
    {  
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);  
        Arrays.fill(password, Character.MIN_VALUE);  
        try   
        {  
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
            return skf.generateSecret(spec).getEncoded();  
        }   
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)   
        {  
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);  
        }   
        finally   
        {  
            spec.clearPassword();  
        }  
    }  
  
    // Fonction de hashage du mot de passe
    public static String generateSecurePassword(String password, String salt)   
    {  
        String finalval = null;  
  
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());  
   
        finalval = Base64.getEncoder().encodeToString(securePassword);  
   
        return finalval;  
    }  
      
    // Fonction de vérification du mot de passe saisi lors de l'authentification 
    public static boolean verifyUserPassword(String providedPassword,  
            String securedPassword, String salt)  
    {  
        boolean finalval = false;  
          
        // On rehash le mot de passe fourni 
        String newSecurePassword = generateSecurePassword(providedPassword, salt);  
          
        // Pour le comparer à celui stocké en BDD
        finalval = newSecurePassword.equalsIgnoreCase(securedPassword);  
          
        return finalval;  
    } 
    
    
    // Fonction d'authentification
    // Peut-être plus de sens de placer ça dans ConnexionController
	public static boolean authenticate(String email, String password) {
		boolean connexionOk = false;


	        try {
	        	Connexion.getInstance();
	    		int idAdherent = AdherentDAO.getInstance().getIdByEmail(email);
	    		String storedPassword = AdherentDAO.getInstance().getPasswordById(idAdherent);
	    		String storedSalt = AdherentDAO.getInstance().getSaltById(idAdherent);

	            connexionOk = PasswordManager.verifyUserPassword(password, storedPassword, storedSalt);

			} catch (Exception e) {
				e.printStackTrace();
			}
	    
		return connexionOk;
	}

}
