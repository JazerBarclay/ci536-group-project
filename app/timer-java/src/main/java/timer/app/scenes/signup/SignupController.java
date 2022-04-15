package timer.app.scenes.signup;

import javafx.stage.Stage;
import timer.app.scenes.login.LoginScreen;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

public class SignupController extends ScreenController {

    public SignupController(ScreenModel model) {
	super(model);
    }
    
    public void launchLogin(Stage window) {
	new LoginScreen(window);
    }

}
