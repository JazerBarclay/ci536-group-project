package timer.app.scenes.timer;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

/**
 * The timer screen where the user can start and stop a timer
 * which logs their pomodoro units. These units are uploaded to the
 * server upon each completion.
 * 
 * @author Jazer
 *
 */
public class TimerScreen extends Screen {

    private String loginToken;
    
    /**
     * Creates a new timer screen with the given window
     * @param window
     */
    public TimerScreen(Stage window, String jwt) {
	super(window);
	this.loginToken = jwt;
    }

    /**
     * Initialise the model, view, controller and link them
     * together with the change listener
     */
    @Override
    protected void constructMVC(Stage window) {
	
	TimerModel model = new TimerModel(loginToken);
	TimerController controller = new TimerController(model);
	TimerView view = new TimerView(window, model, controller);
	
	model.setChangeListener(() -> view.repaint());
	
    }

}
