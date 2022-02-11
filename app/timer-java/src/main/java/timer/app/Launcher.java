package timer.app;

import javafx.application.Application;
import javafx.scene.Scene;
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

  }

}
