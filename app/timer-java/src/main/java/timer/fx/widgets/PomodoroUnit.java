package timer.fx.widgets;

import javafx.scene.paint.Color;

public class PomodoroUnit extends CanvasClickButton {

  int count;

  public PomodoroUnit(int x, int y, int width, int height) {
    super(x, y, width, height);
    setLayoutX(x);
    setLayoutY(y);
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
    int size=8;
    gc.setStroke(Color.color(0.168,0.188,0.231));
    gc.strokeOval(getWidth()/2, getHeight()/2, size,size);
//    Ellipse ellipse = new Ellipse();
//    ellipse.setFill(Color.color(0.168, 0.188, 0.231));
//    ellipse.setCenterX(getWidth()/ 2);
//    ellipse.setCenterY(getHeight() / 4.5);
//    ellipse.setRadiusX(5.0f);
//    ellipse.setRadiusY(5.0f);
  }
}
