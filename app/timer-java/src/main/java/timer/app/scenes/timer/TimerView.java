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
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import timer.app.Launcher;
import timer.app.Mode;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;
import timer.fx.mvc.ScreenView;
import timer.fx.widgets.CloseButton;
import timer.fx.widgets.PlayButton;
import timer.fx.widgets.StopButton;
import timer.fx.widgets.PomodoroUnit;

/**
 * User interface design of timer screen
 * 
 * @author Jazer
 *
 */
public class TimerView extends ScreenView {

  // Store cast copy of super model and controller (seen in construct view method)
  private TimerModel model;
  private TimerController controller;

  /** The element the window can be dragged by **/
  private Node draggableElement;

  // The delta of the window from the screen on drag
  private static double xOffset = 0;
  private static double yOffset = 0;

  // Set window width and height in easy variables to use
  private static int windowWidth = 220, windowHeight = 130;

  private Pane layout;

  /** Window background canvas **/
  private Canvas background;

  /** Label holding the time remaining **/
  private Label timerLabel;

  // Interactive elements
  private PlayButton btnPlay;
  private StopButton btnStop;
  private CloseButton btnClose;
  private PomodoroUnit pomUnit;

  /**
   * Creates a new timer interface with the given window, model and controller
   * 
   * @param window     - Window the timer screen will render into
   * @param model      - The timer model
   * @param controller - The timer controller
   */
  public TimerView(Stage window, ScreenModel model, ScreenController controller) {
    super(window, model, controller);
    window.setTitle((Launcher.state == Mode.PRODUCTION ? "" : "DEV | ") + "Quark Timer");
    window.setAlwaysOnTop(true);
    window.initStyle(StageStyle.UNDECORATED);
  }

  /**
   * Builds the user interface with the desired elements
   */
  @Override
  protected Scene constructView() {

    // Store cast copy of model and controller
    this.model = (TimerModel) super.model;
    this.controller = (TimerController) super.controller;

    // Create layout and scene
    layout = new Pane();

    // Setup background canvas with base colour
    background = new Canvas(windowWidth, windowHeight);
    GraphicsContext gc = background.getGraphicsContext2D();
    gc.setFill(Color.color(.8, .4, .4));
    gc.fillRect(0, 0, windowWidth, windowHeight);

    // Create the Pomodoro Units
    pomUnit = new PomodoroUnit(20, 20, 20, 20);
    pomUnit.setLayoutY(windowHeight / 2 - 38);

    // Create elippse
    Ellipse ellipse = new Ellipse();
    ellipse.setFill(Color.color(0.168, 0.188, 0.231));
    ellipse.setCenterX(windowWidth / 2);
    ellipse.setCenterY(windowHeight / 4.5);
    ellipse.setRadiusX(5.0f);
    ellipse.setRadiusY(5.0f);

    // Create timer label where main timer can be viewed
    timerLabel = new Label(formatTime(model.minutes, model.seconds));
    timerLabel.setLayoutY(windowHeight / 2 - 38);
    timerLabel.setFont(new Font("Arial", 48));
    timerLabel.setTextFill(Color.color(0.168, 0.188, 0.231));
    timerLabel.setPrefWidth(windowWidth);
    timerLabel.setAlignment(Pos.CENTER);

    // Create close button
    btnClose = new CloseButton(windowWidth - 25, 5, 20);
    btnClose.setOnClickHandler(() -> {
      controller.closeTimer(window);
    });

    // Create play button
    btnPlay = new PlayButton((windowWidth / 2) - 10, windowHeight - 35, 20);
    btnPlay.setOnClickHandler(() -> {
      controller.startTimer(window);

    });

    // Create stop button
    btnStop = new StopButton((windowWidth / 2) - 10, windowHeight - 35, 20);
    btnStop.setOnClickHandler(() -> {
      controller.stopTimer();
    });

    // Add elements to layout
    layout.getChildren().add(background);
    layout.getChildren().add(timerLabel);
    layout.getChildren().add(btnClose);
    layout.getChildren().add(btnPlay);
    layout.getChildren().add(pomUnit);
    layout.getChildren().add(ellipse);

    // Set time label as draggable element
    setDraggableElement(timerLabel);

    // Return the scene with all elements in
    return new Scene(layout, windowWidth, windowHeight);

  }

  @Override
  protected void update() {

    // Update displayed text using model values
    timerLabel.setText(formatTime(model.minutes, model.seconds));

    // Depending on state, change background and which buttons are visible
    switch (model.state) {
      case WORKING:
        setBackground(Color.color(.369, .557, .745));
        showStopButton();
        break;
      case RESTING:
        setBackground(Color.LIGHTGREEN);
        showStopButton();
        break;
      case STOPPED:
      default:
        setBackground(Color.color(.8, .4, .4));
        showPlayButton();
    }

  }

  /**
   * Show the play button and hide the stop button
   */
  protected void showPlayButton() {
    if (layout.getChildren().contains(btnStop))
      layout.getChildren().remove(btnStop);
    if (!layout.getChildren().contains(btnPlay))
      layout.getChildren().add(btnPlay);
  }

  /**
   * Show the stop button and hide the play button
   */
  protected void showStopButton() {
    if (layout.getChildren().contains(btnPlay))
      layout.getChildren().remove(btnPlay);
    if (!layout.getChildren().contains(btnStop))
      layout.getChildren().add(btnStop);
  }

  /**
   * Sets the background colour to the paint colour given
   * 
   * @param colour
   */
  public void setBackground(Paint colour) {
    GraphicsContext gc = background.getGraphicsContext2D();
    gc.setFill(colour);
    gc.fillRect(0, 0, window.getWidth(), window.getHeight());
  }

  /**
   * Sets a given node as a draggable element. Used to solve the issue where
   * window is undecorrated and needs to be movable.
   * 
   * @param node
   */
  public void setDraggableElement(Node node) {
    if (node == null && draggableElement == null)
      return;
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

  /**
   * Repositions the window to the bottom right of the default screen device
   */
  public void positionBottomRight() {
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();

    window.setX(width - window.getWidth());
    window.setY(height - window.getHeight() - 40);
  }

  /**
   * Formats the minutes and seconds for time to include 0 prior to single digit
   * values and includes a colon ':' separator
   */
  private String formatTime(int mins, int secs) {
    String s = formatSecondsMinutesHours(secs);
    String m = formatSecondsMinutesHours(mins);
    return (m + ":" + s);
  }

  /**
   * Format a unit of time to include leading 0 when single digit value is given
   * 
   * @param unit
   * @return
   */
  private String formatSecondsMinutesHours(int unit) {
    String formatted = "";
    if (unit < 10)
      formatted += "0";
    formatted += unit;
    return formatted;
  }

}
