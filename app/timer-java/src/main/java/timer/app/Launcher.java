package timer.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

  Stage window;
  Scene intro, picture;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {

    window = stage;

    Label welcomemsg = new Label("This is the login screen");
    Button logger = new Button("Login");
    logger.setOnAction(e -> window.setScene(picture));

    //Intro Login Layout
    VBox intro1 = new VBox(20);
    intro1.getChildren().addAll(welcomemsg,logger);
    intro = new Scene(intro1, 1280, 720);

    // Picture scene

    Button previous = new Button("Logout");
    previous.setOnAction(e -> window.setScene(intro));

    // Picture Layout
    StackPane picturelayout = new StackPane();
    picturelayout.getChildren().addAll(previous);
    picture = new Scene(picturelayout, 1280, 720);

    window.setScene(intro);
    window.setTitle("Timer");
    window.show();

  }

}
