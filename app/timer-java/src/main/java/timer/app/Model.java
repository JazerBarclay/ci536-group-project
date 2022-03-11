package timer.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Model {

    private boolean loggedIn = false;

    public Model() {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
			updateTime();
			if (listener != null) listener.onChange();
		}, 0, 1, TimeUnit.SECONDS);
    }


	PropertyChangeListener listener = null;

	public long starter, elapsedTimeSeconds, elapsedTimeMinutes;

    public void startTimer() {
        elapsedTimeMinutes = 24;
        starter = 60;
        elapsedTimeSeconds = 60;
    }


    public void updateTime() {
        elapsedTimeSeconds--;
        if (elapsedTimeSeconds == 0) {
            elapsedTimeSeconds = starter;
            elapsedTimeMinutes--;
        }
		System.out.println("This is Listener = " + listener);
    }

    public long getSeconds() {
        return (elapsedTimeSeconds);
    }

    public long getMinutes() {
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