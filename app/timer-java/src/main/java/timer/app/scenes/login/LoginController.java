package timer.app.scenes.login;

import javafx.stage.Stage;
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
     * Log into the user account and upon success launch the timer screen
     * in the given window
     * 
     * @param window
     */
    public void login(Stage window) {
	new TimerScreen(new Stage());
	window.close();
    }
    
}
