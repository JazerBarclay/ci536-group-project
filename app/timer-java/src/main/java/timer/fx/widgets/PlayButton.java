package timer.fx.widgets;

import javafx.scene.paint.Color;

public class PlayButton extends CanvasClickButton {

    public PlayButton(int x, int y, int width, int height) {
	super(x, y, width, height);
    }

    public PlayButton(int x, int y, int size) {
	this(x, y, size, size);
    }

    public PlayButton(int x, int y) {
	this(x, y, 25, 25);
    }

    public PlayButton() {
	this(0, 0);
    }

    @Override
    public void drawClicked() {
	gc.setStroke(Color.color(0.168, 0.188, 0.231));
	gc.setLineWidth(2);
	gc.strokePolygon(new double[]{2, (getWidth()/5)*4+3, 2}, new double[]{0, getHeight()/2, getHeight()}, 3);
    }

}
