package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
import java.awt.Image;


/**
 * The type Level bg.
 *
 * @author Noa Azoulay.
 */
public class LevelBG implements Sprite {

    private Image image;
    private Color color;

    /**
     * Constructor by image.
     * @param  i  background image
     */
    public LevelBG(Image i) {
        this.image = i;
    }

    /**
     * Constructor by color.
     * @param  c  Background simple color
     */
    public LevelBG(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        } else {
            d.drawImage(0, 0, this.image);
        }
    }

    @Override
    public void timePassed(double dt) {
    }

}

