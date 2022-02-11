package timer.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10,10,10,10));
    grid.setVgap(8);
    grid.setHgap(10);

    GridPane grid2 = new GridPane();
    grid2.setPadding(new Insets(10,10,10,10));
    grid2.setVgap(8);
    grid2.setHgap(10);

    // Intro Login Layout
    // Username Label
    Label userLabel = new Label("Username");
    grid.add(userLabel,2,0);

    // Username Input
    TextField userInput = new TextField(("Username"));
    grid.add(userInput,4,0);

    // Password Label
    Label passwordLabel = new Label("Password");
    grid.add(passwordLabel,2,4);

    // Password Input
    TextField passinput = new TextField(("Test2"));
    passinput.setPromptText("Password");
    grid.add(passinput,4,4);

    // Buttons
    Button loginButton = new Button("Login");
    loginButton.setOnAction(e -> {
      window.setScene(scene2);
    });
    grid.add(loginButton,4,6);

    Button previousButton= new Button("Previous button");
    previousButton.setOnAction(e -> {
      window.setScene(scene1);
    });

    grid2.add(previousButton,7,6);
    scene2 = new Scene(grid2, 1280,720);


    //Layout 1
    Scene scene1 = new Scene(grid, 1280,720);
    scene1.getStylesheets().add("res/stylesheet.css");

    //Layout 2



    window.setScene(scene1);
    window.show();
    window.setTitle("Timer");




  }

}
