package timer.fx.mvc;

import javafx.stage.Stage;

/**
 * A simple screen template which utilises the MVC design pattern.
 * 
 * @author Jazer
 *
 */
public abstract class Screen {

  /** Window which this screen is contained **/
  protected Stage window;

  /**
   * Creates a new screen with the given stage window
   * 
   * @param window
   */
  public Screen(Stage window) {
    this.window = window;
    constructMVC(window);
    window.show();
  }

  /**
   * Template method to construct the MVC design pattern associated with this
   * implementation of a screen.
   * 
   * @param window
   */
  protected abstract void constructMVC(Stage window);

}
