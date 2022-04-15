package timer.app.scenes.login;

import javafx.stage.Stage;
import timer.app.scenes.signup.SignupScreen;
import timer.app.scenes.timer.TimerScreen;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

public class LoginController extends ScreenController {

    public LoginController(ScreenModel model) {
	super(model);
    }

    /**
     * Launches the signup screen in a new window
     */
    public void launchSignup(Stage window) {
	new SignupScreen(window);
    }
    
    public void login(Stage window) {
	new TimerScreen(new Stage());
	window.close();
    }
    
}
