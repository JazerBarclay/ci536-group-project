package timer.fx.widgets;

import javafx.scene.paint.Color;

public class ProgressBar extends CanvasClickButton {

    int count = 0;

    public ProgressBar(int x, int y, int width, int height) {
        super(x, y, width, height);
        setLayoutX(x);
        setLayoutY(y);
    }

    public ProgressBar(int x, int y, int size) {
        this(x, y, size, size);
    }

    public ProgressBar(int x, int y) {
        this(x, y, 25, 25);
    }

    public ProgressBar() {
        this(0, 0);
    }
    
    public void setCount(int count) {
      this.count = count;
    }

    @Override
    public void drawClicked() {
       gc.setStroke(Color.BLACK);
       gc.setFill(Color.color(0.168, 0.188, 0.231));
       int w = 8, p = 4;
       for (int i = 0; i < count; i++) {
         gc.strokeOval(getWidth()/2 - (count*(w+p)) / 2+i*(w+p) + p/2, getHeight()/2-w/2, w, w);
         gc.fillOval(getWidth()/2 - (count*(w+p)) / 2+i*(w+p) + p/2, getHeight()/2-w/2, w, w);
       }
    }
    
}
