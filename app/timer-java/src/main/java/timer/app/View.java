package timer.app;

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

    Scene scene,scene1, scene2;

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
        grid2.setHgap(10);

        // Buttons
        javafx.scene.control.Button loginButton = new javafx.scene.control.Button("Login");
        loginButton.setOnAction(e -> {
            controller.changescene(window, scene2);
        });
        grid.add(loginButton,42,46);

        javafx.scene.control.Button signup = new javafx.scene.control.Button("Sign up");
        signup.setOnAction(e -> {
            controller.example();
        });
        grid.add(signup,43,46);

        javafx.scene.control.Button previousButton= new Button("Previous button");
        previousButton.setOnAction(e -> {
            window.setScene(scene1);
        });

        // Intro Login Layout
        // Username Label
        javafx.scene.control.Label userLabel = new javafx.scene.control.Label("Username");
        grid.add(userLabel,40,40);

        // Username Input
        javafx.scene.control.TextField userInput = new javafx.scene.control.TextField(("Username"));
        grid.add(userInput,42,40);

        // Password Label
        javafx.scene.control.Label passwordLabel = new javafx.scene.control.Label("Password");
        grid.add(passwordLabel,40,44);

        // Password Input
        javafx.scene.control.TextField passinput = new javafx.scene.control.TextField(("Test2"));
        passinput.setPromptText("Password");
        grid.add(passinput,42,44);


        //Layout 2
        grid2.add(previousButton,42,46);
        scene2 = new Scene(grid2, 1280,720);

        //Layout 1
        scene1 = new Scene(grid, 1280,720);

        window.setScene(scene1);


    }


}
