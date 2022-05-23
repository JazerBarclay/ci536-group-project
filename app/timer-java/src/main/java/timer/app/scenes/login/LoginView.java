package timer.app.scenes.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    private GridPane gridLayout;
    private Label message;
    private TextField emailInput;
    private PasswordField passwordInput;
    private Button signupButton;
    private Button loginButton;

    /**
     * Creates a new login interface with the given window, model and controller
     * 
     * @param window - Window the login screen will render into
     * @param model - The login model
     * @param controller - The login controller
     */
    public LoginView(Stage window, ScreenModel model, ScreenController controller) {
	super(window, model, controller);
	window.setTitle("Quark Login");
	window.setResizable(false);
    }

    /**
     * Builds the user interface with the desired elements
     */
    @Override
    protected Scene constructView() {

	// Create grid layout container
	gridLayout = new GridPane();
	gridLayout.setHgap(10);
	gridLayout.setVgap(10);
	gridLayout.setPadding(new Insets(20, 40, 30, 30));

	// Create username label for next text box
	message = new Label("Login Failed - Please Try Again");
	message.setMaxWidth(Double.MAX_VALUE);
	message.setAlignment(Pos.CENTER);

	// Create username label for next text box
	Label emailLabel = new Label("Email");

	// Create username input field
	emailInput = new TextField();
	emailInput.setPromptText("Enter Email Here");

	// Create password label for next password box
	Label passwordLabel = new Label("Password");

	// Create password input field
	passwordInput = new PasswordField();
	passwordInput.setPromptText("Enter Password Here");

	// Create sign up button which takes you to the signup screen
	signupButton = new Button("Signup");
	signupButton.setPrefWidth(60);

	// Create login button that attempts a login and forwards you to the app
	loginButton = new Button("Login");
	loginButton.setPrefWidth(80);

	// Add all elements to the grid layout (element, x, y, col-span, row-span)
	gridLayout.add(emailLabel, 0, 1);
	gridLayout.add(emailInput, 1, 1, 2, 1);
	gridLayout.add(passwordLabel, 0, 2);
	gridLayout.add(passwordInput, 1, 2, 2, 1);
	gridLayout.add(signupButton, 1, 3, 1, 1);
	gridLayout.add(loginButton, 2, 3, 1, 1);

	// Add action handlers set in defined function
	setupActionHandlers();

	// Return the grid layout containing all elements
	return new Scene(gridLayout);

    }

    protected void setupActionHandlers() {

	// TODO: Move functionality to login controller and store stats to model

	// Handle login process
	loginButton.setOnAction(e -> {

	    // Remove any existing error label
	    if (gridLayout.getChildren().contains(message)) gridLayout.getChildren().remove(message);

	    // Resize window to fit content
	    window.sizeToScene();

	    // Try login
	    boolean success = ((LoginController) controller).login(window, emailInput.getText(), passwordInput.getText());

	    // If successful then exit here
	    if (success) return;

	    // Since we didnt succeed, add (potentially back) error message
	    if (!gridLayout.getChildren().contains(message)) gridLayout.add(message, 0, 0, 3, 1);

	    // Resize window to fit content
	    window.sizeToScene();

	});

	// Handle signup process
	signupButton.setOnAction(e -> {
	    ((LoginController) controller).launchSignup(window);
	});

    }


    @Override
    protected void update() {
	// Currently usused since all buttons handle themselves for now
    }

}
