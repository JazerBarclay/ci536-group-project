package timer.app.scenes.signup;

import javafx.stage.Stage;
import timer.app.scenes.login.LoginScreen;
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

}
