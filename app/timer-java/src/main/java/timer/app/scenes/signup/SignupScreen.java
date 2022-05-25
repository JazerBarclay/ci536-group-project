package timer.app.scenes.signup;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

/**
 * The signup screen where users can enter their email, desired username and
 * password in order to create a new account. Once complete, they can be
 * forwarded to the login screen to attempt a login with their newly set account
 * details.
 * 
 * @author Jazer
 *
 */
public class SignupScreen extends Screen {

  /**
   * Creates a new signup screen with the given window
   * 
   * @param window
   */
  public SignupScreen(Stage window) {
    super(window);
  }

  /**
   * Initialise the model, view, controller and link them together with the change
   * listener
   */
  @Override
  protected void constructMVC(Stage window) {

    SignupModel model = new SignupModel();
    SignupController controller = new SignupController(model);
    SignupView view = new SignupView(window, model, controller);

    model.setChangeListener(() -> view.repaint());

  }

}
