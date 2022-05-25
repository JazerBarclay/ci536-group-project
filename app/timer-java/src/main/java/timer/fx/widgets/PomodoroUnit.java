package timer.fx.widgets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PomodoroUnit extends CanvasClickButton {

  public PomodoroUnit(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  public PomodoroUnit(int x, int y, int size) {
    this(x, y, size, size);
  }

  public PomodoroUnit(int x, int y) {
    this(x, y, 25, 25);
  }

  public PomodoroUnit() {
    this(0, 0);
  }

  @Override
  public void drawClicked() {
//        Ellipse ellipse = new Ellipse();
//        ellipse.setCenterX(300.0f);  ellipse.setCenterY(150.0f);  ellipse.setRadiusX(150.0f);  ellipse.setRadiusY(75.0f);
  }
}
