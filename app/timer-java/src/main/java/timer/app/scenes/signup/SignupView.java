package timer.app.scenes.signup;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import timer.app.Launcher;
import timer.app.Mode;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;
import timer.fx.mvc.ScreenView;

/**
 * User interface design of signup screen
 * 
 * @author Jazer
 *
 */
public class SignupView extends ScreenView {

  private Button createButton;
  
  /**
   * Creates a new signup interface with the given window, model and controller
   * 
   * @param window     - Window the signup screen will render into
   * @param model      - The signup model
   * @param controller - The signup controller
   */
  public SignupView(Stage window, ScreenModel model, ScreenController controller) {
    super(window, model, controller);
    window.setTitle((Launcher.state == Mode.PRODUCTION ? "" : "DEV | ") + "Quark Signup");
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

    // Create email label for next text box
    Label emailLabel = new Label("Email");

    // Create username input field
    TextField emailInput = new TextField();
    emailInput.setPromptText("Enter Email Here");

    // Create username label for next text box
    Label userLabel = new Label("Username");

    // Create username input field
    TextField userInput = new TextField();
    userInput.setPromptText("Enter Username Here");

    // Create password label for next password box
    Label passwordLabel = new Label("Password");

    // Create password input field
    PasswordField passwordInput = new PasswordField();
    passwordInput.setPromptText("Enter Password Here");
    passwordInput.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        createButton.fire();
      }
    });

    // Create sign up button which takes you to the signup screen
    Button loginButton = new Button("Login");
    loginButton.setPrefWidth(60);
    loginButton.setOnAction(e -> {
      ((SignupController) controller).launchLogin(window);
    });

    // Create login button that attempts a login and forwards you to the app
    createButton = new Button("Create");
    createButton.setPrefWidth(80);
    createButton.setOnAction(e -> {
      
      
      
    });

    // Add all elements to the grid layout (element, x, y, col-span, row-span)
    gridLayout.add(emailLabel, 0, 0);
    gridLayout.add(emailInput, 1, 0, 2, 1);
    gridLayout.add(userLabel, 0, 1);
    gridLayout.add(userInput, 1, 1, 2, 1);
    gridLayout.add(passwordLabel, 0, 2);
    gridLayout.add(passwordInput, 1, 2, 2, 1);
    gridLayout.add(loginButton, 1, 3, 1, 1);
    gridLayout.add(createButton, 2, 3, 1, 1);

    // Return the grid layout containing all elements
    return new Scene(gridLayout);
  }

  @Override
  protected void update() {

  }

}
