package timer.fx.mvc;

import javafx.stage.Stage;

/**
 * An abstract template for an MVC screen controller. Includes a protected
 * class variable to store the associated model.
 * 
 * @author Jazer
 *
 */
public abstract class ScreenController {
    
    /** Model which the controller affects **/
    protected ScreenModel model;

    /**
     * Creates a new screen controller with the associated
     * model.
     * 
     * @param model
     */
    public ScreenController(ScreenModel model) {
	this.model = model;
    }
    
    /**
     * Closes the given window.
     * 
     * @param window
     */
    public void closeWindow(Stage window) {
	window.close();
    }
    
}
