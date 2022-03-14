package timer.app;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class View {
    private Stage window;
    private Screen screen;
    private Model model;

    private Canvas bar;

    //Timer
    javafx.scene.control.Label minutes;
    javafx.scene.control.Label seconds;

    Scene scene, scene1, scene2;

    public View(Stage window, Model model, Controller controller){
	this.window = window;
	this.model = model;

	GridPane grid = new GridPane();
	grid.setPadding(new javafx.geometry.Insets(10,10,10,10));
	grid.setVgap(8);
	grid.setHgap(10);


	GridPane grid2 = new GridPane();
	grid2.setPadding(new Insets(10,10,10,10));
	grid2.setVgap(8);
	grid2.setHgap(3);

	// Buttons
	javafx.scene.control.Button loginButton = new javafx.scene.control.Button("Login");
	loginButton.setOnAction(e -> {
	    controller.changescene(window, scene2);
	});
	grid.add(loginButton,0,3,1,1);

	javafx.scene.control.Button signupButton = new javafx.scene.control.Button("Sign up");
	signupButton.setOnAction(e -> {
	    controller.example();
	});
	grid.add(signupButton,1,3,1,1);

	javafx.scene.control.Button logout = new Button("Logout");
	logout.setOnAction(e -> {
	    controller.changescene(window, scene1);
	});

	javafx.scene.control.Button startButton = new Button("Start Timer");
	startButton.setOnAction(e -> {
		controller.startsTimer();

	});

	javafx.scene.control.Button stopButton = new Button("Stop Timer");
	stopButton.setOnAction(e -> {
		controller.stopsTimer();

	});

	// Intro Login Layout
	// Username Label
	javafx.scene.control.Label userLabel = new javafx.scene.control.Label("Username");
	grid.add(userLabel,0,0, 1,1);

	// Username Input
	javafx.scene.control.TextField userInput = new javafx.scene.control.TextField(("Enter Username here"));
	grid.add(userInput,1,0,1,1);

	// Password Label
	javafx.scene.control.Label passwordLabel = new javafx.scene.control.Label("Password");
	grid.add(passwordLabel,0,1,1,1);

	// Password Input
	javafx.scene.control.TextField passwordInput = new javafx.scene.control.TextField(("Enter Password here"));
	passwordInput.setPromptText("Password");
	grid.add(passwordInput,1,1,1,1);

	//Timer
	minutes = new javafx.scene.control.Label(("" + model.getMinutes()));
	grid2.add(minutes, 2 , 0,1,1);

//	seconds  = new javafx.scene.control.Label(("" + model.getSeconds()));
	seconds  = new javafx.scene.control.Label(("" + model.getSeconds()));
	grid2.add(seconds, 3, 0,1,1);


	//Layout 2
	grid2.add(logout,1,1,1,1);
	grid2.add(startButton, 2, 1,1,1);
	grid2.add(stopButton, 3, 1,1,1);
	scene2 = new Scene(grid2, 320,200);

	//Layout 1
	scene1 = new Scene(grid, 320,200);
	window.setScene(scene1);
    }

    public void update() {

	Platform.runLater(() -> {
	    minutes.setText(model.getMinutes() + "");
	    seconds.setText(model.getSeconds()+ "");
	});
    }


}
