package timer.app.scenes.signup;

import java.io.IOException;
import java.util.HashMap;

import javafx.stage.Stage;
import timer.api.API;
import timer.api.HttpResponse;
import timer.app.Launcher;
import timer.app.Mode;
import timer.app.scenes.login.LoginScreen;
import timer.app.scenes.timer.TimerScreen;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

/**
 * Interaction management for signup screen
 * 
 * @author Jazer
 *
 */
public class SignupController extends ScreenController {

  /**
   * Create a new controller with the given model to interact with
   * 
   * @param model
   */
  public SignupController(ScreenModel model) {
    super(model);
  }

  /**
   * Launches the login screen in the given window
   */
  public void launchLogin(Stage window) {
    new LoginScreen(window);
  }

  public boolean signup(Stage window, String email, String username, String password) {
    
    // Ensure email is valid
    if (!validateEmail(email))
      return false;
    
 // Send POST request to API
    HttpResponse res = null;

    // API endpoint based on launch conditions
    String endPoint = "";
    if (Launcher.state == Mode.DEV)
      endPoint = "https://dev.api.quark.rocks/register";
    else if (Launcher.state == Mode.PRODUCTION)
      endPoint = "https://api.quark.rocks/register";
    else
      endPoint = "http://[::1]:4000/register";

    try {
      res = API.postRequest(endPoint, new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("email", email);
          put("username", username);
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
    if (res.getResponseCode() != 201)
      return false;

    // Open the timer screen with the login token
    new LoginScreen(new Stage());

    // Close this window now we are done!
    window.close();

    return true;
    
  }

  //TODO: Add validation
  private boolean validateEmail(String email) {
    return true;
  }

}
