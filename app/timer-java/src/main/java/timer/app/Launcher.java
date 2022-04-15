package timer.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timer.app.scenes.login.LoginScreen;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
	
	// Launch the login screen with the given starting stage
	new LoginScreen(stage);
    
    }
    
    public static void main(String[] args) {
	launch(args);
    }

}
