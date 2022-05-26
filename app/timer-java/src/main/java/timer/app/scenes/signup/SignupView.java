package timer.app.scenes.signup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

  private GridPane gridLayout;
  private Label message;
  private TextField emailInput;
  private TextField userInput;
  private PasswordField passwordInput;
  private Button loginButton;
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
    window.getIcons().add(new Image(this.getClass().getResourceAsStream("/desktop.png")));
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
    gridLayout.setPadding(new Insets(30, 40, 40, 30));

    // Create username label for next text box
    message = new Label("Signup Failed - Email or Username in use");
    message.setMaxWidth(Double.MAX_VALUE);
    message.setAlignment(Pos.CENTER);
    
    // Create email label for next text box
    Label emailLabel = new Label("Email");

    // Create username input field
    emailInput = new TextField();
    emailInput.setPromptText("Enter Email Here");

    // Create username label for next text box
    Label userLabel = new Label("Username");

    // Create username input field
    userInput = new TextField();
    userInput.setPromptText("Enter Username Here");

    // Create password label for next password box
    Label passwordLabel = new Label("Password");

    // Create password input field
    passwordInput = new PasswordField();
    passwordInput.setPromptText("Enter Password Here");


    // Create sign up button which takes you to the signup screen
    loginButton = new Button("Login");
    loginButton.setPrefWidth(60);


    // Create login button that attempts a login and forwards you to the app
    createButton = new Button("Create");
    createButton.setPrefWidth(80);


    // Add all elements to the grid layout (element, x, y, col-span, row-span)
    gridLayout.add(emailLabel, 0, 1);
    gridLayout.add(emailInput, 1, 1, 2, 1);
    gridLayout.add(userLabel, 0, 2);
    gridLayout.add(userInput, 1, 2, 2, 1);
    gridLayout.add(passwordLabel, 0, 3);
    gridLayout.add(passwordInput, 1, 3, 2, 1);
    gridLayout.add(loginButton, 1, 4, 1, 1);
    gridLayout.add(createButton, 2, 4, 1, 1);

    // Add action handlers set in defined function
    setupActionHandlers();
    
    // Return the grid layout containing all elements
    return new Scene(gridLayout);
  }
  
  protected void setupActionHandlers() {

    // TODO: Move functionality to controller and store stats to model

    // Handle signup process
    createButton.setOnAction(e -> {
      if (gridLayout.getChildren().contains(message))
        gridLayout.getChildren().remove(message);
      
      // Resize window to fit content
      window.sizeToScene();
      
      boolean success = ((SignupController) controller).signup(window, emailInput.getText(), userInput.getText(), passwordInput.getText());
      
      // If successful then exit here
      if (success)
        return;
      
      // Since we didnt succeed, add (potentially back) error message
      if (!gridLayout.getChildren().contains(message))
        gridLayout.add(message, 0, 0, 3, 1);

      // Resize window to fit content
      window.sizeToScene();
      
    });
    
    passwordInput.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        createButton.fire();
      }
    });
    
    // Go to login screen
    loginButton.setOnAction(e -> {
      ((SignupController) controller).launchLogin(window);
    });

  }

  @Override
  protected void update() {
    // Currently usused since all buttons handle themselves for now
  }

}
