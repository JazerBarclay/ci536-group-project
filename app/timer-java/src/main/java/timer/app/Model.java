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
	    }
	}, 0, 1, TimeUnit.SECONDS);
    }

    PropertyChangeListener listener = null;

    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();
	long elapsedTime = System.currentTimeMillis() - startTime;
	long elapsedSeconds = elapsedTime / 1000;
	long secondsDisplay = elapsedSeconds % 60;

    public Long secondstime(){
		long elapsedMinutes = elapsedSeconds / 60;

		//put here code to format andddd display the values
	return elapsedSeconds;
    }

	public Long minutestime() {
		long elapsedMinutes = elapsedSeconds / 60;
		//put here code to format and display the values
		return elapsedMinutes;
	}

		public String updateTime() {
	this.endTime = System.currentTimeMillis();
	if (listener != null) listener.onChange();
	return endTime + "";
    }

    public void setChangeListener(PropertyChangeListener listener) {
	this.listener = listener;
    }
    
    public void authenticated(boolean auth) {
	this.loggedIn = auth;
	if (listener != null) listener.onChange();
    }



}