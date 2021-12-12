package game;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Countdown animation.
 *
 * @author Noa Azoulay.
 */
public class CountdownAnimation implements Animation {

    private double seconds, start;
    private int countFrom;
    private SpriteCollection sprites;
    private boolean stop;

    /**
     * Constructor for CountdownAnimation.
     * @param  numOfSeconds  number of seconds left to count
     * @param  countFrom     starting count
     * @param  gameScreen    spritecollection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.countFrom = countFrom;
        this.seconds = numOfSeconds * 1000 / this.countFrom;
        this.stop = false;
        this.start = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        if (this.countFrom <= 0) {
            this.stop = true;
        }
        if (System.currentTimeMillis() <= this.start + this.seconds) {
            d.setColor(Color.WHITE);
            d.drawText(266, 450, Integer.toString(this.countFrom), 500);
            d.drawText(266, 449, Integer.toString(this.countFrom), 500);
            d.drawText(264, 449, Integer.toString(this.countFrom), 500);
            d.drawText(264, 451, Integer.toString(this.countFrom), 500);

            d.setColor(Color.BLACK);
            d.drawText(265, 450, Integer.toString(this.countFrom), 500);

        } else {
            this.countFrom--;
            this.start = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
