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

    public long startTime = System.currentTimeMillis();
	long elapsedTime = System.currentTimeMillis() - startTime;

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}


	public void updateTime() {
		elapsedTime = System.currentTimeMillis() - startTime;
		elapsedTime /= 1000;
		System.out.println(elapsedTime);
    }

	public long getSeconds() {
		return (elapsedTime % 60);
	}

	public long getMinutes(){
		return (elapsedTime / 60);
	}

    public void setChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
    }
    
    public void authenticated(boolean auth) {
		this.loggedIn = auth;
		if (listener != null) listener.onChange();
    }
}