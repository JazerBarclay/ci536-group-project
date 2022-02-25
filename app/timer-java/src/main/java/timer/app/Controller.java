package timer.app;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    private Model model;

    public Controller(Model model) {
	this.model = model;

    }

    public void example(){
	System.out.println("Something");
    }

    public void changescene(Stage window, Scene scene){
	window.setScene(scene);
    }

    public void startsTimer(){
        model.startTimer();
    }

    public void authenticateUser() {

	// On fail, return false, else return true
	 model.authenticated(false);

    }

}
