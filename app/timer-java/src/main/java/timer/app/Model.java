package timer.app;

import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Model {

    private boolean loggedIn = false;

    public Model() {

    }

    public TimerState state = TimerState.STOPPED;

    PropertyChangeListener listener = null;
    ScheduledExecutorService exec;
    public long starter, elapsedTimeSeconds, elapsedTimeMinutes;

    public void startTimer() {
        if(state == TimerState.RUNNING) return;
        exec = Executors.newSingleThreadScheduledExecutor();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                updateTime();
                if (listener != null) listener.onChange();
            }
        };

        exec.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        state = TimerState.RUNNING;
        elapsedTimeMinutes = 24;
        starter = 60;
        elapsedTimeSeconds = 60;
    }

    public void stopTimer(){
        if(state == TimerState.RUNNING) r.stop();
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