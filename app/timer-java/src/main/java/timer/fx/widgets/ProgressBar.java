package timer.fx.widgets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class ProgressBar extends CanvasClickButton {

    int count;

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

    @Override
    public void drawClicked() {
       gc.setStroke(Color.BLACK);
       gc.strokeRect(0,0,getWidth(),getHeight());
    }
}
