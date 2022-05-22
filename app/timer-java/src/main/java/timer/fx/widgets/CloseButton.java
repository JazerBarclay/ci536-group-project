package timer.fx.widgets;

import javafx.scene.paint.Color;

public class CloseButton extends CanvasClickButton {

    public CloseButton(int x, int y, int width, int height) {
	super(x, y, width, height);
    }

    public CloseButton(int x, int y, int size) {
	this(x, y, size, size);
    }

    public CloseButton(int x, int y) {
	this(x, y, 25, 25);
    }

    public CloseButton() {
	this(0, 0);
    }

    @Override
    public void drawClicked() {
	gc.setFill(Color.color(0.168, 0.188, 0.231));
	gc.fillPolygon(new double[]{5, getWidth() - 3, getWidth() - 5, 3}, new double[]{3, getHeight() - 5, getHeight() - 3, 5}, 4);
	gc.fillPolygon(new double[]{getWidth() - 5, getWidth() - 3, 5, 3}, new double[]{3, 5, getHeight() - 3, getHeight() - 5}, 4);
    }

}
