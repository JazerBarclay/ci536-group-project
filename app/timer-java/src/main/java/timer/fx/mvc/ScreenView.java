package timer.fx.mvc;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An abstract template for an MVC screen view. Includes protected
 * class variables for the window, scene, model and controller.
 * <br><br>
 * Functions include constructing the layout view where the elements
 * can be initialised, what to do when updating the layout based
 * on the model and a repaint method that manages running the update.
 * <br><br>
 * The model and view will need to be recast as their implementations
 * when used in functionality. I would recommend creating a private local 
 * class variable to store the cast copy.
 * 
 * @author Jazer
 * 
 */
public abstract class ScreenView {

    /** Window where the screen is contained **/
    protected Stage window;
    
    /** Contents of screen container **/
    protected Scene scene;
    
    /** Implemenmtation of screen model **/
    protected ScreenModel model;
    
    /** Implementation of screen controller **/
    protected ScreenController controller;
    
    /**
     * Creates a new screen view with the given window, model and controller.
     * 
     * @param window
     * @param model
     * @param controller
     */
    public ScreenView(Stage window, ScreenModel model, ScreenController controller) {
	this.window = window;
	this.model = model;
	this.controller = controller;
	this.scene = constructView();
	window.setScene(scene);
    }
    
    /**
     * Creates the view for the contructor which is then
     * displayed in the given window.
     * 
     * @return a new scene containing the view elements
     */
    protected abstract Scene constructView();
    
    /**
     * Manages how the view is updated when there is a change
     * in the implemented model.
     */
    protected abstract void update();
    
    /**
     * Call to update the view within a new javafx thread
     */
    public void repaint() {
	Platform.runLater(() -> {
	    update();
	});
    }
    
}
