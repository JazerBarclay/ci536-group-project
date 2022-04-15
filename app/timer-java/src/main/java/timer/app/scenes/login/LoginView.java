package timer.app.scenes.login;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;
import timer.fx.mvc.ScreenView;

/**
 * User interface design of login screen
 * 
 * @author Jazer
 *
 */
public class LoginView extends ScreenView {

    /**
     * Creates a new login interface with the given window, model and controller
     * 
     * @param window - Window the login screen will render into
     * @param model - The login model
     * @param controller - The login controller
     */
    public LoginView(Stage window, ScreenModel model, ScreenController controller) {
	super(window, model, controller);
	window.setTitle("Login");
    }

    /**
     * Builds the user interface with the desired elements
     */
    @Override
    protected Scene constructView() {
	
	// Create grid layout container
	GridPane gridLayout = new GridPane();
	gridLayout.setHgap(10);
	gridLayout.setVgap(10);
	gridLayout.setPadding(new Insets(30, 40, 40, 30));
	
	// Create username label for next text box
	Label userLabel = new Label("Username");
	
	// Create username input field
	TextField userInput = new TextField();
	userInput.setPromptText("Enter Username Here");
	
	// Create password label for next password box
	Label passwordLabel = new Label("Password");
	
	// Create password input field
	PasswordField  passwordInput = new PasswordField();
	passwordInput.setPromptText("Enter Password Here");
	
	// Create sign up button which takes you to the signup screen
	Button signupButton = new Button("Signup");
	signupButton.setPrefWidth(60);
	signupButton.setOnAction(e -> {
	    ((LoginController)controller).launchSignup(window);
	});

	// Create login button that attempts a login and forwards you to the app
	Button loginButton = new Button("Login");
	loginButton.setPrefWidth(80);
	loginButton.setOnAction(e -> {
	    ((LoginController)controller).login(window);
	});

	// Add all elements to the grid layout (element, x, y, col-span, row-span)
	gridLayout.add(userLabel, 0, 0);
	gridLayout.add(userInput, 1, 0, 2, 1);
	gridLayout.add(passwordLabel, 0, 1);
	gridLayout.add(passwordInput, 1, 1, 2, 1);
	gridLayout.add(signupButton, 1, 2, 1, 1);
	gridLayout.add(loginButton, 2, 2, 1, 1);
	
	// Return the grid layout containing all elements
	return new Scene(gridLayout);
    }


    @Override
    protected void update() {
	// TODO: Add update based on login attempt details or something like that
    }

}
