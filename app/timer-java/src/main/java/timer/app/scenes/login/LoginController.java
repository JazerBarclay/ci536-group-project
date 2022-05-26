package timer.app.scenes.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import javafx.stage.Stage;
import timer.api.API;
import timer.api.HttpResponse;
import timer.app.Launcher;
import timer.app.Mode;
import timer.app.scenes.signup.SignupScreen;
import timer.app.scenes.timer.TimerScreen;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

/**
 * Interaction management for login screen
 * 
 * @author Jazer
 *
 */
public class LoginController extends ScreenController {

  /**
   * Create a new controller with the given model to interact with
   * 
   * @param model
   */
  public LoginController(ScreenModel model) {
    super(model);
  }

  /**
   * Launches the signup screen in the given window
   */
  public void launchSignup(Stage window) {
    new SignupScreen(window);
  }

  /**
   * Log into the user account and upon success launch the timer screen in the
   * given window
   * 
   * @param window
   */
  public boolean login(Stage window, String email, String password) {

    // Ensure email is valid
    if (!validateEmail(email))
      return false;

    // Send POST request to API
    HttpResponse res = null;

    // API endpoint based on launch conditions
    String endPoint = "";
    if (Launcher.state == Mode.DEV)
      endPoint = "https://dev.api.quark.rocks/login";
    else if (Launcher.state == Mode.PRODUCTION)
      endPoint = "https://api.quark.rocks/login";
    else
      endPoint = "http://[::1]:4000/login";

    try {
      res = API.postRequest(endPoint, new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("email", email);
          put("password", password);
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

    // If we error out and res remains null, return
    if (res == null)
      return false;

    // If we got a response other than 200 then return
    if (res.getResponseCode() != 200)
      return false;

    // We passed the gauntlet, we can get the token by substringing the response
    // body
    String token = res.getBody().toString().substring(10, res.getBody().length() - 2);

    // Open the timer screen with the login token
    new TimerScreen(new Stage(), token);

    // Close this window now we are done!
    window.close();

    return true;
  }

  private boolean validateEmail(String email) {
    Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$");
    return pattern.matcher(email).matches();
  }

}
