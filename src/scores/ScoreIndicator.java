package scores;

import biuoop.DrawSurface;
import game.Counter;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Noa Azoulay.
 */
public class ScoreIndicator implements Sprite {

    private Counter counter;

    /**
     * Constructor for ScoreIndicator.
     * @param  counter  counter
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(30 + 1, 20 + 1, "SCORE: " + Integer.toString(this.counter.getValue()), 12);
        d.setColor(Color.WHITE);
        d.drawText(30, 20, "SCORE: " + Integer.toString(this.counter.getValue()), 12);
    }


    @Override
    public void timePassed(double dt) {
    }

}
