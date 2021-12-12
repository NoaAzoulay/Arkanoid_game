package game;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * @author Noa Azoulay.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(300, d.getHeight() / 2, "PAUSED", 50);
        d.drawText(305, d.getHeight() / 2 + 20, "PRESS SPACE TO CONTINUE", 14);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}

