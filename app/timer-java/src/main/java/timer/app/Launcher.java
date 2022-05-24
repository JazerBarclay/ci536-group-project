package timer.app;

import javafx.application.Application;
import javafx.stage.Stage;
import timer.app.scenes.login.LoginScreen;

public class Launcher extends Application {

    public static Mode state;

    @Override
    public void start(Stage stage) throws Exception {
	// Launch the login screen with the given starting stage
	new LoginScreen(stage);
    }

    public static void main(String[] args) {
		System.out.println(args[0]);
		if (args.length > 0) launchMode(args[0]);
		else state = Mode.LOCAL;
		launch(args);
    }

    private static void launchMode(String arg) {
	switch (arg.toLowerCase().trim()) {
    	    case "local":
        	state = Mode.LOCAL;
        	break;
            case "development":
            case "dev":
        	state = Mode.DEV;
        	break;
            case "production":
            case "prod":
        	state = Mode.PRODUCTION;
        	break;
            default:
        	state = Mode.LOCAL;
        	break;
	}
    }
    

}
