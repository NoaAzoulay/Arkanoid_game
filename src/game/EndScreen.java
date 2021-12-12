package game;

import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;


/**
 * The type End screen.
 *
 * @author Noa Azoulay.
 */
public class EndScreen implements Animation {
    private String score;


    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     */
    public EndScreen(Counter score) {
        this.score = Integer.toString(score.getValue());
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(300, 300, "GAME OVER", 32);
        d.drawText(325, 350, "YOU LOSE", 50);
        d.drawText(30, 560, "Final score: " + this.score, 18);
        d.drawText(30, 580, "Press space to continue", 15);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}

