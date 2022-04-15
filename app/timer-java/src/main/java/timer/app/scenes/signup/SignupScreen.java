package timer.app.scenes.signup;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

public class SignupScreen extends Screen {

    public SignupScreen(Stage s) {
	super(s);
    }

    @Override
    protected void constructMVC(Stage window) {
	
	SignupModel model = new SignupModel();
	SignupController controller = new SignupController(model);
	SignupView view = new SignupView(window, model, controller);
	
	model.setChangeListener( () -> view.repaint() );
	
    }

}
