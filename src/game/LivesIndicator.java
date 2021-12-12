package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Lives indicator.
 *
 * @author Noa Azoulay.
 */
public class LivesIndicator implements Sprite {

    private Counter counter;

    /**
     * Constructor for LivesIndicator.
     * @param  counter  counter
     */
    public LivesIndicator(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(210 + 1, 20 + 1, "LIVES: " + Integer.toString(this.counter.getValue()), 12);
        d.setColor(Color.WHITE);
        d.drawText(210, 20, "LIVES: " + Integer.toString(this.counter.getValue()), 12);
    }

    @Override
    public void timePassed(double dt) {
    }

}
