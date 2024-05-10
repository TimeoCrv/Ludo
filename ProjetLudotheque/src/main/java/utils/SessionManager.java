package utils;

import java.util.Timer;
import java.util.TimerTask;

import model.User;

public class SessionManager {
	
	private final static long DELAY = 10*60*1000;
	
	private static SessionManager instance;
	private static User currentUser;
	private static long startTime;
	
	private SessionManager() {
		
	}
	
	public static synchronized SessionManager getInstance() {
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
	}
	
	public static void startSessionTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				
				if (instance != null) {
					checkSessionActivity();
				}
				
			}
			
		}, DELAY);
	}
	
	public synchronized static void checkSessionActivity() {
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
	
}
