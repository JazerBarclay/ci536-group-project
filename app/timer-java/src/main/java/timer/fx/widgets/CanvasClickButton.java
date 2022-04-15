package timer.fx.widgets;

import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class CanvasClickButton extends Canvas {

    protected GraphicsContext gc;
    protected ClickEvent press;

    public CanvasClickButton(int x, int y, int width, int height) {
	super(width, height);

	this.gc = getGraphicsContext2D();

	setLayoutX(x);
	setLayoutY(y);

	clear();
	drawClicked();
	setClickEvent();
    }

    public CanvasClickButton() {
	this(0, 0, 100, 100);
    }

    protected void setClickEvent() {
	setOnMouseClicked((Event e) -> {
	    press.onClick();
	    clear();
	    drawClicked();
	});
    }

    public void setOnClickHandler(ClickEvent event) {
	this.press = event;
    }

    public void clear() {
	gc.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    public abstract void drawClicked();


}
