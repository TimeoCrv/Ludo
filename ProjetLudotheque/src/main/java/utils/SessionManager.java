package utils;

import java.util.Timer;
import java.util.TimerTask;

import model.User;


// Classe qui gère ce qui a trait à la création de session

public class SessionManager {
	
	private final static long DELAY = 10*60*1000;
	
	private static SessionManager instance;
	private static User currentUser;
	private static long startTime;
	private static Timer sessionTimer;
	
	// Constructeur privé pour ne pas pouvoir être instancié ailleurs
	private SessionManager() {
		
	}

	// Singleton
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}
	

	
	// Création de session en renseignant ses attributs
	public void createSession(User user) {
		currentUser = user;
		startTime = System.currentTimeMillis();
	}
	

	// Fermeture de session en retirant les valeurs des attributs
	public static void closeSession() {
		currentUser = null;
		instance = null;
		initTimer();
	}
	

	// Fonction équivalente à session_start() de php
	// Appelé à chaque changement de page (voir classe PageInit, parente de toutes les IHM)

	public static void startSessionTimer() {
		initTimer();
		sessionTimer = new Timer();
		sessionTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				
				if (instance != null) {

					checkUserActivity();
				}
				
			}
			
		}, DELAY);
	}
	
	// Fonction de vérification de session active
	// Mise à jour du temps de déaprt du timer
	public static void checkUserActivity() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - startTime > DELAY) {
			closeSession();
		} else {
			startTime = currentTime;
		}
	}


	// Récupération du user à des fins de vérifications des rôles
	public static User getCurrentUser() {
		return currentUser;
	}
	

	// Fonction de réinitialisation du timer
	// Pour pouvoir en recréer un dans startSessionTimer()
	private static void initTimer() {
		if (sessionTimer != null) {
			sessionTimer.cancel();
			sessionTimer=null;
		}}}
	
	

