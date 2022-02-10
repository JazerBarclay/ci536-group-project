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
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version1");
    Button button = new Button();
    button.setText("Fetch API tingz");
    Scene scene = new Scene(new StackPane(button), 640, 480);
    stage.setScene(scene);
    stage.show();


  }

}
