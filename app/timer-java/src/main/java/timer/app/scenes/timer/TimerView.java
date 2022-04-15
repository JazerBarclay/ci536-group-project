package timer.app.scenes.timer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;
import timer.fx.mvc.ScreenView;
import timer.fx.widgets.CloseButton;
import timer.fx.widgets.PlayButton;
import timer.fx.widgets.StopButton;

/**
 * User interface design of timer screen
 * 
 * @author Jazer
 *
 */
public class TimerView extends ScreenView {

    /** The element the window can be dragged by **/
    private Node draggableElement;
    
    // The delta of the window from the screen on drag
    private static double xOffset = 0;
    private static double yOffset = 0;

    // Set window width and height in easy variables to use
    private static int windowWidth = 220, windowHeight = 130;
    
    /** Window background canvas **/
    private Canvas background;

    // Interactive elements
    private PlayButton btnPlay;
    private StopButton btnStop;

    /**
     * Creates a new timer interface with the given window, model and controller
     * 
     * @param window - Window the timer screen will render into
     * @param model - The timer model
     * @param controller - The timer controller
     */
    public TimerView(Stage window, ScreenModel model, ScreenController controller) {
	super(window, model, controller);
	window.setAlwaysOnTop(true);
	window.initStyle(StageStyle.UNDECORATED);
    }
    
    /**
     * Builds the user interface with the desired elements
     */
    @Override
    protected Scene constructView() {

	// Create layout and scene
	Pane layout = new Pane();

	// Setup background canvas with base colour
	background = new Canvas(windowWidth, windowHeight);
	GraphicsContext gc = background.getGraphicsContext2D();
	gc.setFill(Color.color(.9, .9, .9));
	gc.fillRect(0, 0, windowWidth, windowHeight);

	// Create timer label where main timer can be viewed
	Label timerLabel = new Label("25:00");
	timerLabel.setLayoutY(windowHeight/2-38);
	timerLabel.setFont(new Font("Arial", 48));
	timerLabel.setTextFill(Color.color(0.168, 0.188, 0.231));
	timerLabel.setPrefWidth(windowWidth);
	timerLabel.setAlignment(Pos.CENTER);

	// Create close button
	CloseButton btnClose = new CloseButton(windowWidth-25, 5, 20);
	btnClose.setOnClickHandler(() -> {
	    System.err.println("Exit");
	    window.close();
	});

	// Create play button
	btnPlay = new PlayButton((windowWidth/2)-10, windowHeight-35, 20);
	btnPlay.setOnClickHandler(() -> {
	    System.err.println("Start");
	});

	// Create stop button
	btnStop = new StopButton((windowWidth/2)-10, windowHeight-35, 20);
	btnStop.setOnClickHandler(() -> {
	    System.err.println("Stop");
	});

	// Add elements to layout
	layout.getChildren().add(background);
	layout.getChildren().add(timerLabel);
	layout.getChildren().add(btnClose);
	layout.getChildren().add(btnPlay);

	// Set time label as draggable element
	setDraggableElement(timerLabel);

	// Return the scene with all elements in
	return new Scene(layout, windowWidth, windowHeight);

    }

    @Override
    protected void update() {
	// TODO Auto-generated method stub
    }

    /**
     * Sets the background colour to the paint colour given
     * @param colour
     */
    public void setBackground(Paint colour) {
	GraphicsContext gc = background.getGraphicsContext2D();
	gc.setFill(colour);
	gc.fillRect(0, 0, window.getWidth(), window.getHeight());
    }

    /**
     * Sets a given node as a draggable element. Used to solve
     * the issue where window is undecorrated and needs to be
     * movable.
     * @param node
     */
    public void setDraggableElement(Node node) {
	if (node == null && draggableElement == null) return;
	if (node == null) {
	    draggableElement.setOnMousePressed((MouseEvent e) -> {});
	    draggableElement.setOnMouseDragged((MouseEvent e) -> {});
	    return;
	}
	draggableElement = node;
	draggableElement.setOnMousePressed((MouseEvent event) -> {
	    xOffset = window.getX() - event.getScreenX();
	    yOffset = window.getY() - event.getScreenY();
	});

	draggableElement.setOnMouseDragged((MouseEvent event) -> {
	    window.setX(event.getScreenX() + xOffset);
	    window.setY(event.getScreenY() + yOffset);
	});
    }

    public void positionBottomRight() {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();

	window.setX(width - window.getWidth());
	window.setY(height - window.getHeight() - 40);
    }

}
