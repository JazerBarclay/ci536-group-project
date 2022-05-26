package timer.app.scenes.timer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.stage.Stage;
import timer.api.API;
import timer.api.HttpResponse;
import timer.app.ClockState;
import timer.app.Launcher;
import timer.app.Mode;
import timer.app.scenes.login.LoginScreen;
import timer.fx.mvc.ScreenModel;

/**
 * Model of timer screen
 * 
 * @author Jazer
 *
 */
public class TimerModel extends ScreenModel {

  // Default work and rest minutes and seconds
  private static int DEFAULT_WORK_MINUTES = 0;
  private static int DEFAULT_WORK_SECONDS = 3;
  private static int DEFAULT_REST_MINUTES = 0;
  private static int DEFAULT_REST_SECONDS = 2;

  // Hold the pomodoro units
  private int pomUnits = 0;

  // Security token used to login
  protected String jwt;

  // Timer State (Working, Resting, Stopped)
  protected ClockState state;

  // Current minutes and seconds
  protected int minutes;
  protected int seconds;

  protected ScheduledExecutorService timer;

  protected Timestamp startTime, endTime;

  /**
   * Creates a new timer model with the login web token from login screen.
   * 
   * Initialises the clock to stopped
   * 
   * @param jwt
   */
  public TimerModel(String jwt) {
    this.jwt = jwt;
    setStopped();
  }

  public void startTimer() {
    setWorking();
    startTime = new Timestamp(System.currentTimeMillis());
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
    if (minutes == 0 && seconds < 0) {
      if (state == ClockState.WORKING) {
        setResting();
        return;
      } else if (state == ClockState.RESTING) {
        endTime = new Timestamp(System.currentTimeMillis());
        sendPomodoro();
        pomUnits += 1;
        stopTimer();
      }
      stopTimer();
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

  protected void sendPomodoro() {
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("label", "");
    params.put("start", startTime.toString() + "000");
    params.put("end", endTime.toString() + "000");
    try {
      if (Launcher.state == Mode.DEV)
        API.postRequest(TimerScreen.loginToken, "https://api.quark.rocks/unit", params);
      else if (Launcher.state == Mode.PRODUCTION)
        API.postRequest(TimerScreen.loginToken, "https://api.quark.rocks/unit", params);
      else
        API.postRequest(TimerScreen.loginToken, "http://[::1]:4000/unit", params);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
