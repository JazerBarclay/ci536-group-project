package timer.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Model {

    private boolean loggedIn = false;
    
    public Model() {
	ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	exec.scheduleAtFixedRate(new Runnable() {
	    @Override
	    public void run() {
			updateTime();
			if (listener != null) listener.onChange();
	    }
	}, 0, 1, TimeUnit.SECONDS);
    }

    PropertyChangeListener listener = null;

//    public long startTime;
//	public long reverseTimer;
//	long elapsedTime;
//
//	public void startTimer() {
//		startTime = System.currentTimeMillis();
//		reverseTimer = 1500;
//		elapsedTime = System.currentTimeMillis() - startTime;
//	}
//
//
//	public void updateTime() {
//		elapsedTime = System.currentTimeMillis() - startTime;
//		elapsedTime /= 1000;
//		reverseTimer -= elapsedTime;
//		System.out.println(elapsedTime);
//		System.out.println("New timer = " + reverseTimer);
//    }
//
//	public long getSeconds() {
//		return (reverseTimer % 60);
//	}
//
//	public long getMinutes(){
//		return (elapsedTime / 60);
//	}

	public long starter, elapsedTimeSeconds, elapsedTimeMinutes;
	public void startTimer() {
		elapsedTimeMinutes = 24;
		starter = 59;
		elapsedTimeSeconds = 59;
	}


	public void updateTime() {
		elapsedTimeSeconds--;
		if(elapsedTimeSeconds == 0){
			elapsedTimeSeconds = starter;
			elapsedTimeMinutes--;
		}
	}

	public long getSeconds() {
		return (elapsedTimeSeconds);
	}

	public long getMinutes(){
		return (elapsedTimeMinutes);
	}

//	---------------------------------------

    public void setChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
    }
    
    public void authenticated(boolean auth) {
		this.loggedIn = auth;
		if (listener != null) listener.onChange();
    }
}