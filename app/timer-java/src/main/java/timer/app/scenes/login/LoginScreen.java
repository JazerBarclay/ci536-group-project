package timer.app.scenes.login;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

/**
 * The login screen where users can enter their username and
 * password in order to access the app where their stats can 
 * be logged.
 * 
 * @author Jazer
 *
 */
public class LoginScreen extends Screen {

    /**
     * Creates a new login screen with the given window
     * @param window
     */
    public LoginScreen(Stage window) {
	super(window);
    }

    /**
     * Initialise the model, view, controller and link them
     * together with the change listener
     */
    @Override
    protected void constructMVC(Stage window) {
	
	LoginModel model = new LoginModel();
	LoginController controller = new LoginController(model);
	LoginView view = new LoginView(window, model, controller);
	
	model.setChangeListener(() -> view.repaint());
    }

}
