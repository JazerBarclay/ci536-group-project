package timer.fx.widgets;

import javafx.scene.paint.Color;

public class StopButton extends CanvasClickButton {

    public StopButton(int x, int y, int width, int height) {
	super(x, y, width, height);
    }

    public StopButton(int x, int y, int size) {
	this(x, y, size, size);
    }

    public StopButton(int x, int y) {
	this(x, y, 25, 25);
    }

    public StopButton() {
	this(0, 0);
    }

    @Override
    public void drawClicked() {
	gc.setStroke(Color.color(0.168, 0.188, 0.231));
	gc.setLineWidth(4);
	gc.strokeRect(0, 0, getWidth(), getHeight());
    }

}
