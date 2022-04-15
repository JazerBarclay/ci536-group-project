package timer.app.scenes.login;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

public class LoginScreen extends Screen {

    public LoginScreen(Stage s) {
	super(s);
    }

    @Override
    protected void constructMVC(Stage window) {
	
	LoginModel model = new LoginModel();
	LoginController controller = new LoginController(model);
	LoginView view = new LoginView(window, model, controller);
	
	model.setChangeListener( () -> view.repaint() );
	
    }

}
