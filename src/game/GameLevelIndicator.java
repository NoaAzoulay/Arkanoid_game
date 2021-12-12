package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Game level indicator.
 *
 * @author Noa Azoulay.
 */
public class GameLevelIndicator implements Sprite {

    private String text;

    /**
     * Constructor for GameLevelIndicator.
     * @param  n  name of the level
     */
    public GameLevelIndicator(String n) {
        this.text = "LEVEL: " + n;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(600 + 1, 20 + 1, this.text, 12);
        d.setColor(Color.WHITE);
        d.drawText(600, 20, this.text, 12);
    }

    @Override
    public void timePassed(double dt) {
    }

}
