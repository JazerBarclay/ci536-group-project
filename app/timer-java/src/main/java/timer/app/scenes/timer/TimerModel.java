package timer.app.scenes.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import timer.app.ClockState;
import timer.fx.mvc.ScreenModel;

/**
 * Model of timer screen
 * 
 * @author Jazer
 *
 */
public class TimerModel extends ScreenModel {

    // Default work and rest minutes and seconds
    private static int DEFAULT_WORK_MINUTES = 25;
    private static int DEFAULT_WORK_SECONDS = 0;
    private static int DEFAULT_REST_MINUTES = 5;
    private static int DEFAULT_REST_SECONDS = 0;

    // Security token used to login
    protected String jwt;

    // Timer State (Working, Resting, Stopped)
    protected ClockState state;

    // Current minutes and seconds
    protected int minutes;
    protected int seconds;

    protected ScheduledExecutorService timer;

    /**
     * Creates a new timer model with the login web token from login screen.
     * 
     * Initialises the clock to stopped
     * @param jwt
     */
    public TimerModel(String jwt) {
	this.jwt = jwt;
	setStopped();
    }

    public void startTimer() {
	setWorking();
	timer = Executors.newSingleThreadScheduledExecutor();
	timer.scheduleAtFixedRate(() -> {
	    updateTime();
	    updateView();
	}, 0, 1, TimeUnit.SECONDS);
	updateView();
    }


    private void updateTime() {

	// Reduce the seconds by 1
	seconds--;

	// Check if at end of timer countdown
	if (minutes == 0 && seconds < 0 ) {
	    if (state == ClockState.WORKING) setResting();
	    else stopTimer();
	    return;
	}

	// Update the minutes if 0 seconds reached
	if (seconds < 0) {
	    seconds = 59;
	    minutes--;
	}

    }

    /**
     * Stops the timer if clock state is not stopped
     */
    public void stopTimer() {
	if (state != ClockState.STOPPED) {
	    setStopped();
	    timer.shutdown();
	    updateView();
	}
    }

    /**
     * Sets the clock to working and reset time to working time
     */
    protected void setWorking() {
	state = ClockState.WORKING;
	minutes = DEFAULT_WORK_MINUTES;
	seconds = DEFAULT_WORK_SECONDS;
    }

    /**
     * Sets the clock to resting and reset time to resting time
     */
    protected void setResting() {
	state = ClockState.RESTING;
	minutes = DEFAULT_REST_MINUTES;
	seconds = DEFAULT_REST_SECONDS;
    }

    /**
     * Sets the clock to stopped and resets time to working time
     */
    protected void setStopped() {
	state = ClockState.STOPPED;
	minutes = DEFAULT_WORK_MINUTES;
	seconds = DEFAULT_WORK_SECONDS;
    }




}
