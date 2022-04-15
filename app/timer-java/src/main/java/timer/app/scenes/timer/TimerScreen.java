package timer.app.scenes.timer;

import javafx.stage.Stage;
import timer.fx.mvc.Screen;

public class TimerScreen extends Screen {

    public TimerScreen(Stage window) {
	super(window);
    }

    @Override
    protected void constructMVC(Stage window) {
	
	TimerModel model = new TimerModel();
	TimerController controller = new TimerController(model);
	TimerView view = new TimerView(window, model, controller);
	
	model.setChangeListener( () -> view.repaint() );
	
    }

}
