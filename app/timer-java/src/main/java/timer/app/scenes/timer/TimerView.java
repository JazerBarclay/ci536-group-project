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

public class TimerView extends ScreenView {

    private static double xOffset = 0;
    private static double yOffset = 0;

    private Node draggableElement;

    private Canvas background;

    private PlayButton btnPlay;
    private StopButton btnStop;

    public TimerView(Stage window, ScreenModel model, ScreenController controller) {
	super(window, model, controller);
	window.setAlwaysOnTop(true);
	window.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    protected Scene constructView() {

	Pane layout = new Pane();
	Scene s = new Scene(layout, 220, 130);

	background = new Canvas(220, 130);
	GraphicsContext gc = background.getGraphicsContext2D();
	gc.setFill(Color.color(.9, .9, .9));
	gc.fillRect(0, 0, 220, 130);

	Label timerLabel = new Label("25:00");
	timerLabel.setLayoutY(130/2-38);
	timerLabel.setFont(new Font("Arial", 48));
	timerLabel.setTextFill(Color.color(0.168, 0.188, 0.231));
	timerLabel.setPrefWidth(220);
	timerLabel.setAlignment(Pos.CENTER);

	CloseButton btnClose = new CloseButton(220-25, 5, 20);
	btnClose.setOnClickHandler(() -> {
	    System.err.println("Exit");
	    window.close();
	});

	btnPlay = new PlayButton((220/2)-10, 130-35, 20);
	btnPlay.setOnClickHandler(() -> {
	    System.err.println("Start"); 
	});

	btnStop = new StopButton((220/2)-10, 130-35, 20);
	btnStop.setOnClickHandler(() -> {
	    System.err.println("Stop"); 
	});

	layout.getChildren().add(background);
	layout.getChildren().add(timerLabel);
	layout.getChildren().add(btnClose);
	layout.getChildren().add(btnPlay);

	setDraggableElement(timerLabel);

	return s;

    }

    @Override
    protected void update() {
	// TODO Auto-generated method stub
    }

    public void setBackground(Paint p) {
	GraphicsContext gc = background.getGraphicsContext2D();
	gc.setFill(p);
	gc.fillRect(0, 0, window.getWidth(), window.getHeight());
    }

    public void setDraggableElement(Node n) {
	if (n == null && draggableElement == null) return;
	if (n == null) {
	    draggableElement.setOnMousePressed((MouseEvent e) -> {});
	    draggableElement.setOnMouseDragged((MouseEvent e) -> {});
	    return;
	}
	draggableElement = n;
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
