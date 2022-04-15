package timer.app;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class View {
    
    // 
    private Stage window;
    private Screen screen;
    private Model model;

    private Canvas bar;

    // Timer
    private Label minutes;
    private Label seconds;

    // Scenes
    private Scene scene1, scene2;

    
    /**
     * Constructs a new view containing login and main app scenes
     * @param window
     * @param model
     * @param controller
     */
    public View(Stage window, Model model, Controller controller) {
	this.window = window;
	this.model = model;

	GridPane grid = new GridPane();
	grid.setPadding(new Insets(10, 10, 10, 10));
	grid.setVgap(8);
	grid.setHgap(10);

	GridPane grid2 = new GridPane();
	grid2.setPadding(new Insets(10, 10, 10, 10));
	grid2.setVgap(8);
	grid2.setHgap(3);

	// Buttons
	Button loginButton = new Button("Login");
	loginButton.setOnAction(e -> {
	    controller.changescene(window, scene2);
	});
	
	grid.add(loginButton, 0, 3, 1, 1);

	Button signupButton = new Button("Sign up");
	signupButton.setOnAction(e -> {
	    controller.example();
	});
	grid.add(signupButton, 1, 3, 1, 1);

	Button logout = new Button("Logout");
	logout.setOnAction(e -> {
	    Stage s = new Stage();
	    s.setScene(new Scene(new HBox(4, new Label("Hi"))));
	    s.show();
	    window.close();
//	    controller.changescene(window, scene1);
	});

	Button startButton = new Button("Start Timer");
	startButton.setOnAction(e -> {
	    controller.startsTimer();
	});

	Button stopButton = new Button("Stop Timer");
	stopButton.setOnAction(e -> {
	    controller.stopsTimer();
	});

	// Intro Login Layout
	// Username Label
	Label userLabel = new Label("Username");
	grid.add(userLabel, 0, 0, 1, 1);

	// Username Input
	TextField userInput = new TextField(("Enter Username here"));
	grid.add(userInput, 1, 0, 1, 1);

	// Password Label
	Label passwordLabel = new Label("Password");
	grid.add(passwordLabel, 0, 1, 1, 1);

	// Password Input
	TextField passwordInput = new TextField(("Enter Password here"));
	passwordInput.setPromptText("Password");
	grid.add(passwordInput, 1, 1, 1, 1);

	// Timer
	minutes = new Label(("" + model.getMinutes()));
	grid2.add(minutes, 2, 0, 1, 1);

	seconds = new Label(("" + model.getSeconds()));
	grid2.add(seconds, 3, 0, 1, 1);

	// Layout 2
	grid2.add(logout, 1, 1, 1, 1);
	grid2.add(startButton, 2, 1, 1, 1);
	grid2.add(stopButton, 3, 1, 1, 1);
	scene2 = new Scene(grid2, 320, 200);

	scene1 = new Scene(grid, 320, 200);
	window.setScene(scene1);
    }

    /**
     * This is run when a change is made within the model
     */
    public void update() {
	Platform.runLater(() -> {
	    minutes.setText(model.getMinutes() + "");
	    seconds.setText(model.getSeconds() + "");
	});
    }

}
