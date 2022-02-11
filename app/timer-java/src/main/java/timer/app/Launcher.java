package timer.app;

import javafx.application.Application;
import javafx.scene.Scene;
<<<<<<< HEAD
=======
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
>>>>>>> 13ec243b2b00db341f0aca91d80065e57180f153
import javafx.stage.Stage;

public class Launcher extends Application {

  Stage window;
  Scene scene1, scene2;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {

    window = stage;
    Model model = new Model();
    View view = new View(window, model);
    Controller controller = new Controller(model);

    

<<<<<<< HEAD
=======
    GridPane grid2 = new GridPane();
    grid2.setPadding(new Insets(10,10,10,10));
    grid2.setVgap(8);
    grid2.setHgap(10);

    // Intro Login Layout
    // Username Label
    Label userLabel = new Label("Username");
    grid.add(userLabel,40,40);

    // Username Input
    TextField userInput = new TextField(("Username"));
    userInput.setPromptText("Username");
    grid.add(userInput,42,40);

    // Password Label
    Label passwordLabel = new Label("Password");
    grid.add(passwordLabel,40,44);

    // Password Input
    PasswordField passInput = new PasswordField();
    passInput.setPromptText("Password");
    grid.add(passInput,42,44);

    // Buttons
    Button loginButton = new Button("Login");
    loginButton.setOnAction(e -> {
      window.setScene(scene2);
    });
    grid.add(loginButton,42,46);




    Button signup = new Button("Sign up");
    signup.setOnAction(e -> {
      System.out.println("Signed up");
    });
    grid.add(signup,43,46);

    Button previousButton = new Button("Previous button");
    previousButton.setOnAction(e -> {
      window.setScene(scene1);
    });

    //Layout 2
    grid2.add(previousButton,42,46);
    scene2 = new Scene(grid2, 1280,720);

    //Layout 1
    scene1 = new Scene(grid, 1280,720);
>>>>>>> 13ec243b2b00db341f0aca91d80065e57180f153


    window.setScene(scene1);
    window.show();
    window.setTitle("Timer");




  }

}
