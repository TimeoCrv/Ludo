package utils;

import java.util.Timer;
import java.util.TimerTask;

import model.User;

public class SessionManager {
	
	private final static long DELAY = 10*60*1000;
	
	private static SessionManager instance;
	private static User currentUser;
	private static long startTime;
	private static Timer sessionTimer;
	
	private SessionManager() {
		
	}
	
	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}
	
	public void createSession(User user) {
		currentUser = user;
		startTime = System.currentTimeMillis();
	}
	
	public static void closeSession() {
		currentUser = null;
		instance = null;
		initTimer();
	}
	
	public static void startSessionTimer() {
		initTimer();
		sessionTimer = new Timer();
		sessionTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				
				if (instance != null) {
					checkSessionActivity();
				}
				
			}
			
		}, DELAY);
	}
	
	public static void checkSessionActivity() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - startTime > DELAY) {
			closeSession();
		} else {
			startTime = currentTime;
		}
	}

	public static User getCurrentUser() {
		return currentUser;
	}
	
	private static void initTimer() {
		if (sessionTimer != null) {
			sessionTimer.cancel();
			sessionTimer=null;
		}
	}
	
}
