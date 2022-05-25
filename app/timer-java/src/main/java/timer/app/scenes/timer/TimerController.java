package timer.app.scenes.timer;

import javafx.stage.Stage;
import timer.fx.mvc.ScreenController;
import timer.fx.mvc.ScreenModel;

/**
 * Interaction management for timer screen
 * 
 * @author Jazer
 *
 */
public class TimerController extends ScreenController {

  private TimerModel model;

  /**
   * Create a new controller with the given model to interact with
   * 
   * @param model
   */
  public TimerController(ScreenModel model) {
    super(model);
    this.model = (TimerModel) model;
  }

  public void closeTimer(Stage window) {
    model.stopTimer();
    window.close();
  }

  public void startTimer() {
    model.startTimer();
  }

  public void stopTimer() {
    model.stopTimer();
  }

}
