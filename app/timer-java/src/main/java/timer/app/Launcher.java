package timer.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launcher extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    stage.setTitle("Timer");
    Button button = new Button();
    button.setText("Fetch API tingz");

    // sets action for button when clicked using lambda expressions
    button.setOnAction(e -> System.out.println("Hola"));

    Scene scene = new Scene(new StackPane(button), 1280 , 720);
    scene.getStylesheets().add("res/stylesheet.css");
    stage.setScene(scene);
    stage.show();

  }

}
