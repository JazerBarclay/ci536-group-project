package timer.app.scenes.timer;

import javafx.stage.Stage;
import timer.api.API;
import timer.api.HttpResponse;
import timer.app.Launcher;
import timer.app.Mode;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

/**
 * Interaction management for timer screen
 * 
 * @author Jazer
 *
 */
public class TimerController extends ScreenController {

  private TimerModel model;

  /**
   * Create a new controller with the given model to interact with
   * 
   * @param model
   */
  public TimerController(ScreenModel model) {
    super(model);
    this.model = (TimerModel) model;
  }

  public void closeTimer(Stage window) {
    model.stopTimer();
    window.close();
  }

  public void startTimer(Stage window) {
//    if (!updateToken()) {
//      new LoginScreen(new Stage());
//      window.close();
//    }
    model.startTimer();
  }

  public void stopTimer() {
    model.stopTimer();
  }

  public boolean updateToken() {
    HttpResponse res = null;
    String newToken = "";
    
    if (Launcher.state == Mode.DEV)
      res = API.getRequest(TimerScreen.loginToken, "https://api.quark.rocks/login/renew");
    else if (Launcher.state == Mode.PRODUCTION)
      API.getRequest(TimerScreen.loginToken, "https://api.quark.rocks/unit");
    else
      API.getRequest(TimerScreen.loginToken, "http://[::1]:4000/unit");

    if (res.getResponseCode() != 200) return false;
    
    newToken = res.getBody().toString().substring(10, res.getBody().length() - 2);
    TimerScreen.loginToken = newToken;
    return true;
  }
  
  
  
}
