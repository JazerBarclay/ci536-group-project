package timer.fx.mvc;

/**
 * An abstract template for an MVC screen model. Includes simple function
 * for adding a screen event listener and storing in protected class variable.
 * 
 * @author Jazer
 *
 */
public abstract class ScreenModel {

    /** On change event listener for view changes **/
    private ScreenEventListener listener;
    
    /**
     * Sets a listener within the model which can update the view
     * on event changes.
     * 
     * @param listener
     */
    public void setChangeListener(ScreenEventListener listener) {
	this.listener = listener;
    }
    
    /**
     * Updates the view using the change listener
     */
    public void updateView() {
	if (listener != null) listener.onChange();
    }
    
}
